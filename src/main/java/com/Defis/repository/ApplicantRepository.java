package com.Defis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.Defis.domain.Applicant;
import com.Defis.domain.User;

public interface ApplicantRepository extends PagingAndSortingRepository<Applicant, Integer> {
	@Query("SELECT u FROM Applicant u WHERE u.email = :email")
	public Applicant getApplicantByEmail(@Param("email") String email);
	
	public Long countById(Integer id);	
	
	@Query("SELECT u FROM Applicant u WHERE CONCAT(u.id, ' ',u.email, ' ',u.firstName, ' ',u.lastName) LIKE %?1%")
	public Page<Applicant> findAll(String keyword, Pageable pageable);
	
	
}
