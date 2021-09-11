package com.Defis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Defis.service.AgentService;

@RestController
public class AgentRestController {
	
	@Autowired
	private AgentService service;
	
	@PostMapping("/agents/check_email")
	public String checkDuplicateEmail(@Param("id") Integer id, @Param("email") String email) {
		return	service.isEmailUnique(id, email) ? "OK" : "Duplicated";
		
	}
}
