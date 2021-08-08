package com.Defis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Defis.domain.Medical;

public interface MedicalRepository extends PagingAndSortingRepository<Medical, Integer> {
public Long countById(Integer id);	
	
	@Query("SELECT u FROM Medical u WHERE CONCAT(u.id, ' ',u.client_info, ' ',u.medical_center, ' ',u.medical_type, ' ',u.cert_no, ' ',u.cert_application_date, ' ',u.passport_no) LIKE %?1%")
	public Page<Medical> findAll(String keyword, Pageable pageable);
}
