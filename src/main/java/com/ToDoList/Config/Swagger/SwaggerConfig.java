package com.ToDoList.Config.Swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	
	@Bean
	 OpenAPI customOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("Rest API com Java 21 e Spring Boot 3.")
				.version("v1")
				.description("A API permite que o usuario gerencie suas tarefas de maneira simples e facil, permitindo a criação, atualização, deleção e a listagem das mesmas.")
				.license(
					new License()
						.name("Apache 2.0")
						.url("")
					)
				)
			
			.components(new Components()
					.addSecuritySchemes("BearerAuth", new SecurityScheme()
					.type(SecurityScheme.Type.HTTP)
					.scheme("bearer")
					.bearerFormat("JWT")
					.description("Insira o token JWT no formato: Bearer {seu_token}")
					
							)
					)
			
			.addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
			
			
			
			;
	}
	}

