package com.Defis.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.Defis.domain.Agent;
import com.Defis.domain.Customer;
import com.Defis.domain.Job;
import com.Defis.repository.AgentRepository;
import com.Defis.repository.CustomerRepository;
import com.Defis.repository.JobRepository;
import com.Defis.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private AgentRepository agentRepo;
	
	@Autowired
	private JobRepository jobRepo;
	
	@GetMapping("customers/new")
	public String showNewCustomerForm(Model model) {
		List<Agent> listAgents = agentRepo.findAll();
		List<Job> listJobs = jobRepo.findAll();
		
		model.addAttribute("customer", new Customer());
		model.addAttribute("listAgents", listAgents);
		model.addAttribute("listJobs", listJobs);
		
		return "customer_form";
	}
	
	@RequestMapping(value = "/customers/save", method = RequestMethod.POST)
	public String saveCustomer(@ModelAttribute("customer") Customer customer, HttpServletRequest request) {
		String[] detailIDs = request.getParameterValues("detailID");
		String[] detailCnames = request.getParameterValues("detailCname");
		String[] detailCcontacts = request.getParameterValues("detailCcontact");
		String[] detailCnationalIds = request.getParameterValues("detailCnationalId");
		String[] detailCrelationships = request.getParameterValues("detailCrelationship");
		String[] detailCcurrent_residences = request.getParameterValues("detailCcurrent_residence");
		
		for (int i  = 0; i < detailCnames.length; i++) {
			if(detailIDs != null && detailIDs.length > 0) {
				customer.setDetail(Integer.valueOf(detailIDs[i]), detailCnames[i], detailCcontacts[i], detailCnationalIds[i], detailCrelationships[i], detailCcurrent_residences[i]);
			}else {
			customer.addDetail(detailCnames[i], detailCcontacts[i], detailCnationalIds[i], detailCrelationships[i], detailCcurrent_residences[i]);
			}
		}
		
		customerRepo.save(customer);
		
		
		MultipartFile itemImage = customer.getItemImage();
		
		if(!itemImage.isEmpty()) {
			try {
				byte[] bytes = itemImage.getBytes();
				String name = customer.getId() + ".jpg";
				
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("./customer-images/customer/" + name)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		return "redirect:/customers";
	}
	
	@RequestMapping("/customers")
	public String customerList(Model model) {
		List<Customer> customerList = customerService.findAll();
		model.addAttribute("customerList", customerList);	
		return "customerList";
		
	}
	
	@GetMapping("/customers/edit/{id}")
	public String listEditCustomerForm(@PathVariable("id") Integer id, Model model) {
		Customer customer = customerRepo.findById(id).get();
		model.addAttribute("customer", customer);
		
		List<Agent> listAgents = agentRepo.findAll();
		List<Job> listJobs = jobRepo.findAll();
		
		model.addAttribute("listAgents", listAgents);
		model.addAttribute("listJobs", listJobs);
		
		return "customer_form";
	}
	
	@GetMapping("/customers/delete/{id}")
	public String deleteCustomer(@PathVariable("id") Integer id, Model model) {
		customerRepo.deleteById(id);
		
		return "redirect:/customers";
		
	}
	

	@GetMapping("/customerInfo/edit/{id}")
	public String customerInfo(@PathVariable("id") Integer id, Model model) {
		Customer customer = customerRepo.findById(id).get();
		model.addAttribute("customer", customer);

		
		return "customerInfo";
	}

}
