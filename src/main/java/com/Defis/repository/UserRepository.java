package com.Defis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.Defis.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	User findByFirstName(String firstName);
	
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User getUserByEmail(@Param("email") String email);
	
	@Query("SELECT u FROM User u WHERE u.id = :id")
	public User getUserById(@Param("id") String id);
	
	public Long countById(Long id);	
	
	@Query("SELECT u FROM User u WHERE CONCAT(u.id, ' ',u.email, ' ',u.firstName, ' ',u.lastName) LIKE %?1%")
	public Page<User> findAll(String keyword, Pageable pageable);
		
	@Query("UPDATE User u SET u.enabled = ?2 WHERE u.id = ?1")
	@Modifying
	public void updateEnabledStatus(Integer id, boolean enabled);
	
}
