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
import com.Defis.domain.Medical;
import com.Defis.domain.MedicalNotFoundException;
import com.Defis.exporter.MedicalCsvExporter;
import com.Defis.exporter.MedicalExcelExporter;
import com.Defis.exporter.MedicalPDFExporter;
import com.Defis.repository.ApplicantRepository;
import com.Defis.service.MedicalService;

@Controller
public class MedicalController {
	@Autowired
	private MedicalService service;
	
	@Autowired
	private ApplicantRepository applicantRepo;
	
	@GetMapping("/medicals")
	public String listFirstPage(Model model) {
		
		return listByPage(1, model, "applicant", "asc", null);
	}
	
	@GetMapping("/medicals/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword
			
			) {
		System.out.println("Sort Field: " + sortField);
		System.out.println("Sort Order: " + sortDir);
		
		Page<Medical> page = service.listByPage(pageNum, sortField, sortDir, keyword);
		List<Medical> listMedicals = page.getContent();
		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		
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
		model.addAttribute("listApplicants", listApplicants);
		model.addAttribute("listMedicals", listMedicals);	
		model.addAttribute("sortField", sortField);	
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);
		
		
		return "medicals/medicals";
	}
	
	@GetMapping("/medicals/new")
	public String newMedical(Model model) {
		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		
		Medical medical = new Medical();
		model.addAttribute("medical", medical);
		model.addAttribute("pageTitle", "Create New Medical");

		model.addAttribute("listApplicants", listApplicants);
		
		return "medicals/medical_form";
	}
	
	@PostMapping("/medicals/save")
	public String saveMedical(Medical medical, 
			RedirectAttributes redirectAttributes) throws IOException {
		
		
		service.save(medical);
	
			
	redirectAttributes.addFlashAttribute("message", "The medical has been saved successfully!");
	
	return getRedirectURLToAffectedMedical(medical);
	}

	private String getRedirectURLToAffectedMedical(Medical medical) {
		Integer medicalId = medical.getId();
		return "redirect:/medicals/page/1?sortField=id&sortDir=asc&keyword=" + medicalId;
	}
	
	@GetMapping("/medicals/edit/{id}")
	public String editMedical(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		Medical medical = service.get(id);
		
		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		

		model.addAttribute("listApplicants", listApplicants);
		
		model.addAttribute("medical", medical);
		model.addAttribute("pageTitle", "Edit Medical (ID: " + id + ")");
		
		return "medicals/medical_form";
		
		} catch (MedicalNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/medicals";
		}
		
	}
	
	
	@GetMapping("/medicals/delete/{id}")
	public String deleteMedical(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		service.delete(id);	
		
		redirectAttributes.addFlashAttribute("message", "The medical ID" + id + " has been deleted successfully!");
		
		
		} catch (MedicalNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
		}

		return "redirect:/medicals";
		
	}
	
	@GetMapping("/medicals/{id}/enabled/{status}")
	public String updateMedicalEnabledStatus(@PathVariable("id") Integer id, 
			@PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) {
		String status =  enabled ? "enabled" : "disabled";
		String message = "The medical ID" + id + " has been " + status;
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return "redirect:/medicals";
	}
	
	@GetMapping("/medicals/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		List<Medical> listMedicals = service.listAll();
		MedicalCsvExporter exporter = new MedicalCsvExporter();
		exporter.export(listMedicals, response);
	}
	
	@GetMapping("/medicals/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		List<Medical> listMedicals = service.listAll();
		MedicalExcelExporter exporter = new MedicalExcelExporter();
		exporter.export(listMedicals, response);
	}
	
	@GetMapping("/medicals/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws IOException {
		List<Medical> listMedicals = service.listAll();
		MedicalPDFExporter exporter = new MedicalPDFExporter();
		exporter.export(listMedicals, response);
	}
}
