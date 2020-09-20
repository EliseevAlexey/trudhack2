package com.eliseev.trudhack2.service

import com.eliseev.trudhack2.dao.TestModelDao
import com.eliseev.trudhack2.model.JobModel
import org.springframework.stereotype.Service

interface SuggestionService {
    fun getSuggestion(rawFilePath: String): JobModel?
}

@Service
class SuggestionServiceImpl(
    private val jobService: JobService,
    private val testModelDao: TestModelDao
) : SuggestionService {
    override fun getSuggestion(rawFilePath: String): JobModel? {
        val suggestionsNumber = testModelDao.getSuggestions(rawFilePath)
        return jobService.getByTestModelResponse(suggestionsNumber)
    }
}
