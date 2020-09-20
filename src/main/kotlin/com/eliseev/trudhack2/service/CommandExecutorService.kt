package com.eliseev.trudhack2.service

import com.eliseev.trudhack2.config.CommandProperties
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.File
import java.util.concurrent.TimeUnit

interface CommandExecutorService {
    fun exec(commands: List<String>): String?
}

@Service
class CommandExecutorServiceImpl(private val commandProperties: CommandProperties) : CommandExecutorService {

    override fun exec(commands: List<String>): String? {
        logger.debug("EXE: '${commands.asString()}'")
        return runCommand(args = commands)
            .also { logger.debug("OUT: $it") }
    }

    private fun runCommand(
        workingDir: File = File(commandProperties.workingDir),
        timeoutAmount: Long = commandProperties.timeout,
        args: List<String>
    ): String? {
        return try {
            val process = ProcessBuilder(args)
                .directory(workingDir)
                .start()
                .also { it.waitFor(timeoutAmount, TimeUnit.SECONDS) }

            when (val exitValue = process.exitValue()) {
                SUCCESS_EXIT_CODE -> process.inputStream.bufferedReader().readText()
                else -> logAndReturnNull(process, args, exitValue)
            }
        } catch (ex: Exception) {
            logger.error("Error while processing '${args.asString()}'", ex)
            null
        }
    }

    private fun logAndReturnNull(process: Process, args: List<String>, exitValue: Int): String? {
        logger.error(
            "Error while processing '${args.asString()}' with exit code: $exitValue. " +
                "Out: ${process.errorStream.bufferedReader().readText()}"
        )
        return null
    }

    private fun List<String>.asString() = this.joinToString(separator = " ")

    companion object {
        private val logger = LoggerFactory.getLogger(CommandExecutorServiceImpl::class.java)

        private const val SUCCESS_EXIT_CODE = 0
    }
}
