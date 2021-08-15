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

@Query("SELECT u FROM Visa u WHERE u.user4.id=?#{ principal.id  }")
public List<Visa> getUserById(@Param("id") long id);

@Query("SELECT u FROM Visa u")
public List<Visa> getViewById(@Param("id") long id);
	
	@Query("SELECT u FROM Visa u WHERE CONCAT(u.id, ' ',u.type_of_visa, ' ',u.user4) LIKE %?1%")
	public Page<Visa> findAll(String keyword, Pageable pageable);

	
}
