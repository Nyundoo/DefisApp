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

import com.Defis.domain.Jobs;
import com.Defis.exception.JobsNotFoundException;
import com.Defis.exporter.JobsCsvExporter;
import com.Defis.exporter.JobsExcelExporter;
import com.Defis.exporter.JobsPDFExporter;
import com.Defis.service.JobsService;

@Controller
public class JobsController {
	@Autowired
	private JobsService service;
	
	@GetMapping("/jobss")
	public String listFirstPage(Model model) {
		
		return listByPage(1, model, "jobTitle", "asc", null);
	}
	
	@GetMapping("/jobss/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
					@Param("sortField") String sortField, 
					@Param("sortDir") String sortDir,
					@Param("keyword") String keyword
					
					) {
				System.out.println("Sort Field: " + sortField);
				System.out.println("Sort Order: " + sortDir);
				
				Page<Jobs> page = service.listByPage(pageNum, sortField, sortDir, keyword);
				List<Jobs> listJobss = page.getContent();
				
				long startCount = (pageNum - 1) * JobsService.JOBSS_PER_PAGE + 1;
				long endCount = startCount + JobsService.JOBSS_PER_PAGE - 1;		
				if (endCount > page.getTotalElements()) {
					endCount = page.getTotalElements();
				}
				
				String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
				
				model.addAttribute("currentPage", pageNum);
				model.addAttribute("totalPages", page.getTotalPages());
				model.addAttribute("startCount", startCount);
				model.addAttribute("endCount", endCount);
				model.addAttribute("totalItems", page.getTotalElements());
				model.addAttribute("listJobss", listJobss);	
				model.addAttribute("sortField", sortField);	
				model.addAttribute("sortDir", sortDir);
				model.addAttribute("reverseSortDir", reverseSortDir);
				model.addAttribute("keyword", keyword);
				
				
				return "jobss/jobs";
			}
	
	@GetMapping("/jobss/new")
	public String newJobs(Model model) {	
		
		Jobs jobs = new Jobs();
		
		model.addAttribute("jobs", jobs);

		model.addAttribute("pageTitle", "Create New Job");
		
		return "jobss/job_form";
	}
	
	@PostMapping("/jobss/save")
	public String saveJob(Jobs jobs, 
			RedirectAttributes redirectAttributes) throws IOException {
		
	
			service.save(jobs);
		
				
		redirectAttributes.addFlashAttribute("message", "The job has been saved successfully!");
		
		return getRedirectURLToAffectedJob(jobs);
	}

	private String getRedirectURLToAffectedJob(Jobs jobs) {
		Integer jobId = jobs.getId();
		return "redirect:/jobss/page/1?sortField=id&sortDir=asc&keyword=" + jobId;
	}
	
	@GetMapping("/jobss/edit/{id}")
	public String editJob(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		Jobs jobs = service.get(id);
		
		
		model.addAttribute("jobs", jobs);
		model.addAttribute("pageTitle", "Edit Job (ID: " + id + ")");
		
		return "jobss/job_form";
		
		} catch (JobsNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/jobss";
		}
		
	}
	
	
	@GetMapping("/jobss/view/{id}")
	public String viewJob(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		Jobs jobs = service.get(id);
		
		
		model.addAttribute("jobs", jobs);
		model.addAttribute("pageTitle", "View Job (ID: " + id + ")");
		
		return "jobss/jobpreview";
		
		} catch (JobsNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/jobss";
		}
		
	}
	
	
	@GetMapping("/jobss/delete/{id}")
	public String deleteJob(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		service.delete(id);	
		
		redirectAttributes.addFlashAttribute("message", "The job ID" + id + " has been deleted successfully!");
		
		
		} catch (JobsNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
		}

		return "redirect:/jobss";
		
	}
	

	
	@GetMapping("/jobss/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		List<Jobs> listJobss = service.listAll();
		JobsCsvExporter exporter = new JobsCsvExporter();
		exporter.export(listJobss, response);
	}
	
	@GetMapping("/jobss/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		List<Jobs> listJobss = service.listAll();
		JobsExcelExporter exporter = new JobsExcelExporter();
		exporter.export(listJobss, response);
	}
	
	@GetMapping("/jobss/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws IOException {
		List<Jobs> listJobss = service.listAll();
		JobsPDFExporter exporter = new JobsPDFExporter();
		exporter.export(listJobss, response);
	}
}
