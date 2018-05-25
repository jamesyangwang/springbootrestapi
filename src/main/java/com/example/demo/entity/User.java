package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description="All details about the user")
@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2, message="Name should have at least 2 characters")
	@ApiModelProperty(notes="Name should have at least 2 characters")
	private String name;
	
	@Past
	@ApiModelProperty(notes="Birth date should be in the past")
	private Date birthDate;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	public User(int id, String name, Date birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.posts = null;
	}
}

















//Maven Dependencies:
//validation-api-2.0.1.Fianl.jar\javax.validation\constraints
//hibernate-validator-6.0.7.Final.jar\org.hibernate.validator\constraints

