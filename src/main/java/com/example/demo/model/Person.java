package com.example.demo.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.annotation.Alpha;
import com.example.demo.annotation.CheckCase;
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
public class Person {

	@NotNull
	@Size(max=10, message="Name should have less than 10 charaters.")
	@Alpha
	private String name;
	
    @NotNull
    @Size(min = 2, max = 14)
    @CheckCase(CaseMode.UPPER)
    private String licensePlate;
}
