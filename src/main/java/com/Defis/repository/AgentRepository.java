package com.Defis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Defis.domain.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {

}
               