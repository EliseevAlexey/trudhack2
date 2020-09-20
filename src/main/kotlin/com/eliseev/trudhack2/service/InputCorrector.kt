package com.eliseev.trudhack2.service

object InputCorrector {
    fun correctData(input: String): String =
        input.mapNotNull {
            if (it.isDigit() || it.isLetter() || it in VALID_PUNCTUATION_SYMBOLS) {
                it
            } else null
        }.let { String(it.toCharArray()) }

    private val VALID_PUNCTUATION_SYMBOLS = setOf(' ')

}
