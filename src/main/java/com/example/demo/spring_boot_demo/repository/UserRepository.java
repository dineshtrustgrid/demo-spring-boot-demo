package com.example.demo.spring_boot_demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.spring_boot_demo.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	boolean existsByEmail(String email);

	Optional<UserEntity> findByUsername(String username);

}
