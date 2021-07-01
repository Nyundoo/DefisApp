package com.Defis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Defis.domain.Agent;
import com.Defis.repository.AgentRepository;

@Controller
public class AgentController {
	
	@Autowired
	private AgentRepository repo;
	
	@GetMapping("/agents")
	public String listAgents(Model model) {
		List<Agent> listAgents = repo.findAll();
		model.addAttribute("listAgents", listAgents);
		
		return "agents";
	}
	
	@GetMapping("/agents/new")
	public String showAgentNewForm(Model model) {
		model.addAttribute("agent", new Agent());
		
		return "agent_form";
	}
	
	@PostMapping("/agents/save")
	public String saveAgent(Agent agent) {
		repo.save(agent);
		
		return "redirect:/agents";
	}
	
	@GetMapping("/agents/edit/{id}")
	public String listEditAgentForm(@PathVariable("id") Integer id, Model model) {
		Agent agent = repo.findById(id).get();
		model.addAttribute("agent", agent);
		
		List<Agent> listCategories = repo.findAll();
		
		model.addAttribute("listCategories", listCategories);
		
		return "agent_form";
	}
	
	@GetMapping("/agents/delete/{id}")
	public String deleteAgent(@PathVariable("id") Integer id, Model model) {
		repo.deleteById(id);
		
		return "redirect:/agents";
		
	}
}
