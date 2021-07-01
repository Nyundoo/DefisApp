package com.Defis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Defis.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
