package com.example.demo.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dao.UserDaoService;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;

@RestController
public class UserController {

	@Autowired
	private UserDaoService service;
	
	// retrieveAllUsers
	// GET /users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	// retrieveUser(int id)
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}
	
	// input - details of user
	// output - created & return the created uri
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		return ResponseEntity.noContent().build();
	}
}
