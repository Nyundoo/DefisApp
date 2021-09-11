package com.Defis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Defis.domain.Job;

public interface JobRepository extends PagingAndSortingRepository<Job, Integer> {
	
	public Long countById(Integer id);	
	
	@Query("SELECT u FROM Job u WHERE CONCAT(u.id, ' ',u.country, ' ',u.jobTitle, ' ',u.payment, ' ',u.jobDescription, ' ',u.qualification, ' ',u.interviewDate, ' ',u.noVacancy, ' ',u.datePosted) LIKE %?1%")
	public Page<Job> findAll(String keyword, Pageable pageable);
		

}
