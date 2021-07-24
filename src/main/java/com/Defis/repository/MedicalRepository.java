package com.Defis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Defis.domain.Medical;

@Repository
public interface MedicalRepository extends JpaRepository<Medical, Integer> {

}
