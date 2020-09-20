package com.eliseev.trudhack2.service

import com.eliseev.trudhack2.dao.AnswerDao
import com.eliseev.trudhack2.model.Answer
import com.eliseev.trudhack2.model.JobEnumDto
import com.eliseev.trudhack2.model.JobEnumDto.*
import com.eliseev.trudhack2.model.Question
import com.eliseev.trudhack2.model.RequestModelDto
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File

interface ModelService {
    fun process(jobModel: RequestModelDto): List<Question>
}

@Service
class ModelServiceImpl(
    private val commandExecutorService: CommandExecutorService,
    private val jobService: JobService,
    @Value("\${storage.udpipe}") private val udpipePath: String,
    private val objectMapper: ObjectMapper,
    private val answerDao: AnswerDao
) : ModelService {


    override fun process(jobModel: RequestModelDto): List<Question> {
        logger.info("Applying model to $jobModel")
        val udpipeFile = "${udpipePath}${File.separator}${jobModel.udpipeName}.conllu"
        when (valueOf(jobModel.modelName)) {
            STOREKEEPER -> {
                val modelAnswers = callModel("model1", udpipeFile)
                val questions = getQuestions(STOREKEEPER)
                val compareAnswers = compareAnswers(questions, modelAnswers)
                saveAnswers(compareAnswers.answers)
                return compareAnswers.lostQuestions
            }
            DRIVER_LOADER -> {
                val modelAnswers = callModel("model1", udpipeFile) // FIXME modelName
                val questions = getQuestions(DRIVER_LOADER)
                val compareAnswers = compareAnswers(questions, modelAnswers)
                saveAnswers(compareAnswers.answers)
                return compareAnswers.lostQuestions
            }
            WAITER -> {
                val modelAnswers = callModel("model1", udpipeFile) // FIXME modelName
                val questions = getQuestions(WAITER)
                val compareAnswers = compareAnswers(questions, modelAnswers)
                saveAnswers(compareAnswers.answers)
                return compareAnswers.lostQuestions
            }
        }
    }

    private data class CompareResult(
        val answers: List<Answer>,
        val lostQuestions: List<Question>
    )

    private fun compareAnswers(questions: List<Question>, modelAnswers: Map<String, String>): CompareResult {
        val currentQuestionSize = questions.size
        val gotAnswers = mutableListOf<Answer>()
        val lostQuestions = mutableListOf<Question>()
        questions.forEach { question ->
            modelAnswers[question.description]?.let {
                gotAnswers.add(
                    Answer(question = question, answerText = it)
                )
            } ?: lostQuestions.add(question)
        }

        val gotAnswersSize = gotAnswers.size
        val message = if (gotAnswersSize != 0) {
            String.format("Automatically detected %.0f%%", currentQuestionSize.toDouble() / gotAnswersSize.toDouble())
        } else "No answers detected"

        logger.info(message)
        return CompareResult(
            answers = gotAnswers,
            lostQuestions = lostQuestions
        )
    }

    private fun getQuestions(jobEnum: JobEnumDto): List<Question> {
        return jobService.getByName(jobEnum.entityName).questions
    }

    private fun saveAnswers(answers: List<Answer>) {
        answerDao.saveAll(answers)
    }

    private fun callModel(modelName: String, udpipePath: String): Map<String, String> {
        val modelsPath = "/home/hack/hack/$modelName.py"
        val jsonResponse = commandExecutorService.exec(
            listOf("python3", modelsPath, udpipePath)
        )!!

        return objectMapper.readValue<HashMap<String, String>>(jsonResponse)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ModelServiceImpl::class.java)
    }
}
