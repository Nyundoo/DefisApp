package com.Defis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Defis.domain.Birth;
import com.Defis.domain.Medical;
import com.Defis.domain.Passport;
import com.Defis.domain.Ticket;
import com.Defis.domain.Training;
import com.Defis.domain.security.NyundooUserDetails;
import com.Defis.repository.AgentRepository;
import com.Defis.repository.ApplicantRepository;
import com.Defis.repository.UserRepository;
import com.Defis.service.BirthService;
import com.Defis.service.MedicalService;
import com.Defis.service.PassportService;
import com.Defis.service.TicketService;
import com.Defis.service.TrainingService;

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
	
	@Autowired
	private MedicalService medicalRepo;
	
	@Autowired
	private BirthService birthRepo;
	
	@Autowired
	private PassportService passportRepo;
	
	@Autowired
	private TicketService ticketRepo;
	
	@Autowired
	private TrainingService trainingRepo;

	
	@GetMapping("/")
	public String viewHomePage(@AuthenticationPrincipal NyundooUserDetails loggedUser, Model model) {	
		
	
		Long id = loggedUser.getId();
		

		List<Medical> listMedicals =  medicalRepo.getById(id);
		List<Birth> listBirths =  birthRepo.getById(id);
		List<Passport> listPassports =  passportRepo.getById(id);
		List<Ticket> listTickets =  ticketRepo.getById(id);
		List<Training> listTrainings =  trainingRepo.getById(id);
		
		  model.addAttribute("listMedicals",listMedicals);
		  model.addAttribute("listBirths",listBirths);
		  model.addAttribute("listPassports",listPassports);
		  model.addAttribute("listTickets",listTickets);
		  model.addAttribute("listTrainings",listTrainings);


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
	
	@GetMapping("/tasks")
	public String viewTask(@AuthenticationPrincipal NyundooUserDetails loggedUser, Model model) {	
		
	
		Long id = loggedUser.getId();
		

		List<Medical> listMedicals =  medicalRepo.getByIdView(id);
		List<Birth> listBirths =  birthRepo.getByIdView(id);
		List<Passport> listPassports =  passportRepo.getByIdView(id);
		List<Ticket> listTickets =  ticketRepo.getByIdView(id);
		List<Training> listTrainings =  trainingRepo.getByIdView(id);
		
		  model.addAttribute("listMedicals",listMedicals);
		  model.addAttribute("listBirths",listBirths);
		  model.addAttribute("listPassports",listPassports);
		  model.addAttribute("listTickets",listTickets);
		  model.addAttribute("listTrainings",listTrainings);


		model.addAttribute("agent", agentRepo.countById());
		model.addAttribute("applicant", applicantRepo.countById());
		model.addAttribute("applicant2", applicantRepo.countById2());
		model.addAttribute("applicant3", applicantRepo.countById3());
		model.addAttribute("applicant4", applicantRepo.countById4());
		model.addAttribute("applicant5", applicantRepo.countById5());
		model.addAttribute("applicant6", applicantRepo.countById6());
		model.addAttribute("applicant7", applicantRepo.countById7());
		model.addAttribute("applicant8", applicantRepo.countById8());
		
		
		return "tasks";
  
	}
	
	
	@GetMapping("/index")
	public String listFirstPage(Model model) {
		
		return listByPage(1, model, "applicant", "asc", null);
	}
	
	@GetMapping("/index/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword
			
			) {
		System.out.println("Sort Field: " + sortField);
		System.out.println("Sort Order: " + sortDir);
		
		Page<Medical> page = service.listByPage(pageNum, sortField, sortDir, keyword);
		List<Medical> listMedicals = page.getContent();
		
		long startCount = (pageNum - 1) * MedicalService.MEDICALS_PER_PAGE + 1;
		long endCount = startCount + MedicalService.MEDICALS_PER_PAGE - 1;		
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listMedicals", listMedicals);	
		model.addAttribute("sortField", sortField);	
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);
		
		
		return "index";
	}
	
	@GetMapping("/login")
	public String viewLoginPage() {
		return "login";
	}
	

}
