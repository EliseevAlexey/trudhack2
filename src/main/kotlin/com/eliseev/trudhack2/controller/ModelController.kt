package com.eliseev.trudhack2.controller

import com.eliseev.trudhack2.model.QuestionDto
import com.eliseev.trudhack2.model.RequestModelDto
import com.eliseev.trudhack2.model.toDto
import com.eliseev.trudhack2.service.ModelService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/model")
class ModelController(private val modelService: ModelService) {
    @PostMapping
    fun process(@RequestBody jobModel: RequestModelDto): List<QuestionDto> =
        modelService.process(jobModel).toDto()
}
