package com.eliseev.trudhack2.dao

import com.eliseev.trudhack2.service.CommandExecutorService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

interface TestModelDao {
    fun getSuggestions(rawFilePath: String): Int
}

@Repository
class TestModelExecution(private val commandExecutorService: CommandExecutorService) : TestModelDao {

    override fun getSuggestions(rawFilePath: String): Int {
        return commandExecutorService.exec(
            listOf("python3", "/home/hack/hack/test.py", rawFilePath)
        )?.trim()?.replace("\n", "")?.toInt() ?: -1
    }

    companion object {
        private val logger = LoggerFactory.getLogger(TestModelExecution::class.java)
    }
}
