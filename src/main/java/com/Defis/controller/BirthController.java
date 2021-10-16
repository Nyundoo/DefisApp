package com.Defis.controller;

import java.io.IOException;
import java.sql.SQLException;
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
import com.Defis.domain.Birth;
import com.Defis.domain.User;
import com.Defis.exception.BirthNotFoundException;
import com.Defis.exporter.BirthCsvExporter;
import com.Defis.exporter.BirthExcelExporter;
import com.Defis.exporter.BirthPDFExporter;
import com.Defis.repository.ApplicantRepository;
import com.Defis.repository.UserRepository;
import com.Defis.service.BirthService;
import com.Defis.utility.FileUploadUtil;

@Controller
public class BirthController {

	@Autowired
	private BirthService service;
	
	@Autowired
	private ApplicantRepository applicantRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/births")
	public String listFirstPage(Model model) {
		
		return listByPage(1, model, "applicant", "asc", null);
	}
	
	@GetMapping("/births/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword
			
			) {
		System.out.println("Sort Field: " + sortField);
		System.out.println("Sort Order: " + sortDir);
		
		Page<Birth> page = service.listByPage(pageNum, sortField, sortDir, keyword);
		List<Birth> listBirths = page.getContent();
		
		long startCount = (pageNum - 1) * BirthService.BIRTHCERTS_PER_PAGE + 1;
		long endCount = startCount + BirthService.BIRTHCERTS_PER_PAGE - 1;		
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listBirths", listBirths);	
		model.addAttribute("sortField", sortField);	
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);
		
		
		return "births/births";
	}
	
	@GetMapping("/births/new")
	public String newBirth(Model model) {
		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		List<User> listUsers = (List<User>) userRepo.findAll();
		
		Birth birthcert = new Birth();
		model.addAttribute("birthcert", birthcert);
		model.addAttribute("pageTitle", "Create New Birth");

		model.addAttribute("listApplicants", listApplicants);
		model.addAttribute("listUsers", listUsers);
		
		return "births/birth_form";
	}
	
	@PostMapping("/births/save")
	public String saveJob(@ModelAttribute("applicant") Birth birthcert, 
			RedirectAttributes redirectAttributes,
			@RequestParam("image") MultipartFile multipartFile, 
			HttpServletRequest request) throws IOException {
		
		if(!multipartFile.isEmpty()) {	
			
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			birthcert.setPhotos(fileName);
			
			Birth savedJob = service.save(birthcert);
			
			String uploadDir = "birth-photos/" + savedJob.getId();
			
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}else {				
			
			
			if (birthcert.getPhotos().isEmpty()) birthcert.setPhotos(null);
			
			
			
			service.save(birthcert);
		}
				
		redirectAttributes.addFlashAttribute("message", "The job has been saved successfully!");
		
		return getRedirectURLToAffectedBirth(birthcert);
	}

	private String getRedirectURLToAffectedBirth(Birth birthcert) {
		Integer birthcertId = birthcert.getId();
		return "redirect:/births/page/1?sortField=id&sortDir=asc&keyword=" + birthcertId;
	}
	
	
	
	@GetMapping("/births/edit/{id}")
	public String editBirth(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		Birth birthcert = service.get(id);
		
		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		List<User> listUsers = (List<User>) userRepo.findAll();
		

		model.addAttribute("listApplicants", listApplicants);
		model.addAttribute("listUsers", listUsers);
		
		model.addAttribute("birthcert", birthcert);
		model.addAttribute("pageTitle", "Edit Birth (ID: " + id + ")");
		
		return "births/birth_form";
		
		} catch (BirthNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/births";
		}
		
	}
	
	
	@GetMapping("/births/view/{id}")
	public String viewBirth(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		try {
		Birth birth = service.get(id);
		
		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		List<User> listUsers = (List<User>) userRepo.findAll();
		

		model.addAttribute("listApplicants", listApplicants);
		model.addAttribute("listUsers", listUsers);
		
		model.addAttribute("birth", birth);
		model.addAttribute("pageTitle", "View Birth (ID: " + id + ")");
		
		return "births/birthpreview";
		
		} catch (BirthNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/births";
		}
		
	}
	
	
	@GetMapping("/births/delete/{id}")
	public String deleteBirth(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		service.delete(id);	
		
		redirectAttributes.addFlashAttribute("message", "The birthcert ID" + id + " has been deleted successfully!");
		
		
		} catch (BirthNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
		}

		return "redirect:/births";
		
	}
	
	@GetMapping("/births/{id}/enabled/{status}")
	public String updateBirthEnabledStatus(@PathVariable("id") Integer id, 
			@PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) {
		String status =  enabled ? "enabled" : "disabled";
		String message = "The birthcert ID" + id + " has been " + status;
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return "redirect:/births";
	}
	
	@GetMapping("/births/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		List<Birth> listBirths = service.listAll();
		BirthCsvExporter exporter = new BirthCsvExporter();
		exporter.export(listBirths, response);
	}
	
	@GetMapping("/births/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		List<Birth> listBirths = service.listAll();
		BirthExcelExporter exporter = new BirthExcelExporter();
		exporter.export(listBirths, response);
	}
	
	@GetMapping("/births/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws IOException {
		List<Birth> listBirths = service.listAll();
		BirthPDFExporter exporter = new BirthPDFExporter();
		exporter.export(listBirths, response);
	}
}
