package com.eliseev.trudhack2.controller

import com.eliseev.trudhack2.service.FinalJsonService
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/final")
class FinalJsonController(private val finalJsonService: FinalJsonService) {
    @PutMapping
    fun updateJson(input: String, modelNumber: Int) {
        finalJsonService.updateJson(input, modelNumber)
    }
}
