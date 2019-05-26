package com.course.microservices.controller;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.course.microservices.dao.UserDao;
import com.course.microservices.exception.UserNotFoundException;
import com.course.microservices.model.User;

@RestController
public class UserController {

	@Autowired
	private UserDao userDao; 
	
	@GetMapping(path="/users")
	public List<User> getAllUsers(){
		return userDao.findAll();  
	}
	
	@GetMapping(path="/users/{id}")
	public User getUserById(@PathVariable int id){
		User user = userDao.findOne(id);
		if(user == null)
			throw new UserNotFoundException(" for id "+id);
		return user;  
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user){
		User savedUser = userDao.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).body(savedUser);
	}
	
	
	
}
