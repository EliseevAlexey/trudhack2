package com.eliseev.trudhack2.util

import com.eliseev.trudhack2.service.ResumeServiceImpl
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object LocalDateTimeUtil {
    fun nowString() = LocalDateTime.now().format(pattern)
    private val pattern = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
}
