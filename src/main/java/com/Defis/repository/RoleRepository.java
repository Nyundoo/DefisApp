package com.Defis.repository;

import org.springframework.data.repository.CrudRepository;

import com.Defis.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByname(String name);
}
