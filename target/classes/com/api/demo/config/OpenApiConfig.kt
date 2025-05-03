package com.api.demo.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(Info()
                .title("Product Management API")
                .description("RESTful API for managing products")
                .version("1.0.0")
                .contact(Contact()
                    .name("API Support")
                    .email("support@example.com"))
                .license(License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0"))
            )
    }
}