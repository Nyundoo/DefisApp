package com.Defis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Defis.domain.Payment;

public interface PaymentRepository extends PagingAndSortingRepository<Payment, Integer> {
	
	public Long countById(Integer id);	
	
	@Query("SELECT u FROM Payment u WHERE CONCAT(u.id, ' ',u.applicant, ' ',u.amount_paid, ' ',u.date_paid) LIKE %?1%")
	public Page<Payment> findAll(String keyword, Pageable pageable);
	
	
}
