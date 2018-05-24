package com.example.demo.model;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description="All details about the user")
public class User {

	private Integer id;
	
	@Size(min=2, message="Name should have at least 2 characters")
	@ApiModelProperty(notes="Name should have at least 2 characters")
	private String name;
	
	@Past
	@ApiModelProperty(notes="Birth date should be in the past")
	private Date birthDate;
	
}

















//Maven Dependencies:
//validation-api-2.0.1.Fianl.jar\javax.validation\constraints
//hibernate-validator-6.0.7.Final.jar\org.hibernate.validator\constraints

