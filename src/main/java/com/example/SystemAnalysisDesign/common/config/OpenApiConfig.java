package com.example.SystemAnalysisDesign.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "시분설 팀플",
                description = "API 명세서",
                version = "v1",
                contact = @Contact(
                        name = "김재관",
                        email = "jaegwan101@gmail.com"
                )
        )
)
@Configuration
public class OpenApiConfig {
}