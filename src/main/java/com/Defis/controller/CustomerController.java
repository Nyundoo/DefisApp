package com.Defis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Defis.domain.Agent;
import com.Defis.domain.Customer;
import com.Defis.repository.AgentRepository;
import com.Defis.repository.CustomerRepository;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private AgentRepository agentRepo;
	
	@GetMapping("customers/new")
	public String showNewCustomerForm(Model model) {
		List<Agent> listCategories = agentRepo.findAll();
		
		model.addAttribute("customer", new Customer());
		model.addAttribute("listCategories", listCategories);
		
		return "customer_form";
	}
	
	@PostMapping("/customers/save")
	public String saveCustomer(Customer customer, HttpServletRequest request) {
		String[] detailIDs = request.getParameterValues("detailID");
		String[] detailCnames = request.getParameterValues("detailCname");
		String[] detailCcontacts = request.getParameterValues("detailCcontact");
		String[] detailCnationalIds = request.getParameterValues("detailCnationalId");
		String[] detailCrelationships = request.getParameterValues("detailCrelationship");
		String[] detailCemail = request.getParameterValues("detailCemail");
		String[] detailCcounties = request.getParameterValues("detailCcounty");
		String[] detailCcwards = request.getParameterValues("detailCcward");
		String[] detailCcurrent_residences = request.getParameterValues("detailCcurrent_residence");
		
		for (int i  = 0; i < detailCnames.length; i++) {
			if(detailIDs != null && detailIDs.length > 0) {
				customer.setDetail(Integer.valueOf(detailIDs[i]), detailCnames[i], detailCcontacts[i], detailCnationalIds[i], detailCrelationships[i], detailCemail[i], detailCcounties[i], detailCcwards[i], detailCcurrent_residences[i]);
			}else {
			customer.addDetail(detailCnames[i], detailCcontacts[i], detailCnationalIds[i], detailCrelationships[i], detailCemail[i], detailCcounties[i], detailCcwards[i], detailCcurrent_residences[i]);
			}
		}
		
		customerRepo.save(customer);
		
		return "redirect:/customers";
	}
	
	@GetMapping("/customers")
	public String lisCustomers(Model model) {
		List<Customer> listCustomers = customerRepo.findAll();
		model.addAttribute("listCustomers", listCustomers);
		
		return "customers";
	}
	
	@GetMapping("/customers/edit/{id}")
	public String listEditCustomerForm(@PathVariable("id") Integer id, Model model) {
		Customer customer = customerRepo.findById(id).get();
		model.addAttribute("customer", customer);
		
		List<Agent> listCategories = agentRepo.findAll();
		
		model.addAttribute("listCategories", listCategories);
		
		return "customer_form";
	}
	
	@GetMapping("/customers/delete/{id}")
	public String deleteCustomer(@PathVariable("id") Integer id, Model model) {
		customerRepo.deleteById(id);
		
		return "redirect:/customers";
		
	}

}
