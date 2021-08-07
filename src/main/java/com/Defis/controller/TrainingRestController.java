package com.Defis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Defis.service.TrainingService;

@RestController
public class TrainingRestController {
	@Autowired
	private TrainingService service;
	
	
}
