package com.Defis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Defis.domain.Applicant;
import com.Defis.domain.Ticket;
import com.Defis.domain.User;
import com.Defis.exception.TicketNotFoundException;
import com.Defis.exporter.TicketCsvExporter;
import com.Defis.exporter.TicketExcelExporter;
import com.Defis.exporter.TicketPDFExporter;
import com.Defis.repository.ApplicantRepository;
import com.Defis.repository.TicketRepository;
import com.Defis.repository.UserRepository;
import com.Defis.service.TicketService;

@Controller
public class TicketController {
	@Autowired
	private ApplicantRepository applicantRepo;

	@Autowired
	private TicketService service;
	
	@Autowired
	private TicketRepository ticketRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/tickets")
	public String listFirstPage(Model model) {
		
		return listByPage(1, model, "applicant", "asc", null);
	}
	
	@GetMapping("/tickets/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword
			
			) {
		System.out.println("Sort Field: " + sortField);
		System.out.println("Sort Order: " + sortDir);
		
		Page<Ticket> page = service.listByPage(pageNum, sortField, sortDir, keyword);
		List<Ticket> listTickets = page.getContent();
		
		long startCount = (pageNum - 1) * TicketService.TICKETS_PER_PAGE + 1;
		long endCount = startCount + TicketService.TICKETS_PER_PAGE - 1;		
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listTickets", listTickets);	
		model.addAttribute("sortField", sortField);	
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);
		
		
		return "tickets/tickets";
	}
	
	@GetMapping("/tickets/new")
	public String newTicket(Model model) {
		
		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		List<User> listUsers = (List<User>) userRepo.findAll();
		List<Ticket> listTickets = (List<Ticket>) ticketRepo.findAll();
		
		Ticket ticket = new Ticket();
		model.addAttribute("ticket", ticket);
		model.addAttribute("pageTitle", "Create New Ticket");

		model.addAttribute("listTickets", listTickets);
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("listApplicants", listApplicants);
		
		return "tickets/ticket_form";
	}
	
	@PostMapping("/tickets/save")
	public String saveTicket(Ticket ticket,
			Integer applicant,
			RedirectAttributes redirectAttributes) throws IOException {

		try {
			service.save(ticket);
			
			
			redirectAttributes.addFlashAttribute("message", "The ticket has been saved successfully!");
			
			return getRedirectURLToAffectedTicket(ticket);
			
			} catch (DataIntegrityViolationException ex) {
				redirectAttributes.addFlashAttribute("message", "Trainer with ID " + applicant + " already exist in ticket");
				
				return "redirect:/tickets";
			}
		
		
	
	}

	private String getRedirectURLToAffectedTicket(Ticket ticket) {
		Integer ticketId = ticket.getId();
		return "redirect:/tickets/page/1?sortField=id&sortDir=asc&keyword=" + ticketId;
	}
	
	@GetMapping("/tickets/edit/{id}")
	public String editTicket(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
			Ticket ticket = service.get(id);
			List<User> listUsers = (List<User>) userRepo.findAll();

		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		

		model.addAttribute("listUsers", listUsers);
		model.addAttribute("listApplicants", listApplicants);
		
		model.addAttribute("ticket", ticket);
		model.addAttribute("pageTitle", "Edit Training (ID: " + id + ")");
		
		return "tickets/ticket_form";
		
		} catch (TicketNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/trainings";
		}
		
	}
	
	
	@GetMapping("/tickets/view/{id}")
	public String viewTicket(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
			Ticket ticket = service.get(id);
			List<User> listUsers = (List<User>) userRepo.findAll();

		List<Applicant> listApplicants = (List<Applicant>) applicantRepo.findAll();
		

		model.addAttribute("listUsers", listUsers);
		model.addAttribute("listApplicants", listApplicants);
		
		model.addAttribute("ticket", ticket);
		model.addAttribute("pageTitle", "View Training (ID: " + id + ")");
		
		return "tickets/ticketpreview";
		
		} catch (TicketNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/trainings";
		}
		
	}
	
	
	@GetMapping("/tickets/delete/{id}")
	public String deleteTicket(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		service.delete(id);	
		
		redirectAttributes.addFlashAttribute("message", "The tickets ID" + id + " has been deleted successfully!");
		
		
		} catch (TicketNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
		}

		return "redirect:/tickets";
		
	}
	
	@GetMapping("/tickets/{id}/enabled/{status}")
	public String updateTicketEnabledStatus(@PathVariable("id") Integer id, 
			@PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) {
		String status =  enabled ? "enabled" : "disabled";
		String message = "The ticket ID" + id + " has been " + status;
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return "redirect:/tickets";
	}
	
	@GetMapping("/tickets/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		List<Ticket> listTickets = service.listAll();
		TicketCsvExporter exporter = new TicketCsvExporter();
		exporter.export(listTickets, response);
	}
	
	@GetMapping("/tickets/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		List<Ticket> listTickets = service.listAll();
		TicketExcelExporter exporter = new TicketExcelExporter();
		exporter.export(listTickets, response);
	}
	
	@GetMapping("/tickets/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws IOException {
		List<Ticket> listTickets = service.listAll();
		TicketPDFExporter exporter = new TicketPDFExporter();
		exporter.export(listTickets, response);
	}
}
