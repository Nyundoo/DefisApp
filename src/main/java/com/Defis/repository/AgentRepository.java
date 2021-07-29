package com.Defis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.Defis.domain.Agent;

public interface AgentRepository extends PagingAndSortingRepository<Agent, Integer> {

	@Query("SELECT u FROM Agent u WHERE u.email = :email")
	public Agent getAgentByEmail(@Param("email") String email);
	
	public Long countById(Integer id);	
	
	@Query("SELECT u FROM Agent u WHERE CONCAT(u.id, ' ',u.email, ' ',u.firstName, ' ',u.lastName, ' ',u.phoneNumber1, ' ',u.phoneNumber2, ' ',u.currentResidence, ' ',u.ward, ' ',u.villageName) LIKE %?1%")
	public Page<Agent> findAll(String keyword, Pageable pageable);
		

}
