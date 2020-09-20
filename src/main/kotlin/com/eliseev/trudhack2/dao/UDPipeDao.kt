package com.eliseev.trudhack2.dao

import com.eliseev.trudhack2.model.UDPipeModel
import com.eliseev.trudhack2.model.UDPipeResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate

interface UDPipeDao {
    fun process(input: String, model: UDPipeModel): UDPipeResponse?
}

@Repository
class UDPipeRestClient(private val restTemplate: RestTemplate) : UDPipeDao {
    override fun process(input: String, model: UDPipeModel): UDPipeResponse? {
        val url = "$TEMPLATE_URL&model=${model.modelName}&data=$input"
        logger.debug("URL: $url")
        return restTemplate.postForObject(
            url,
            null,
            UDPipeResponse::class.java
        )
    }

    companion object {
        private val logger = LoggerFactory.getLogger(UDPipeRestClient::class.java)
        private const val TEMPLATE_URL = "http://lindat.mff.cuni.cz/services/udpipe/api/process?tokenizer&tagger&parser"
    }
}
