package com.Defis.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Defis.domain.Applicant;
import com.Defis.domain.Medical;
import com.Defis.domain.MedicalNotFoundException;
import com.Defis.domain.User;
import com.Defis.domain.security.NyundooUserDetails;
import com.Defis.service.ApplicantService;
import com.Defis.service.MedicalService;
import com.Defis.service.UserService;
import com.Defis.utility.FileUploadUtil;

@Controller
public class AccountController {

	@Autowired
	private UserService service;
	
	@Autowired
	private MedicalService medicalRepo;
	
	@Autowired
	private ApplicantService applicantRepo;
	
	@GetMapping("/account")
	public String viewDetails(@AuthenticationPrincipal NyundooUserDetails loggedUser,
			Model model) {
		String email = loggedUser.getUsername();
		
		
		User user = service.getByEmail(email);
		
		model.addAttribute("user", user);
		
		return "users/account_form";
		
	}

	
	@PostMapping("/account/update")
	public String saveDetails(User user, 
			RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal NyundooUserDetails loggedUser,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		
		if(!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			user.setPhotos(fileName);
			
			User savedUser = service.updateAccount(user);
			
			String uploadDir = "user-photos/" + savedUser.getId();
			
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}else {
			if (user.getPhotos().isEmpty()) user.setPhotos(null);
			service.updateAccount(user);
		}
		
		loggedUser.setFirstName(user.getFirstName());
		loggedUser.setLastName(user.getLastName());
				
		redirectAttributes.addFlashAttribute("message", "Your account has been updated!");
		
		return "redirect:/account";
	}
	
	
	
	
}
