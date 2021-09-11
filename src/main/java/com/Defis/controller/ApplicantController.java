package com.Defis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Defis.domain.Agent;
import com.Defis.domain.Applicant;
import com.Defis.domain.Job;
import com.Defis.domain.Jobs;
import com.Defis.domain.Role;
import com.Defis.exception.ApplicantNotFoundException;
import com.Defis.exporter.ApplicantCsvExporter;
import com.Defis.exporter.ApplicantExcelExporter;
import com.Defis.exporter.ApplicantPDFExporter;
import com.Defis.repository.AgentRepository;
import com.Defis.repository.JobRepository;
import com.Defis.service.ApplicantService;
import com.Defis.utility.FileUploadUtil;

@Controller
public class ApplicantController {
	@Autowired
	private ApplicantService service;
	
	@Autowired
	private AgentRepository agentRepo;
	
	@Autowired
	private JobRepository jobRepo;
		
	@GetMapping("/applicants")
	public String listFirstPage(Model model) {
		
		return listByPage(1, model, "firstName", "asc", null);
	}
	
	@GetMapping("/applicants/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword
			
			) {
		System.out.println("Sort Field: " + sortField);
		System.out.println("Sort Order: " + sortDir);
		
		Page<Applicant> page = service.listByPage(pageNum, sortField, sortDir, keyword);
		List<Applicant> listApplicants = page.getContent();
		
		long startCount = (pageNum - 1) * ApplicantService.APPLICANTS_PER_PAGE + 1;
		long endCount = startCount + ApplicantService.APPLICANTS_PER_PAGE - 1;		
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listApplicants", listApplicants);	
		model.addAttribute("sortField", sortField);	
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);
		
		
		return "applicants/applicants";
	}
	
	@GetMapping("/applicants/new")
	public String newApplicant(Model model) {	
		List<Agent> listAgents = (List<Agent>) agentRepo.findAll();
		List<Job> listJobs = (List<Job>) jobRepo.findAll();
		List<Jobs> listJobss = service.listJobss();
		
		Applicant applicants = new Applicant();
		
		model.addAttribute("applicant", applicants);

		model.addAttribute("pageTitle", "Create New Applicant");
		
		model.addAttribute("listAgents", listAgents);
		
		model.addAttribute("listJobs", listJobs);
		
		model.addAttribute("listJobss", listJobss);
		
		return "applicants/applicant_form";
	}
	
	@PostMapping("/applicants/save")
	public String saveApplicant(@ModelAttribute("applicant") Applicant applicant, 
			RedirectAttributes redirectAttributes,
			@RequestParam("image") MultipartFile multipartFile, 
			HttpServletRequest request) throws IOException {
		
		if(!multipartFile.isEmpty()) {
			
			String[] detailIDs = request.getParameterValues("detailID");
			String[] detailCnames = request.getParameterValues("detailCname");
			String[] detailCcontacts = request.getParameterValues("detailCcontact");
			String[] detailCnationalIds = request.getParameterValues("detailCnationalId");
			String[] detailCrelationships = request.getParameterValues("detailCrelationship");
			String[] detailCcurrent_residences = request.getParameterValues("detailCcurrent_residence");
			
			for (int i  = 0; i < detailCnames.length; i++) {
				if(detailIDs != null && detailIDs.length > 0) {
					applicant.setDetail(Integer.valueOf(detailIDs[i]), detailCnames[i], Integer.valueOf(detailCcontacts[i]), Integer.valueOf(detailCnationalIds[i]), detailCrelationships[i], detailCcurrent_residences[i]);
				}else {
					applicant.addDetail(detailCnames[i], Integer.valueOf(detailCcontacts[i]), Integer.valueOf(detailCnationalIds[i]), detailCrelationships[i], detailCcurrent_residences[i]);
				}
			}	
			
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			applicant.setPhotos(fileName);
			
			Applicant savedApplicant = service.save(applicant);
			
			String uploadDir = "applicant-photos/" + savedApplicant.getId();
			
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}else {				
			
			
			if (applicant.getPhotos().isEmpty()) applicant.setPhotos(null);
			

			String[] detailIDs = request.getParameterValues("detailID");
			String[] detailCnames = request.getParameterValues("detailCname");
			String[] detailCcontacts = request.getParameterValues("detailCcontact");
			String[] detailCnationalIds = request.getParameterValues("detailCnationalId");
			String[] detailCrelationships = request.getParameterValues("detailCrelationship");
			String[] detailCcurrent_residences = request.getParameterValues("detailCcurrent_residence");
			
			for (int i  = 0; i < detailCnames.length; i++) {
				if(detailIDs != null && detailIDs.length > 0) {
					applicant.setDetail(Integer.valueOf(detailIDs[i]), detailCnames[i], Integer.valueOf(detailCcontacts[i]), Integer.valueOf(detailCnationalIds[i]), detailCrelationships[i], detailCcurrent_residences[i]);
				}else {
					applicant.addDetail(detailCnames[i], Integer.valueOf(detailCcontacts[i]), Integer.valueOf(detailCnationalIds[i]), detailCrelationships[i], detailCcurrent_residences[i]);
				}
			}
			
			service.save(applicant);
		}
				
		redirectAttributes.addFlashAttribute("message", "The applicant has been saved successfully!");
		
		return getRedirectURLToAffectedApplicant(applicant);
	}

	private String getRedirectURLToAffectedApplicant(Applicant applicant) {
		Integer applicantId = applicant.getId();
		return "redirect:/applicants/page/1?sortField=id&sortDir=asc&keyword=" + applicantId;
	}
	
	@GetMapping("/applicants/edit/{id}")
	public String editApplicant(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		Applicant applicant = service.get(id);

		List<Agent> listAgents = (List<Agent>) agentRepo.findAll();
		List<Job> listJobs = (List<Job>) jobRepo.findAll();
		List<Jobs> listJobss = service.listJobss();
		
		model.addAttribute("applicant", applicant);
		model.addAttribute("pageTitle", "Edit Applicant (ID: " + id + ")");		
		
		model.addAttribute("listAgents", listAgents);
		model.addAttribute("listJobs", listJobs);
		model.addAttribute("listJobss", listJobss);
		
		return "applicants/applicant_form";
		
		} catch (ApplicantNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/applicants";
		}
		
	}
	
	
	@GetMapping("/applicants/view/{id}")
	public String viewApplicant(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		Applicant applicant = service.get(id);

		List<Agent> listAgents = (List<Agent>) agentRepo.findAll();
		List<Job> listJobs = (List<Job>) jobRepo.findAll();
		
		model.addAttribute("applicant", applicant);
		model.addAttribute("pageTitle", "View Applicant (ID: " + id + ")");		
		
		model.addAttribute("listAgents", listAgents);
		model.addAttribute("listJobs", listJobs);
		
		return "applicants/applicantprofile";
		
		} catch (ApplicantNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/applicants";
		}
		
	}
	
	
	@GetMapping("/applicants/delete/{id}")
	public String deleteApplicant(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		service.delete(id);	
		
		redirectAttributes.addFlashAttribute("message", "The applicant ID" + id + " has been deleted successfully!");
		
		
		} catch (ApplicantNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
		}

		return "redirect:/applicants";
		
	}
	

	
	@GetMapping("/applicants/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		List<Applicant> listApplicants = service.listAll();
		ApplicantCsvExporter exporter = new ApplicantCsvExporter();
		exporter.export(listApplicants, response);
	}
	
	@GetMapping("/applicants/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		List<Applicant> listApplicants = service.listAll();
		ApplicantExcelExporter exporter = new ApplicantExcelExporter();
		exporter.export(listApplicants, response);
	}
	
	@GetMapping("/applicants/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws IOException {
		List<Applicant> listApplicants = service.listAll();
		ApplicantPDFExporter exporter = new ApplicantPDFExporter();
		exporter.export(listApplicants, response);
	}
}
