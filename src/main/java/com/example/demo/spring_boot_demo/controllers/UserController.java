package com.example.demo.spring_boot_demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.spring_boot_demo.entity.UserEntity;
import com.example.demo.spring_boot_demo.service.UserServices;



@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins ="*")
public class UserController {
	
	@Autowired
	private UserServices userServices;
	
//	@GetMapping
//	public String getUsers() {
//		return "Hello Welcome";
//	}


	@GetMapping
	public ResponseEntity<List <UserEntity>> getAllUsers(){
		List <UserEntity> users =userServices.getAllUsers();
		System.out.println(users);
		return new ResponseEntity<>(users,HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable Long id){
		
		Optional<UserEntity> user = userServices.getUserById(id);
		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@PostMapping
	public ResponseEntity<?> createUser(@Validated @RequestBody UserEntity userEntity ){
		
		try {
			UserEntity createUser = userServices.createUser(userEntity);
			
			return new ResponseEntity<>(createUser,HttpStatus.CREATED);
			
		}catch (RuntimeException e) {
						
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	
}
