package com.Defis.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Defis.domain.Customer;
import com.Defis.repository.CustomerRepository;
import com.Defis.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> findAll() {
		List<Customer> customerList = (List<Customer>) customerRepository.findAll();
		List<Customer> activeCustomerList = new ArrayList();
		
		for (Customer customer: customerList) {
			
			activeCustomerList.add(customer);
		
	}
		
		return activeCustomerList;
	}
	
	


	@Override
	public Customer findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Customer> findByCategory(String age) {
List<Customer> customerList = customerRepository.findByAge(age);
		
		List<Customer> activeCustomerList = new ArrayList<>();
		
		for (Customer customer: customerList) {
			
				activeCustomerList.add(customer);
			
		}
		
		return activeCustomerList;
	}


	@Override
	public List<Customer> blurrySearch(String identification) {
		List<Customer> customerList = customerRepository.findByIdentification(identification);
		List<Customer> activeCustomerList = new ArrayList<>();
		
		for (Customer customer: customerList) {
			
				activeCustomerList.add(customer);
			
		}
		
		return activeCustomerList;
	}

}
