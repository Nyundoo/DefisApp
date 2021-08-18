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
import com.Defis.domain.Training;
import com.Defis.domain.User;
import com.Defis.exception.TrainingNotFoundException;
import com.Defis.exporter.TrainingCsvExporter;
import com.Defis.exporter.TrainingExcelExporter;
import com.Defis.exporter.TrainingPDFExporter;
import com.Defis.repository.ApplicantRepository;
import com.Defis.repository.UserRepository;
import com.Defis.service.TrainingService;

@Controller
public class TrainingController {

	@Autowired
	private TrainingService service;
	
	@Autowired
	private ApplicantRepository applicantRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@GetMapping("/trainings")
	public String listFirstPage(Model model) {
		
		return listByPage(1, model, "applicant", "asc", null);
	}
	
	@GetMapping("/trainings/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword
			
			) {
		System.out.println("Sort Field: " + sortField);
		System.out.println("Sort Order: " + sortDir);
		
		Page<Training> page = service.listByPage(pageNum, sortField, sortDir, keyword);
		List<Training> listTrainings = page.getContent();
		
		long startCount = (pageNum - 1) * TrainingService.TRAININGS_PER_PAGE + 1;
		long endCount = startCount + TrainingService.TRAININGS_PER_PAGE - 1;		
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listTrainings", listTrainings);	
		model.addAttribute("sortField", sortField);	
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);
		
		
		return "trainings/trainings";
	}
	
	@GetMapping("/trainings/new")
	public String newTraining(Model model) {
		
		
		List<User> listUsers = (List<User>) userRepo.findAll();

		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		
		Training training = new Training();
		model.addAttribute("training", training);
		model.addAttribute("pageTitle", "Create New Training");
		model.addAttribute("listUsers", listUsers);

		model.addAttribute("listApplicants", listApplicants);
		
		return "trainings/training_form";
	}
	
	@PostMapping("/trainings/save")
	public String saveTraining(Training training,
			Integer applicant,
			RedirectAttributes redirectAttributes) throws IOException {

		try {
			service.save(training);
			
			
			redirectAttributes.addFlashAttribute("message", "The training has been saved successfully!");
			
			return getRedirectURLToAffectedTraining(training);
			
			} catch (DataIntegrityViolationException ex) {
				redirectAttributes.addFlashAttribute("message", "Trainer with ID " + applicant + " already exist in training");
				
				return "redirect:/trainings";
			}
		
		
	
	}

	private String getRedirectURLToAffectedTraining(Training training) {
		Integer trainingId = training.getId();
		return "redirect:/trainings/page/1?sortField=id&sortDir=asc&keyword=" + trainingId;
	}
	
	@GetMapping("/trainings/edit/{id}")
	public String editTraining(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		Training training = service.get(id);
		List<User> listUsers = (List<User>) userRepo.findAll();

		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		

		model.addAttribute("listApplicants", listApplicants);
		model.addAttribute("listUsers", listUsers);

		model.addAttribute("training", training);
		model.addAttribute("pageTitle", "Edit Training (ID: " + id + ")");
		
		return "trainings/training_form";
		
		} catch (TrainingNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/trainings";
		}
		
	}
	
	
	@GetMapping("/trainings/view/{id}")
	public String viewTraining(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		Training training = service.get(id);
		List<User> listUsers = (List<User>) userRepo.findAll();

		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		

		model.addAttribute("listApplicants", listApplicants);
		model.addAttribute("listUsers", listUsers);

		model.addAttribute("training", training);
		model.addAttribute("pageTitle", "View Training (ID: " + id + ")");
		
		return "trainings/trainingpreview";
		
		} catch (TrainingNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/trainings";
		}
		
	}
	
	
	@GetMapping("/trainings/delete/{id}")
	public String deleteTraining(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		service.delete(id);	
		
		redirectAttributes.addFlashAttribute("message", "The training ID" + id + " has been deleted successfully!");
		
		
		} catch (TrainingNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
		}

		return "redirect:/trainings";
		
	}
	
	@GetMapping("/trainings/{id}/enabled/{status}")
	public String updateTrainingEnabledStatus(@PathVariable("id") Integer id, 
			@PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) {
		String status =  enabled ? "enabled" : "disabled";
		String message = "The training ID" + id + " has been " + status;
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return "redirect:/trainings";
	}
	
	@GetMapping("/trainings/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		List<Training> listTrainings = service.listAll();
		TrainingCsvExporter exporter = new TrainingCsvExporter();
		exporter.export(listTrainings, response);
	}
	
	@GetMapping("/trainings/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		List<Training> listTrainings = service.listAll();
		TrainingExcelExporter exporter = new TrainingExcelExporter();
		exporter.export(listTrainings, response);
	}
	
	@GetMapping("/trainings/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws IOException {
		List<Training> listTrainings = service.listAll();
		TrainingPDFExporter exporter = new TrainingPDFExporter();
		exporter.export(listTrainings, response);
	}
}
