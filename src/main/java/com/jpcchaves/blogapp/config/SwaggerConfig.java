package com.jpcchaves.blogapp.config;


import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Blog App API",
                version = "v1.0",
                description = "Blog App API",
                termsOfService = "https://github.com/jpcchaves/blog-app-api/blob/master/LICENSE",
                contact = @Contact(
                        name = "Jonathan Chaves",
                        url = "https://github.com/jpcchaves",
                        email = "anpch@example.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Blog App API",
                url = "https://github.com/jpcchaves/blog-app-api"
        ))
@Configuration
public class SwaggerConfig {
}
