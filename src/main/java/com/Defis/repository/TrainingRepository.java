package com.Defis.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.Defis.domain.Training;

public interface TrainingRepository extends PagingAndSortingRepository<Training, Integer> {
public Long countById(Integer id);	

@Query("SELECT u FROM Training u WHERE (u.user5.id=?#{ principal.id } AND u.t_status = false) OR (u.user5.id=?#{ principal.id } AND u.t_status = true AND year(u.finish_date) - year(CURRENT_DATE) = 0 AND month(u.finish_date) - month(CURRENT_DATE) = 0 AND day(u.finish_date) - day(CURRENT_DATE) BETWEEN -1 AND 1)")
public List<Training> getUserById(@Param("id") long id);

@Query("SELECT u FROM Training u WHERE u.t_status = false OR (u.t_status = true AND year(u.finish_date) - year(CURRENT_DATE) = 0 AND month(u.finish_date) - month(CURRENT_DATE) = 0 AND day(u.finish_date) - day(CURRENT_DATE) BETWEEN -1 AND 1)")
public List<Training> getViewById(@Param("id") long id);
	
	@Query("SELECT u FROM Training u WHERE CONCAT(u.applicant, ' ',u.start_date, ' ',u.finish_date, ' ',u.cert_no) LIKE %?1%")
	public Page<Training> findAll(String keyword, Pageable pageable);
}
