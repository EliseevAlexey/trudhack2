package com.eliseev.trudhack2.model

import com.eliseev.trudhack2.dao.AnswerDao
import com.eliseev.trudhack2.dao.QuestionDao
import org.springframework.stereotype.Service

interface QuestionService {
    fun saveAll(answersDto: List<AnswerDto>)
}

@Service
class QuestionServiceImpl(
    private val questionDao: QuestionDao,
    private val answerDao: AnswerDao
) : QuestionService {
    override fun saveAll(answersDto: List<AnswerDto>) {
        val questions = questionDao.findAll().associateBy { it.id!! }

        val answers = answersDto.map {
            Answer(
                answerText = it.answer,
                question = questions.getValue(it.id)
            )
        }
        answerDao.saveAll(answers)
    }
}
