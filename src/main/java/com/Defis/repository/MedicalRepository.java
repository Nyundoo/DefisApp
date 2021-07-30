package com.Defis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Defis.domain.Agent;
import com.Defis.domain.Medical;

public interface MedicalRepository extends PagingAndSortingRepository<Medical, Integer> {
public Long countById(Integer id);	
	
	@Query("SELECT u FROM Agent u WHERE CONCAT(u.id, ' ',u.clientInfo, ' ',u.medicalCenter, ' ',u.medicalType, ' ',u.amountPaid, ' ',u.certNo, ' ',u.certApplicationDate, ' ',u.assignTo, ' ',u.passportNo, ' ',u.pass_assign_to) LIKE %?1%")
	public Page<Agent> findAll(String keyword, Pageable pageable);
}
