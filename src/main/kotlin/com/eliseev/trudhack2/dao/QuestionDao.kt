package com.eliseev.trudhack2.dao

import com.eliseev.trudhack2.model.Question
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionDao : JpaRepository<Question, Long>
