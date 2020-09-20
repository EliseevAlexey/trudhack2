package com.eliseev.trudhack2.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.File

interface FileSystemService {
    fun save(body: String, fileName: String)
}

@Service
class FileSystemServiceImpl : FileSystemService {
    override fun save(body: String, fileName: String) {
        logger.info("Saving '$fileName'")
        File(fileName).writeText(body)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FileSystemServiceImpl::class.java)
    }
}
