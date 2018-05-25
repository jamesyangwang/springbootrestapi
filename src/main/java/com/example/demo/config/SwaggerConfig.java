package com.example.demo.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final Contact DEFAULT_CONTACT = new Contact("Contact Info", "www.digicert.com", "info@digicert.com");
	@SuppressWarnings("rawtypes")
	private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Api Documentation", "Api Documentation", "1.0",
			"urn:tos",
			DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
			new ArrayList<VendorExtension>());

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(
			Arrays.asList("application/json"));
	// Arrays.asList("application/json", "application/xml"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo"))
				.paths(regex("/users"))
				.build();
	}
}

















// swagger-annotations-1.5.14.jar\io.swagger.annotations\

// http://localhost:8080/swagger-ui.html
// http://localhost:8080/v2/api-docs

// https://dzone.com/articles/spring-boot-restful-api-documentation-with-swagger
// http://www.baeldung.com/swagger-2-documentation-for-spring-rest-api

// https://mvnrepository.com/artifact/io.springfox/springfox-swagger2
// https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui

//https://github.com/Swagger2Markup/swagger2markup
//http://swagger2markup.github.io/swagger2markup/1.3.3/
//https://github.com/Swagger2Markup/swagger2markup-maven-plugin
//https://github.com/atomfrede/generator-jhipster-swagger2markup

//https://stackoverflow.com/questions/30217910/generate-pdf-from-swagger-api-documentation


