package com.Defis.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Defis.domain.Medical;
import com.Defis.repository.AgentRepository;
import com.Defis.repository.ApplicantRepository;
import com.Defis.repository.UserRepository;
import com.Defis.service.MedicalService;

@Controller
public class MainController {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MedicalService service;
	
	@Autowired
	private AgentRepository agentRepo;
	
	@Autowired
	private ApplicantRepository applicantRepo;

	
	@GetMapping("/")
	public String viewHomePage(@AuthenticationPrincipal Principal principal, Model model) {	
		
		
		List<Medical> listMedicals =  service.listAll();
		
		   model.addAttribute("listMedicals",listMedicals);

		model.addAttribute("agent", agentRepo.countById());
		model.addAttribute("applicant", applicantRepo.countById());
		model.addAttribute("applicant2", applicantRepo.countById2());
		model.addAttribute("applicant3", applicantRepo.countById3());
		model.addAttribute("applicant4", applicantRepo.countById4());
		model.addAttribute("applicant5", applicantRepo.countById5());
		model.addAttribute("applicant6", applicantRepo.countById6());
		model.addAttribute("applicant7", applicantRepo.countById7());
		model.addAttribute("applicant8", applicantRepo.countById8());
		
		
			
		return "index";
	}
	
	@GetMapping("/login")
	public String viewLoginPage() {
		return "login";
	}
	
	
}
