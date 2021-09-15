package com.Defis.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Defis.domain.Agent;
import com.Defis.exception.AgentNotFoundException;
import com.Defis.repository.AgentRepository;


@Service
@Transactional
public class AgentService {
public static final int AGENTS_PER_PAGE = 15;

	
	@Autowired
	private AgentRepository agentRepo;
	

	public Agent getByEmail(String email) {
		return agentRepo.getAgentByEmail(email);
	}
	
	public List<Agent> listAll()	{
		return (List<Agent>) agentRepo.findAll(Sort.by("firstName").ascending());
		
	}
	
	public Page<Agent> listByPage(int pageNum, String sortField, String sortDir, String keyword){
			Sort sort = Sort.by(sortField);
		
			sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		
			Pageable pageable = PageRequest.of(pageNum - 1, AGENTS_PER_PAGE, sort);
		
		if(keyword != null) {
			return agentRepo.findAll(keyword, pageable);
		}
		
		return agentRepo.findAll(pageable);
	}
	

	public Agent save(Agent agent) {		
	
		return agentRepo.save(agent);
		
	}
	
	public boolean isEmailUnique(Integer id, String email) {
		Agent agentByEmail = agentRepo.getAgentByEmail(email);
		
		if(agentByEmail == null) return true;
		
		boolean isCreatingNew = (id == null);
		
		if(isCreatingNew) {
			if(agentByEmail != null) return false;
		}else {
			if(agentByEmail.getId() != id) {
				return false;
			}
		}
		
		return true;
	}

	public Agent get(Integer id) throws AgentNotFoundException {
		try {
			return agentRepo.findById(id).get();
		}catch (NoSuchElementException ex) {
			throw new AgentNotFoundException("Could not find any agent with ID " + id);
		}
	}
	
	public void delete(Integer id) throws AgentNotFoundException {
		Long countById = agentRepo.countById(id);
		if(countById == null || countById == 0) {
			throw new AgentNotFoundException("Could not find any agent with ID " + id);
		}
		
		agentRepo.deleteById(id);
	}
	

}


