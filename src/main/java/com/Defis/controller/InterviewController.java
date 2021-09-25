package com.Defis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Defis.domain.Applicant;
import com.Defis.domain.Interview;
import com.Defis.domain.User;
import com.Defis.exception.InterviewNotFoundException;
import com.Defis.exporter.InterviewCsvExporter;
import com.Defis.exporter.InterviewExcelExporter;
import com.Defis.repository.ApplicantRepository;
import com.Defis.repository.UserRepository;
import com.Defis.service.InterviewService;

@Controller
public class InterviewController {

	@Autowired
	private InterviewService service;
	
	@Autowired
	private ApplicantRepository applicantRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@GetMapping("/interviews")
	public String listFirstPage(Model model) {
		
		return listByPage(1, model, "applicant", "asc", null);
	}
	
	@GetMapping("/interviews/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword
			
			) {
		System.out.println("Sort Field: " + sortField);
		System.out.println("Sort Order: " + sortDir);
		
		Page<Interview> page = service.listByPage(pageNum, sortField, sortDir, keyword);
		List<Interview> listInterviews = page.getContent();
		
		long startCount = (pageNum - 1) * InterviewService.INTERVIEWS_PER_PAGE + 1;
		long endCount = startCount + InterviewService.INTERVIEWS_PER_PAGE - 1;		
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listInterviews", listInterviews);	
		model.addAttribute("sortField", sortField);	
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);
		
		
		return "interviews/interviews";
	}
	
	@GetMapping("/interviews/new")
	public String newInterview(Model model) {
		
		
		List<User> listUsers = (List<User>) userRepo.findAll();

		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		
		Interview interview = new Interview();
		model.addAttribute("interview", interview);
		model.addAttribute("pageTitle", "Create New Interview");
		model.addAttribute("listUsers", listUsers);

		model.addAttribute("listApplicants", listApplicants);
		
		return "interviews/interview_form";
	}
	
	@PostMapping("/interviews/save")
	public String saveInterview(Interview interview,
			Integer applicant,
			RedirectAttributes redirectAttributes) throws IOException {

		try {
			service.save(interview);
			
			
			redirectAttributes.addFlashAttribute("message", "The interview has been saved successfully!");
			
			return getRedirectURLToAffectedInterview(interview);
			
			} catch (DataIntegrityViolationException ex) {
				redirectAttributes.addFlashAttribute("message", "Trainer with ID " + applicant + " already exist in interview");
				
				return "redirect:/interview";
			}
		
		
	
	}

	private String getRedirectURLToAffectedInterview(Interview interview) {
		Integer interviewId = interview.getId();
		return "redirect:/interview/page/1?sortField=id&sortDir=asc&keyword=" + interviewId;
	}
	
	@GetMapping("/interviews/edit/{id}")
	public String editInterview(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		Interview interview = service.get(id);
		List<User> listUsers = (List<User>) userRepo.findAll();

		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		

		model.addAttribute("listApplicants", listApplicants);
		model.addAttribute("listUsers", listUsers);

		model.addAttribute("interview", interview);
		model.addAttribute("pageTitle", "Edit Interview (ID: " + id + ")");
		
		return "interviews/interview_form";
		
		} catch (InterviewNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/interviews";
		}
		
	}
	
	
	@GetMapping("/interviews/view/{id}")
	public String viewInterview(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		Interview interview = service.get(id);
		List<User> listUsers = (List<User>) userRepo.findAll();

		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		

		model.addAttribute("listApplicants", listApplicants);
		model.addAttribute("listUsers", listUsers);

		model.addAttribute("interview", interview);
		model.addAttribute("pageTitle", "View Interview (ID: " + id + ")");
		
		return "interviews/interviewpreview";
		
		} catch (InterviewNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/interviews";
		}
		
	}
	
	
	@GetMapping("/interviews/delete/{id}")
	public String deleteInterview(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		service.delete(id);	
		
		redirectAttributes.addFlashAttribute("message", "The interview ID" + id + " has been deleted successfully!");
		
		
		} catch (InterviewNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
		}

		return "redirect:/interviews";
		
	}
	
	@GetMapping("/interviews/{id}/enabled/{status}")
	public String updateInterviewEnabledStatus(@PathVariable("id") Integer id, 
			@PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) {
		String status =  enabled ? "enabled" : "disabled";
		String message = "The interview ID" + id + " has been " + status;
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return "redirect:/interviews";
	}
	
	@GetMapping("/interviews/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		List<Interview> listInterviews = service.listAll();
		InterviewCsvExporter exporter = new InterviewCsvExporter();
		exporter.export(listInterviews, response);
	}
	
	@GetMapping("/interviews/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		List<Interview> listInterviews = service.listAll();
		InterviewExcelExporter exporter = new InterviewExcelExporter();
		exporter.export(listInterviews, response);
	}
	

}
