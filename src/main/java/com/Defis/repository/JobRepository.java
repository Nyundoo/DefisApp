package com.Defis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Defis.domain.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

}
