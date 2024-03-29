package com.Defis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Defis.domain.Agent;
import com.Defis.exception.AgentNotFoundException;
import com.Defis.exporter.AgentCsvExporter;
import com.Defis.exporter.AgentExcelExporter;
import com.Defis.exporter.AgentPDFExporter;
import com.Defis.service.AgentService;
import com.Defis.utility.FileUploadUtil;

@Controller
public class AgentController {
	@Autowired
	private AgentService service;
	
	
	
	@GetMapping("/agents")
	public String listFirstPage(Model model) {
		
		return listByPage(1, model, "id", "asc", null);
	}
	
	@GetMapping("/agents/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword
			
			) {
		System.out.println("Sort Field: " + sortField);
		System.out.println("Sort Order: " + sortDir);
		
		Page<Agent> page = service.listByPage(pageNum, sortField, sortDir, keyword);
		List<Agent> listAgents = page.getContent();
		
		long startCount = (pageNum - 1) * AgentService.AGENTS_PER_PAGE + 1;
		long endCount = startCount + AgentService.AGENTS_PER_PAGE - 1;		
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listAgents", listAgents);	
		model.addAttribute("sortField", sortField);	
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);
		
		
		return "agents/agents";
	}
	
	@GetMapping("/agents/new")
	public String newAgent(Model model) {	
		
		Agent agent = new Agent();
		
		model.addAttribute("agent", agent);

		model.addAttribute("pageTitle", "Create New Agent");
		
		return "agents/agent_form";
	}
	
	@PostMapping("/agents/save")
	public String saveAgent(Agent agent, 
			RedirectAttributes redirectAttributes,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		
		if(!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			agent.setPhotos(fileName);
			
			Agent savedAgent = service.save(agent);
			
			String uploadDir = "agent-photos/" + savedAgent.getId();
			
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}else {
			if (agent.getPhotos().isEmpty()) agent.setPhotos(null);
			service.save(agent);
		}
				
		redirectAttributes.addFlashAttribute("message", "The agent has been saved successfully!");
		
		return getRedirectURLToAffectedAgent(agent);
	}

	private String getRedirectURLToAffectedAgent(Agent agent) {
		Integer agentId = agent.getId();
		return "redirect:/agents/page/1?sortField=id&sortDir=asc&keyword=" + agentId;
	}
	
	@GetMapping("/agents/edit/{id}")
	public String editAgent(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		Agent agent = service.get(id);
		
		model.addAttribute("agent", agent);
		model.addAttribute("pageTitle", "Edit Agent (ID: " + id + ")");
		
		return "agents/agent_form";
		
		} catch (AgentNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/agents";
		}
		
	}
	
	
	@GetMapping("/agents/view/{id}")
	public String viewAgent(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		Agent agent = service.get(id);
		
		model.addAttribute("agent", agent);
		model.addAttribute("pageTitle", "View Agent (ID: " + id + ")");
		
		return "agents/agentprofile";
		
		} catch (AgentNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/agents";
		}
		
	}
	
	
	@GetMapping("/agents/delete/{id}")
	public String deleteAgent(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		service.delete(id);	
		
		redirectAttributes.addFlashAttribute("message", "The agent ID" + id + " has been deleted successfully!");
		
		
		} catch (AgentNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
		}

		return "redirect:/agents";
		
	}
	

	
	@GetMapping("/agents/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		List<Agent> listAgents = service.listAll();
		AgentCsvExporter exporter = new AgentCsvExporter();
		exporter.export(listAgents, response);
	}
	
	@GetMapping("/agents/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		List<Agent> listAgents = service.listAll();
		AgentExcelExporter exporter = new AgentExcelExporter();
		exporter.export(listAgents, response);
	}
	
	@GetMapping("/agents/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws IOException {
		List<Agent> listAgents = service.listAll();
		AgentPDFExporter exporter = new AgentPDFExporter();
		exporter.export(listAgents, response);
	}
}
