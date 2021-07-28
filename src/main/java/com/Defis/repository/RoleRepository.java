package com.Defis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Defis.domain.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{

}
