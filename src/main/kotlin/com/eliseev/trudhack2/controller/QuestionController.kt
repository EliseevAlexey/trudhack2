package com.eliseev.trudhack2.controller

import com.eliseev.trudhack2.model.AnswerDto
import com.eliseev.trudhack2.model.QuestionService
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/questions")
class QuestionController(private val questionService: QuestionService) {
    @PutMapping("/save-answers")
    fun saveAnswers(@RequestBody answers: List<AnswerDto>) = questionService.saveAll(answers)
}
