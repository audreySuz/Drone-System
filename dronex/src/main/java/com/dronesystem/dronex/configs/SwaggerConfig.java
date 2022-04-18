package com.dronesystem.dronex.configs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Sabi Employee
 */
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(info())
                .externalDocs(new ExternalDocumentation());
    }

    private Info info() {
        return new Info()
                .title("Dronex Api")
                .description("Documentation for Dronex")
                .contact(new Contact()
                        .email("asuzu.audrey@sabi.am")
                        .name("Asuzu Audrey")
                        .url("")
                )
                .termsOfService("")
                .version("2.0");
    }

}
