package com.example.demo.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				 .securitySchemes(Collections.singletonList(new ApiKey("JWT", "Authorization", "header")))
	                .securityContexts(Collections.singletonList(
	                        SecurityContext.builder()
	                                .securityReferences(
	                                        Collections.singletonList(SecurityReference.builder()
	                                                .reference("JWT")
	                                                .scopes(new AuthorizationScope[0])
	                                                .build()
	                                        )
	                                )
	                                .build())
	                )
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
				.paths(PathSelectors.any())
				.build().apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Bon management")
				.description("Bon management")
				.license("audaxis licence")
				.version("1.0.0")
				.build();
		        
	}
	
	
}
