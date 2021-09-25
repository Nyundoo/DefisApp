package com.Defis.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.Defis.domain.Task;
import com.Defis.domain.Ticket;
import com.Defis.domain.Task;

public interface TasKRepository extends PagingAndSortingRepository<Task, Integer> {
	
	public Long countById(Integer id);	
	
	@Query("SELECT u FROM Task u WHERE CONCAT(u.id, ' ',u.createTask, ' ',u.createdAt, ' ',u.deadlineDate) LIKE %?1%")
	public Page<Task> findAll(String keyword, Pageable pageable);
	
	
}
