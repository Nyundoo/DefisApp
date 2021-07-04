package com.Defis.repository;

import org.springframework.data.repository.CrudRepository;

import com.Defis.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	
	User findByEmail(String email);
}
