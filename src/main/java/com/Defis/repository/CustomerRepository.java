package com.Defis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Defis.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	

	Optional<Customer> findById(Integer id);

	void deleteById(Integer id);

	List<Customer> findByAge(String age);

	List<Customer> findByIdentification(String identification);

}
