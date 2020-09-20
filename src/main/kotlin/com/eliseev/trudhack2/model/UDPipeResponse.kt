package com.eliseev.trudhack2.model

data class UDPipeResponse(
    val model: String,
    val acknowledgements: List<String>,
    val result: String
)
