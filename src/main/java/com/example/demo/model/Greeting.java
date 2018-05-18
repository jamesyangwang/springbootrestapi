package com.example.demo.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Greeting {
	@NotNull
	@Min(1)
    private final long id;
	@NotNull
	@Size(max=18, message="Name should have less than 10 charaters.")
    private final String content;
	@NotNull
    private final Quote quote;
}

//https://mvnrepository.com/artifact/org.hibernate/hibernate-validator/6.0.9.Final