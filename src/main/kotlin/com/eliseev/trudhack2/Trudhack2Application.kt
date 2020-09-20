package com.eliseev.trudhack2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class Trudhack2Application

fun main(args: Array<String>) {
    runApplication<Trudhack2Application>(*args)
}
