package com.Defis.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Defis.domain.Medical;

@Repository
public interface MedicalRepository extends PagingAndSortingRepository<Medical, Integer> {
	
public Long countById(Integer id);	

@Query("SELECT u FROM Medical u WHERE u.user3.id=?#{ principal.id }")
public List<Medical> getUserById(@Param("id") long id);
	
	@Query("SELECT u FROM Medical u WHERE CONCAT(u.id, ' ',u.client_info, ' ',u.medical_center, ' ',u.medical_type, ' ',u.cert_no, ' ',u.cert_application_date, ' ',u.passport_no) LIKE %?1%")
	public Page<Medical> findAll(String keyword, Pageable pageable);

	
}
