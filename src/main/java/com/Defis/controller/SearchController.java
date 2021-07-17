package com.Defis.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Defis.domain.Customer;
import com.Defis.domain.User;
import com.Defis.service.CustomerService;
import com.Defis.service.UserService;

@Controller
public class SearchController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/searchByCategory")
	public String searchByCategory(
			@RequestParam("category") String category,
			Model model, Principal principal
			){
		if(principal!=null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		String classActiveCategory = "active"+category;
		classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
		classActiveCategory = classActiveCategory.replaceAll("&", "");
		model.addAttribute(classActiveCategory, true);
		
		List<Customer> customerList = customerService.findByCategory(category);
		
		if (customerList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "customerShelf";
		}
		
		model.addAttribute("customerList", customerList);
		
		return "customerShelf";
	}
	
	@RequestMapping("/searchCustomer")
	public String searchCustomer(
			@ModelAttribute("keyword") String keyword,
			Principal principal, Model model
			) {
		if(principal!=null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		List<Customer> customerList = customerService.blurrySearch(keyword);
		
		if (customerList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "customers";
		}
		
		model.addAttribute("customerList", customerList);
		
		return "customers";
	}
}
