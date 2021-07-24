package com.Defis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Defis.domain.Customer;
import com.Defis.domain.Medical;
import com.Defis.repository.CustomerRepository;
import com.Defis.repository.MedicalRepository;

@Controller
public class MedicalController {
	@Autowired
	private MedicalRepository medicalRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@GetMapping("/medicals")
	public String listMedicals(Model model) {
		List<Medical> listMedicals = medicalRepo.findAll();
		List<Customer> listCustomers = customerRepo.findAll();
		
		model.addAttribute("medical", new Medical());
		model.addAttribute("listMedicals", listMedicals);
		model.addAttribute("listCustomers", listCustomers);
		
		return "medicals";
	}
	
	@GetMapping("/medicals/new")
	public String showMedicalNewForm(Model model) {
		List<Medical> listMedicals = medicalRepo.findAll();
		List<Customer> listCustomers = customerRepo.findAll();
		
		model.addAttribute("medical", new Medical());
		model.addAttribute("listMedicals", listMedicals);
		model.addAttribute("listCustomers", listCustomers);
		
		return "medical_form";
	}
	
	@PostMapping("/medicals/save")
	public String savemedical(Medical medical) {
		medicalRepo.save(medical);
		
		return "redirect:/medicals";
	}
	
	@GetMapping("/medicals/edit/{id}")
	public String listEditMedicalForm(@PathVariable("id") Integer id, Model model) {
		Medical medical = medicalRepo.findById(id).get();
		model.addAttribute("medical", medical);
		
		List<Medical> listMedicals = medicalRepo.findAll();
		List<Customer> listCustomers = customerRepo.findAll();
		
		model.addAttribute("listMedicals", listMedicals);
		model.addAttribute("listCustomers", listCustomers);
		
		return "medical_formedit";
	}
	
	@GetMapping("/medicals/delete/{id}")
	public String deleteMedical(@PathVariable("id") Integer id, Model model) {
		medicalRepo.deleteById(id);
		
		return "redirect:/medicals";
		
	}

}
