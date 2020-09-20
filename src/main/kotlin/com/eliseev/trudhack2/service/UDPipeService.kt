package com.eliseev.trudhack2.service

import com.eliseev.trudhack2.dao.UDPipeDao
import com.eliseev.trudhack2.model.UDPipeModel
import com.eliseev.trudhack2.util.LocalDateTimeUtil.nowString
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File
import java.time.LocalDateTime

interface UDPipeService {
    fun process(input: String): String
    fun convertToUDPipe(input: String): String
    fun store(input: String): String
}

@Service
class UDPipeServiceImpl(
    private val udPipeDao: UDPipeDao,
    @Value("\${storage.udpipe}") private val udpipePath: String,
    private val fileSystemService: FileSystemService
) : UDPipeService {
    override fun process(input: String): String {
        logger.info("Processing '$input'")
        InputValidator.validate(input)
        val result = convertToUDPipe(InputCorrector.correctData(input))
        store(result)
        return result
    }

    override fun convertToUDPipe(input: String): String {
        val ruModel = UDPipeModel.RU_MODEL

        val sentences = input.split(". ")
            .map { "$it." } // FIXME save delimiter after splitting
        val size = sentences.size
        val parsedWords = mutableListOf<String>()
        sentences.forEachIndexed { index, sentence ->
            val words = udPipeDao.process(sentence.toLowerCase(), ruModel)!!.result.lines()
            words.filter { !it.isBlank() && !it.startsWith("#") }
                .let { parsedWords.addAll(it) }

            if (index == size - 1) {
                parsedWords.addAll(listOf("\\n", "\\n"))
            }
        }

        return parsedWords.joinToString("\n")
    }

    override fun store(input: String): String {
        val udpipeName = "processed_${nowString()}"
        val processedPath = "$udpipePath${File.separator}$udpipeName.conllu"
        fileSystemService.save(input, processedPath)
        return udpipeName
    }

    companion object {
        private val logger = LoggerFactory.getLogger(UDPipeServiceImpl::class.java)
    }
}
