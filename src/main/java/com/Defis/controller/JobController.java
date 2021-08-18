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

import com.Defis.domain.Job;
import com.Defis.exception.JobNotFoundException;
import com.Defis.exporter.JobCsvExporter;
import com.Defis.exporter.JobExcelExporter;
import com.Defis.exporter.JobPDFExporter;
import com.Defis.service.JobService;

@Controller
public class JobController {
	@Autowired
	private JobService service;
	
	@GetMapping("/jobs")
	public String listFirstPage(Model model) {
		
		return listByPage(1, model, "jobTitle", "asc", null);
	}
	
	@GetMapping("/jobs/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
					@Param("sortField") String sortField, 
					@Param("sortDir") String sortDir,
					@Param("keyword") String keyword
					
					) {
				System.out.println("Sort Field: " + sortField);
				System.out.println("Sort Order: " + sortDir);
				
				Page<Job> page = service.listByPage(pageNum, sortField, sortDir, keyword);
				List<Job> listJobs = page.getContent();
				
				long startCount = (pageNum - 1) * JobService.JOBS_PER_PAGE + 1;
				long endCount = startCount + JobService.JOBS_PER_PAGE - 1;		
				if (endCount > page.getTotalElements()) {
					endCount = page.getTotalElements();
				}
				
				String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
				
				model.addAttribute("currentPage", pageNum);
				model.addAttribute("totalPages", page.getTotalPages());
				model.addAttribute("startCount", startCount);
				model.addAttribute("endCount", endCount);
				model.addAttribute("totalItems", page.getTotalElements());
				model.addAttribute("listJobs", listJobs);	
				model.addAttribute("sortField", sortField);	
				model.addAttribute("sortDir", sortDir);
				model.addAttribute("reverseSortDir", reverseSortDir);
				model.addAttribute("keyword", keyword);
				
				
				return "jobs/jobs";
			}
	
	@GetMapping("/jobs/new")
	public String newJobs(Model model) {	
		
		Job job = new Job();
		
		model.addAttribute("job", job);

		model.addAttribute("pageTitle", "Create New Job");
		
		return "jobs/job_form";
	}
	
	@PostMapping("/jobs/save")
	public String saveJob(Job job, 
			RedirectAttributes redirectAttributes) throws IOException {
		
	
			service.save(job);
		
				
		redirectAttributes.addFlashAttribute("message", "The job has been saved successfully!");
		
		return getRedirectURLToAffectedJob(job);
	}

	private String getRedirectURLToAffectedJob(Job job) {
		Integer jobId = job.getId();
		return "redirect:/jobs/page/1?sortField=id&sortDir=asc&keyword=" + jobId;
	}
	
	@GetMapping("/jobs/edit/{id}")
	public String editJob(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		Job job = service.get(id);
		
		
		model.addAttribute("job", job);
		model.addAttribute("pageTitle", "Edit Job (ID: " + id + ")");
		
		return "jobs/job_form";
		
		} catch (JobNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/jobs";
		}
		
	}
	
	
	@GetMapping("/jobs/view/{id}")
	public String viewJob(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		Job job = service.get(id);
		
		
		model.addAttribute("job", job);
		model.addAttribute("pageTitle", "View Job (ID: " + id + ")");
		
		return "jobs/jobpreview";
		
		} catch (JobNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/jobs";
		}
		
	}
	
	
	@GetMapping("/jobs/delete/{id}")
	public String deleteJob(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		service.delete(id);	
		
		redirectAttributes.addFlashAttribute("message", "The job ID" + id + " has been deleted successfully!");
		
		
		} catch (JobNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
		}

		return "redirect:/jobs";
		
	}
	

	
	@GetMapping("/jobs/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		List<Job> listJobs = service.listAll();
		JobCsvExporter exporter = new JobCsvExporter();
		exporter.export(listJobs, response);
	}
	
	@GetMapping("/jobs/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		List<Job> listJobs = service.listAll();
		JobExcelExporter exporter = new JobExcelExporter();
		exporter.export(listJobs, response);
	}
	
	@GetMapping("/jobs/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws IOException {
		List<Job> listJobs = service.listAll();
		JobPDFExporter exporter = new JobPDFExporter();
		exporter.export(listJobs, response);
	}
}
