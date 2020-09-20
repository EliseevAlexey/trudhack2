package com.eliseev.trudhack2.config

import com.eliseev.trudhack2.Trudhack2Application
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@Configuration
class SwaggerConfiguration {
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage(Trudhack2Application::class.java.`package`.name))
        .paths(PathSelectors.any())
        .build()
}
