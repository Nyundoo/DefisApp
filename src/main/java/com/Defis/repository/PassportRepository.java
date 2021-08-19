package com.Defis.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.Defis.domain.Birth;
import com.Defis.domain.Medical;
import com.Defis.domain.Passport;

public interface PassportRepository extends PagingAndSortingRepository<Passport, Integer> {
	
public Long countById(Integer id);	

@Query("SELECT u FROM Passport u WHERE (u.user3.id=?#{ principal.id } AND u.status = false) OR (u.user3.id=?#{ principal.id } AND u.status = true AND year(u.pass_reception_date) - year(CURRENT_DATE) = 0 AND month(u.pass_reception_date) - month(CURRENT_DATE) = 0 AND day(u.pass_reception_date) - day(CURRENT_DATE) BETWEEN -1 AND 1)")
public List<Passport> getUserById(@Param("id") long id);

@Query("SELECT u FROM Passport u WHERE u.status = false OR (u.status = true AND year(u.pass_reception_date) - year(CURRENT_DATE) = 0 AND month(u.pass_reception_date) - month(CURRENT_DATE) = 0 AND day(u.pass_reception_date) - day(CURRENT_DATE) BETWEEN -1 AND 1)")
public List<Passport> getViewById(@Param("id") long id);
	
	@Query("SELECT u FROM Passport u WHERE CONCAT(u.id, ' ',u.applicant, ' ',u.passport_no, ' ',u.pass_paid) LIKE %?1%")
	public Page<Passport> findAll(String keyword, Pageable pageable);

	
}
