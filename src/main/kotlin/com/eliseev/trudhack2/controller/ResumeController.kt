package com.eliseev.trudhack2.controller

import com.eliseev.trudhack2.model.JobModelDto
import com.eliseev.trudhack2.service.ResumeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/upload")
class ResumeController(private val resumeService: ResumeService) {
    @GetMapping
    fun upload(input: String): JobModelDto? = resumeService.upload(input)
}
