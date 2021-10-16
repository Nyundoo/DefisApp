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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Defis.domain.Applicant;
import com.Defis.domain.Payment;
import com.Defis.domain.User;
import com.Defis.exception.PaymentNotFoundException;
import com.Defis.exporter.PaymentCsvExporter;
import com.Defis.exporter.PaymentExcelExporter;
import com.Defis.exporter.PaymentPDFExporter;
import com.Defis.repository.ApplicantRepository;
import com.Defis.repository.UserRepository;
import com.Defis.service.PaymentService;

@Controller
public class PaymentController {
	@Autowired
	private ApplicantRepository applicantRepo;
	
	@Autowired
	private PaymentService service;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@GetMapping("/payments")
	public String listFirstPage(Model model) {
		
		return listByPage(1, model, "applicant", "asc", null);
	}
	
	@GetMapping("/payments/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword
			
			) {
		System.out.println("Sort Field: " + sortField);
		System.out.println("Sort Order: " + sortDir);
		
		Page<Payment> page = service.listByPage(pageNum, sortField, sortDir, keyword);
		List<Payment> listPayments = page.getContent();
		
		long startCount = (pageNum - 1) * PaymentService.PAYMENTS_PER_PAGE + 1;
		long endCount = startCount + PaymentService.PAYMENTS_PER_PAGE - 1;		
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listPayments", listPayments);	
		model.addAttribute("sortField", sortField);	
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);
		
		
		return "payments/payments";
	}
	
	@GetMapping("/payments/new")
	public String newPayment(Model model) {	
		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		List<User> listUsers = (List<User>) userRepo.findAll();
		
		Payment payments = new Payment();

		model.addAttribute("listUsers", listUsers);
		model.addAttribute("payment", payments);

		model.addAttribute("pageTitle", "Create New Payment");
		
		model.addAttribute("listApplicants", listApplicants);
		
		return "payments/payment_form";
	}
	
	@PostMapping("/payments/save")
	public String savePayment(@ModelAttribute("payment") Payment payment, 
			RedirectAttributes redirectAttributes) throws IOException {
		
		
		service.save(payment);
	
			
	redirectAttributes.addFlashAttribute("message", "The payment has been saved successfully!");
	
	return getRedirectURLToAffectedPayment(payment);
	}

	private String getRedirectURLToAffectedPayment(Payment payment) {
		Integer paymentId = payment.getId();
		return "redirect:/payments/page/1?sortField=id&sortDir=asc&keyword=" + paymentId;
	}
	
	@GetMapping("/payments/edit/{id}")
	public String editPayment(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
			Payment payment = service.get(id);
			
			List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
			List<User> listUsers = (List<User>) userRepo.findAll();
			

			model.addAttribute("listApplicants", listApplicants);
			model.addAttribute("listUsers", listUsers);
			
			model.addAttribute("payment", payment);
			model.addAttribute("pageTitle", "Edit Payment (ID: " + id + ")");
			
			return "payments/payment_form";
			
			} catch (PaymentNotFoundException ex) {
				redirectAttributes.addFlashAttribute("message", ex.getMessage());
				
				return "redirect:/payments";
			}
		
	}
	
	
	@GetMapping("/payments/delete/{id}")
	public String deletePayment(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		service.delete(id);	
		
		redirectAttributes.addFlashAttribute("message", "The payment ID" + id + " has been deleted successfully!");
		
		
		} catch (PaymentNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
		}

		return "redirect:/payments";
		
	}
	
	@GetMapping("/payments/view/{id}")
	public String viewBirth(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) throws PaymentNotFoundException {
		try {
			Payment payment = service.get(id);
		
		List<Payment> listPayment = (List<Payment>) service.findAll();
		List<User> listUsers = (List<User>) userRepo.findAll();
		

		model.addAttribute("listPayment", listPayment);
		model.addAttribute("listUsers", listUsers);
		
		model.addAttribute("payment", payment);
		model.addAttribute("pageTitle", "View Birth (ID: " + id + ")");
		
		return "births/birthpreview";
		
		} catch (PaymentNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/births";
		}
		
	}
	

	
	@GetMapping("/payments/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		List<Payment> listPayments = service.listAll();
		PaymentCsvExporter exporter = new PaymentCsvExporter();
		exporter.export(listPayments, response);
	}
	
	@GetMapping("/payments/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		List<Payment> listPayments = service.listAll();
		PaymentExcelExporter exporter = new PaymentExcelExporter();
		exporter.export(listPayments, response);
	}
	
	@GetMapping("/payments/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws IOException {
		List<Payment> listPayments = service.listAll();
		PaymentPDFExporter exporter = new PaymentPDFExporter();
		exporter.export(listPayments, response);
	}
}
