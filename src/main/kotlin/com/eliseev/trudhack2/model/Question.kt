package com.eliseev.trudhack2.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Question(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne val jobModel: JobModel,
    val shortCode: String,
    val description: String,
    val answerType: QuestionDataType
)

data class QuestionDto(
    val id: Long,
    val shortCode: String,
    val question: String,
    val answerType: String
)

fun Question.toDto() = QuestionDto(
    id = id!!,
    shortCode = shortCode,
    question = description,
    answerType = answerType.name
)

fun List<Question>.toDto() = map { it.toDto() }
