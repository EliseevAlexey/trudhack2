package com.eliseev.trudhack2.controller

import com.eliseev.trudhack2.service.UDPipeService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/udpipe")
class UDPipeController(private val udPipeService: UDPipeService) {

    @PostMapping
    fun process(input: String): String = udPipeService.process(input)
}
