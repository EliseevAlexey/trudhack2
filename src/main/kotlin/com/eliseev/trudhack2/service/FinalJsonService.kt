package com.eliseev.trudhack2.service

import org.apache.tomcat.jni.File
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.FileOutputStream
import java.util.concurrent.atomic.AtomicInteger

interface FinalJsonService {
    fun updateJson(input: String, modelNumber: Int)
}

@Service
class FinalJsonServiceImpl(
    private val udPipeService: UDPipeService,
    private val commandExecutorService: CommandExecutorService
) : FinalJsonService {

    private val counter = AtomicInteger(0)

    override fun updateJson(input: String, modelNumber: Int) {
        logger.info("Processing ${counter.get()} element by model $modelNumber")
        InputValidator.validate(input)
        val correctedData = InputCorrector.correctData(input)

        val udPipeData = udPipeService.convertToUDPipe(correctedData)
        val udPipeFileName = udPipeService.store(udPipeData)
        store(callModel(modelNumber, udPipeFileName), modelNumber, udPipeFileName)

        counter.incrementAndGet()
    }

    private fun store(modelResponse: String?, modelNumber: Int, udPipeFileName: String) {
        if (modelResponse != null) {
            FileOutputStream(FULL_JSON_PATH, true).bufferedWriter().use { writer ->
                writer.append("$modelResponse,\n")
            }
        } else logger.error("No model[$modelNumber] response! $udPipeFileName")

    }

    private fun callModel(modelNumber: Int, udPipeFileName: String): String? {
        val modelsPath = "/home/hack/hack/model$modelNumber.py"
        return commandExecutorService.exec(
            listOf("python3", modelsPath, udPipeFileName)
        )
    }

    companion object {
        private const val FULL_JSON_PATH = "/home/hack/fullJson.json"
        private val logger = LoggerFactory.getLogger(FinalJsonServiceImpl::class.java)
    }
}
