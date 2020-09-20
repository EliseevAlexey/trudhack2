package com.eliseev.trudhack2.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
class Answer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @OneToOne
    val question: Question,
    val answerText: String?
)

data class AnswerDto(
    val id: Long,
    val answer: String
)
