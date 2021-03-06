package com.eliseev.trudhack2.service

object InputValidator {
    fun validate(input: String) {
        if (input in INVALID_DESCRIPTION) throw InvalidInputException("Invalid input")
    }

    private val INVALID_DESCRIPTION = listOf(
        ".",
        ",",
        "/",
        "-",
        "...",
        "..",
        "--",
        "....",
        "***************************",
        ".....",
        "5+",
        "http://slavyansky23.ru",
        "!",
        "*",
        "123",
        "1",
        "***",
        "................................................................................................................",
        ".................................................................................................................................",
        "/////",
        "010100100010100101010010101001",
        "1212",
        ",)))",
        ")",
        "🙌🏻",
        ".............",
        "****",
        "_",
        "))",
        "^_^",
        "-//-",
        "111",
        "......",
        "+",
        "2",
        ",,,",
        "---------",
        "-----",
        "—",
        """''"'"'""",
        ":)"
    )
}
