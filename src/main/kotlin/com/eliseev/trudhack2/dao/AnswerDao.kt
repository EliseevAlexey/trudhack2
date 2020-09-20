package com.eliseev.trudhack2.dao

import com.eliseev.trudhack2.model.Answer
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerDao : JpaRepository<Answer, Long>
