package com.Defis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Defis.domain.Job;
import com.Defis.repository.JobRepository;


@Controller
public class JobController {


	@Autowired
	private JobRepository repo;
	
	@GetMapping("/jobs")
	public String listJobs(Model model) {
		List<Job> listJobs = repo.findAll();
		model.addAttribute("listJobs", listJobs);
		
		return "jobs";
	}
	
	@GetMapping("/jobs/new")
	public String showJobNewForm(Model model) {
		model.addAttribute("job", new Job());
		
		return "job_form";
	}
	
	@PostMapping("/jobs/save")
	public String saveJob(Job job) {
		repo.save(job);
		
		return "redirect:/jobs";
	}
	
	@GetMapping("/jobs/edit/{id}")
	public String listEditjobForm(@PathVariable("id") Integer id, Model model) {
		Job job = repo.findById(id).get();
		model.addAttribute("job", job);
		
		List<Job> listJobs = repo.findAll();
		
		model.addAttribute("listJobs", listJobs);
		
		return "job_form";
	}
	
	@GetMapping("/jobs/delete/{id}")
	public String deleteJob(@PathVariable("id") Integer id, Model model) {
		repo.deleteById(id);
		
		return "redirect:/jobs";
		
	}
}
