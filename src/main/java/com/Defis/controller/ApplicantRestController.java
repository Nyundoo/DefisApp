package com.Defis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Defis.service.ApplicantService;

@RestController
public class ApplicantRestController {
	@Autowired
	private ApplicantService service;
	
	@PostMapping("/applicants/check_email")
	public String checkDuplicateEmail(@Param("id") Integer id, @Param("email") String email) {
		return	service.isEmailUnique(id, email) ? "OK" : "Duplicated";
		
	}
}
