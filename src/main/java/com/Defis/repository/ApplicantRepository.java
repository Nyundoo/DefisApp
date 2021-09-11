package com.Defis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.Defis.domain.Applicant;

public interface ApplicantRepository extends PagingAndSortingRepository<Applicant, Integer> {
	@Query("SELECT count(u.id) FROM Applicant u")
	long countById ();
	
	@Query("SELECT count(u.applicant) FROM Birth u WHERE u.status=false")
	long countById2 ();
	
	@Query("SELECT count(u.applicant) FROM Passport u WHERE u.status=false")
	long countById3 ();
	
	@Query("SELECT count(u.applicant) FROM Visa u WHERE u.status=false")
	long countById4 ();
	
	@Query("SELECT count(u.applicant) FROM Training u WHERE u.t_status=false")
	long countById5 ();
	
	@Query("SELECT count(u.id) FROM Jobs u")
	long countById7 ();
	
	@Query("SELECT count(u.applicant) FROM Ticket u WHERE u.travel_status=false")
	long countById6 ();
	
	@Query("SELECT count(u.applicant) FROM Medical u WHERE u.m_status=false")
	long countById8 ();
	
	@Query("SELECT u FROM Applicant u WHERE u.email = :email")
	public Applicant getApplicantByEmail(@Param("email") String email);
	
	public Long countById(Integer id);	
	
	@Query("SELECT u FROM Applicant u WHERE CONCAT(u.id, ' ',u.email, ' ',u.firstName, ' ',u.lastName) LIKE %?1%")
	public Page<Applicant> findAll(String keyword, Pageable pageable);
	
	
}
