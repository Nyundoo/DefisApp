package com.Defis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Defis.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
