package com.Defis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Defis.domain.Customer;

@Service
public interface CustomerService {
	List<Customer> findAll ();
	
	Customer findOne(Long id);

	List<Customer> findByCategory(String age);

	List<Customer> blurrySearch(String identification_no);
}
