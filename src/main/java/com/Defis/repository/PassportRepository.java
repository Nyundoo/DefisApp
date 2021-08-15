package com.Defis.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.Defis.domain.Medical;
import com.Defis.domain.Passport;

public interface PassportRepository extends PagingAndSortingRepository<Passport, Integer> {
	
public Long countById(Integer id);	

@Query("SELECT u FROM Passport u WHERE u.user3.id=?#{ principal.id }")
public List<Passport> getUserById(@Param("id") long id);

@Query("SELECT u FROM Passport u")
public List<Passport> getViewById(@Param("id") long id);
	
	@Query("SELECT u FROM Passport u WHERE CONCAT(u.id, ' ',u.applicant, ' ',u.passport_no, ' ',u.pass_paid) LIKE %?1%")
	public Page<Passport> findAll(String keyword, Pageable pageable);

	
}
