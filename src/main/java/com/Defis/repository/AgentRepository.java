package com.Defis.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.Defis.domain.Agent;

public interface AgentRepository extends PagingAndSortingRepository<Agent, Integer> {

}
