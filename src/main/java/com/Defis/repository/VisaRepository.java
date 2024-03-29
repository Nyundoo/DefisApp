package com.Defis.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.Defis.domain.Visa;

public interface VisaRepository extends PagingAndSortingRepository<Visa, Integer> {

	
public Long countById(Integer id);	

@Query("SELECT u FROM Visa u WHERE (u.user4.id=?#{ principal.id } AND u.status = false) OR (u.user4.id=?#{ principal.id } AND u.status = true AND year(u.visa_reception_date) - year(CURRENT_DATE) = 0 AND month(u.visa_reception_date) - month(CURRENT_DATE) = 0 AND day(u.visa_reception_date) - day(CURRENT_DATE) BETWEEN -1 AND 1)")
public List<Visa> getUserById(@Param("id") long id);

@Query("SELECT u FROM Visa u WHERE u.status = false OR (u.status = true AND year(u.visa_reception_date) - year(CURRENT_DATE) = 0 AND month(u.visa_reception_date) - month(CURRENT_DATE) = 0 AND day(u.visa_reception_date) - day(CURRENT_DATE) BETWEEN -1 AND 1)")
public List<Visa> getViewById(@Param("id") long id);
	
	@Query("SELECT u FROM Visa u WHERE CONCAT(u.id, ' ',u.type_of_visa, ' ',u.user4) LIKE %?1%")
	public Page<Visa> findAll(String keyword, Pageable pageable);

	
}
