package com.example.demo.spring_boot_demo.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String message) {
		super(message);
		
	}
	
}
