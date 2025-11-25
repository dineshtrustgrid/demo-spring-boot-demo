package com.example.demo.spring_boot_demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.spring_boot_demo.entity.UserEntity;
import com.example.demo.spring_boot_demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServices implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	//get all users
	public List<UserEntity> getAllUsers(){
		return userRepository.findAll();
		
	}
	
	// get user by ID
	
	public Optional<UserEntity> getUserById(Long id) {
		return userRepository.findById(id);
	}
	
	// create
	
	public UserEntity createUser(UserEntity userEntity) {
		if(userRepository.existsByEmail(userEntity.getEmail())) {
			throw new RuntimeException("User eith email "+userEntity.getEmail()+"already exist");
		}
		return userRepository.save(userEntity);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 UserEntity user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"+ username));
		return org.springframework.security.core.userdetails.User
         .withUsername(user.getName())
//         .password(user.getPassword())
//         .roles(user.getRole())   // remove "ROLE_" prefix if present
         .build();
	}

}
