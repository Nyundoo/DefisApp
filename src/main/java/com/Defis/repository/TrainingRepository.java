package com.Defis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.Defis.domain.Training;

public interface TrainingRepository extends PagingAndSortingRepository<Training, Integer> {
public Long countById(Integer id);	

@Query("SELECT u FROM Training u WHERE u.applicant = :applicant")
public Training getTrainingById(@Param("applicant") Integer applicant);
	
	@Query("SELECT u FROM Training u WHERE CONCAT(u.applicant, ' ',u.start_date, ' ',u.finish_date, ' ',u.cert_no) LIKE %?1%")
	public Page<Training> findAll(String keyword, Pageable pageable);
}
