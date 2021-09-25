package com.Defis.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.Defis.domain.Interview;

public interface InterviewRepository extends PagingAndSortingRepository<Interview, Integer> {
public Long countById(Integer id);	

@Query("SELECT u FROM Interview u WHERE (u.user6.id=?#{ principal.id } AND u.i_status = false) OR (u.user6.id=?#{ principal.id } AND u.i_status = true AND year(u.startTime) - year(CURRENT_DATE) = 0 AND month(u.startTime) - month(CURRENT_DATE) = 0 AND day(u.startTime) - day(CURRENT_DATE) BETWEEN -1 AND 1)")
public List<Interview> getUserById(@Param("id") long id);

@Query("SELECT u FROM Interview u WHERE u.i_status = false OR (u.i_status = true AND year(u.startTime) - year(CURRENT_DATE) = 0 AND month(u.startTime) - month(CURRENT_DATE) = 0 AND day(u.startTime) - day(CURRENT_DATE) BETWEEN -1 AND 1)")
public List<Interview> getViewById(@Param("id") long id);
	
	@Query("SELECT u FROM Interview u WHERE CONCAT(u.applicant, ' ',u.startTime, ' ',u.typeOfInterview) LIKE %?1%")
	public Page<Interview> findAll(String keyword, Pageable pageable);
}
