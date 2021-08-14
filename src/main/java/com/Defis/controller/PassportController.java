package com.Defis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Defis.domain.Applicant;
import com.Defis.domain.Passport;
import com.Defis.domain.User;
import com.Defis.exception.PassportNotFoundException;
import com.Defis.exporter.PassportCsvExporter;
import com.Defis.exporter.PassportExcelExporter;
import com.Defis.exporter.PassportPDFExporter;
import com.Defis.repository.ApplicantRepository;
import com.Defis.repository.UserRepository;
import com.Defis.service.PassportService;

@Controller
public class PassportController {

	@Autowired
	private PassportService service;
	
	@Autowired
	private ApplicantRepository applicantRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/passports")
	public String listFirstPage(Model model) {
		
		return listByPage(1, model, "applicant", "asc", null);
	}
	
	@GetMapping("/passports/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword
			
			) {
		System.out.println("Sort Field: " + sortField);
		System.out.println("Sort Order: " + sortDir);
		
		Page<Passport> page = service.listByPage(pageNum, sortField, sortDir, keyword);
		List<Passport> listPassports = page.getContent();
		
		long startCount = (pageNum - 1) * PassportService.PASSPORTS_PER_PAGE + 1;
		long endCount = startCount + PassportService.PASSPORTS_PER_PAGE - 1;		
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listPassports", listPassports);	
		model.addAttribute("sortField", sortField);	
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);
		
		
		return "passports/passports";
	}
	
	@GetMapping("/passports/new")
	public String newPassport(Model model) {
		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		List<User> listUsers = (List<User>) userRepo.findAll();
		
		Passport passport = new Passport();
		model.addAttribute("passport", passport);
		model.addAttribute("pageTitle", "Create New Passport");

		model.addAttribute("listApplicants", listApplicants);
		model.addAttribute("listUsers", listUsers);
		
		return "passports/passport_form";
	}
	
	@PostMapping("/passports/save")
	public String savePassport(Passport passport,
			Integer applicant,
			RedirectAttributes redirectAttributes) throws IOException {

		
			service.save(passport);
			
			
			redirectAttributes.addFlashAttribute("message", "The ticket has been saved successfully!");
			
			return getRedirectURLToAffectedPassport(passport);		
	
	}

	private String getRedirectURLToAffectedPassport(Passport passport) {
		Integer passportId = passport.getId();
		return "redirect:/passports/page/1?sortField=id&sortDir=asc&keyword=" + passportId;
	}
	
	@GetMapping("/passports/edit/{id}")
	public String editPassport(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		Passport passport = service.get(id);
		
		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		List<User> listUsers = (List<User>) userRepo.findAll();
		

		model.addAttribute("listApplicants", listApplicants);
		model.addAttribute("listUsers", listUsers);
		
		model.addAttribute("passport", passport);
		model.addAttribute("pageTitle", "Edit Passport (ID: " + id + ")");
		
		return "passports/passport_form";
		
		} catch (PassportNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/passports";
		}
		
	}
	
	
	@GetMapping("/passports/delete/{id}")
	public String deletePassport(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		service.delete(id);	
		
		redirectAttributes.addFlashAttribute("message", "The passport ID" + id + " has been deleted successfully!");
		
		
		} catch (PassportNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
		}

		return "redirect:/passports";
		
	}
	
	@GetMapping("/passports/{id}/enabled/{status}")
	public String updatePassportEnabledStatus(@PathVariable("id") Integer id, 
			@PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) {
		String status =  enabled ? "enabled" : "disabled";
		String message = "The passport ID" + id + " has been " + status;
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return "redirect:/passports";
	}
	
	@GetMapping("/passports/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		List<Passport> listPassports = service.listAll();
		PassportCsvExporter exporter = new PassportCsvExporter();
		exporter.export(listPassports, response);
	}
	
	@GetMapping("/passports/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		List<Passport> listPassports = service.listAll();
		PassportExcelExporter exporter = new PassportExcelExporter();
		exporter.export(listPassports, response);
	}
	
	@GetMapping("/passports/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws IOException {
		List<Passport> listPassports = service.listAll();
		PassportPDFExporter exporter = new PassportPDFExporter();
		exporter.export(listPassports, response);
	}
}
