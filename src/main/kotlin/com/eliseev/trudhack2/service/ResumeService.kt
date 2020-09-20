package com.eliseev.trudhack2.service

import com.eliseev.trudhack2.model.JobModelDto
import com.eliseev.trudhack2.model.toDto
import com.eliseev.trudhack2.util.LocalDateTimeUtil.nowString
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

interface ResumeService {
    fun upload(input: String): JobModelDto?
}

@Service
class ResumeServiceImpl(
    private val fileSystemService: FileSystemService,
    private val suggestionService: SuggestionService,
    @Value("\${storage.raw}") private val rawPath: String,
    private val udPipeService: UDPipeService
) : ResumeService {

    override fun upload(input: String): JobModelDto? {
        logger.info("Uploading resume: '$input'")
        InputValidator.validate(input)
        val correctedData = InputCorrector.correctData(input)
        val rawFileName = saveRaw(correctedData)

        val result = udPipeService.convertToUDPipe(correctedData)
        val udpipeName = udPipeService.store(result)

        return suggestionService.getSuggestion(rawFileName)
            ?.toDto(udpipeName)
    }

    private fun saveRaw(input: String): String {
        val rawFileName = "$rawPath${File.separator}raw_${nowString()}.txt"
        fileSystemService.save(input, rawFileName)
        return rawFileName
    }


    companion object {
        private val logger = LoggerFactory.getLogger(ResumeServiceImpl::class.java)
    }
}

