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

import com.Defis.domain.Applicant;
import com.Defis.domain.User;
import com.Defis.domain.Visa;
import com.Defis.exception.VisaNotFoundException;
import com.Defis.exporter.VisaCsvExporter;
import com.Defis.exporter.VisaExcelExporter;
import com.Defis.exporter.VisaPDFExporter;
import com.Defis.repository.ApplicantRepository;
import com.Defis.repository.UserRepository;
import com.Defis.service.VisaService;
import com.Defis.utility.FileUploadUtil;

@Controller
public class VisaController {

	@Autowired
	private VisaService service;
	
	@Autowired
	private ApplicantRepository applicantRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/visas")
	public String listFirstPage(Model model) {
		
		return listByPage(1, model, "applicant", "asc", null);
	}
	
	@GetMapping("/visas/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword
			
			) {
		System.out.println("Sort Field: " + sortField);
		System.out.println("Sort Order: " + sortDir);
		
		Page<Visa> page = service.listByPage(pageNum, sortField, sortDir, keyword);
		List<Visa> listVisas = page.getContent();
		
		long startCount = (pageNum - 1) * VisaService.VISAS_PER_PAGE + 1;
		long endCount = startCount + VisaService.VISAS_PER_PAGE - 1;		
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listVisas", listVisas);	
		model.addAttribute("sortField", sortField);	
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);
		
		
		return "visas/visas";
	}
	
	@GetMapping("/visas/new")
	public String newVisa(Model model) {
		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		List<User> listUsers = (List<User>) userRepo.findAll();
		
		Visa visa = new Visa();
		model.addAttribute("visa", visa);
		model.addAttribute("pageTitle", "Create New Visa");

		model.addAttribute("listApplicants", listApplicants);
		model.addAttribute("listUsers", listUsers);
		
		return "visas/visa_form";
	}
	
	@PostMapping("/visas/save")
	public String saveJob(@ModelAttribute("applicant") Visa visa, 
			RedirectAttributes redirectAttributes,
			@RequestParam("image") MultipartFile multipartFile, 
			HttpServletRequest request) throws IOException {
		
		if(!multipartFile.isEmpty()) {	
			
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			visa.setPhotos(fileName);
			
			Visa savedVisa = service.save(visa);
			
			String uploadDir = "visa-photos/" + savedVisa.getId();
			
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}else {				
			
			
			if (visa.getPhotos().isEmpty()) visa.setPhotos(null);
			
			
			
			service.save(visa);
		}
				
		redirectAttributes.addFlashAttribute("message", "The job has been saved successfully!");
		
		return getRedirectURLToAffectedVisa(visa);
	}

	private String getRedirectURLToAffectedVisa(Visa visa) {
		Integer visaId = visa.getId();
		return "redirect:/visas/page/1?sortField=id&sortDir=asc&keyword=" + visaId;
	}
	
	@GetMapping("/visas/edit/{id}")
	public String editVisa(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		Visa visa = service.get(id);
		
		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		List<User> listUsers = (List<User>) userRepo.findAll();
		

		model.addAttribute("listApplicants", listApplicants);
		model.addAttribute("listUsers", listUsers);
		
		model.addAttribute("visa", visa);
		model.addAttribute("pageTitle", "Edit Visa (ID: " + id + ")");
		
		return "visas/visa_form";
		
		} catch (VisaNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/visas";
		}
		
	}
	
	
	@GetMapping("/visas/view/{id}")
	public String viewVisa(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		Visa visa = service.get(id);
		
		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		List<User> listUsers = (List<User>) userRepo.findAll();
		

		model.addAttribute("listApplicants", listApplicants);
		model.addAttribute("listUsers", listUsers);
		
		model.addAttribute("visa", visa);
		model.addAttribute("pageTitle", "View Visa (ID: " + id + ")");
		
		return "visas/visapreview";
		
		} catch (VisaNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/visas";
		}
		
	}
	
	
	@GetMapping("/visas/delete/{id}")
	public String deleteVisa(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		service.delete(id);	
		
		redirectAttributes.addFlashAttribute("message", "The visa ID" + id + " has been deleted successfully!");
		
		
		} catch (VisaNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
		}

		return "redirect:/visas";
		
	}
	
	@GetMapping("/visas/{id}/enabled/{status}")
	public String updateVisaEnabledStatus(@PathVariable("id") Integer id, 
			@PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) {
		String status =  enabled ? "enabled" : "disabled";
		String message = "The visa ID" + id + " has been " + status;
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return "redirect:/visas";
	}
	
	@GetMapping("/visas/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		List<Visa> listVisas = service.listAll();
		VisaCsvExporter exporter = new VisaCsvExporter();
		exporter.export(listVisas, response);
	}
	
	@GetMapping("/visas/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		List<Visa> listVisas = service.listAll();
		VisaExcelExporter exporter = new VisaExcelExporter();
		exporter.export(listVisas, response);
	}
	
	@GetMapping("/visas/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws IOException {
		List<Visa> listVisas = service.listAll();
		VisaPDFExporter exporter = new VisaPDFExporter();
		exporter.export(listVisas, response);
	}
}
