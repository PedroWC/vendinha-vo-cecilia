package com.vendinha.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Vendinha da Vó Cecília - API Documentation")
                        .version("1.0.0")
                        .description("Documentação da API do sistema Vendinha da Vó Cecília"));
    }
}
