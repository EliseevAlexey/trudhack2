package com.eliseev.trudhack2.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

@ConfigurationProperties(prefix = "command-executor")
@Validated
class CommandProperties {

    @NotBlank
    var workingDir: String = ""

    @Positive
    var timeout: Long = -1

}
