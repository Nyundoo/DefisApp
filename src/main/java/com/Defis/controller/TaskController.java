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

import com.Defis.domain.Task;
import com.Defis.domain.User;
import com.Defis.exception.TaskNotFoundException;
import com.Defis.exporter.TaskCsvExporter;
import com.Defis.exporter.TaskExcelExporter;
import com.Defis.service.TaskService;

@Controller
public class TaskController {
	@Autowired
	private TaskService service;
		
	@GetMapping("/tasks")
	public String listFirstPage(Model model) {
		
		return listByPage(1, model, "id", "asc", null);
	}
	
	@GetMapping("/tasks/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword
			
			) {
		System.out.println("Sort Field: " + sortField);
		System.out.println("Sort Order: " + sortDir);
		
		Page<Task> page = service.listByPage(pageNum, sortField, sortDir, keyword);
		List<Task> listTasks = page.getContent();
		
		long startCount = (pageNum - 1) * TaskService.TASKS_PER_PAGE + 1;
		long endCount = startCount + TaskService.TASKS_PER_PAGE - 1;		
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listTasks", listTasks);	
		model.addAttribute("sortField", sortField);	
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);
		
		
		return "tasks/tasks";
	}
	
	@GetMapping("/tasks/new")
	public String newTask(Model model) {	
		
		List<User> listParticipants = service.listParticipants();
		List<User> listResponsible = service.listResponsible();
		List<User> listObservers = service.listObservers();
		
		Task task = new Task();
		
		model.addAttribute("task", task);

		model.addAttribute("pageTitle", "Create New Task");
		
		model.addAttribute("listParticipants", listParticipants);
		model.addAttribute("listResponsible", listResponsible);
		model.addAttribute("listObservers", listObservers);
		
		return "tasks/task_form";
	}
	
	@PostMapping("/tasks/save")
	public String saveTask(Task task,
			Integer applicant,
			RedirectAttributes redirectAttributes) throws IOException {

		try {
			service.save(task);
			
			
			redirectAttributes.addFlashAttribute("message", "The task has been saved successfully!");
			
			return getRedirectURLToAffectedTask(task);
			
			} catch (DataIntegrityViolationException ex) {
				redirectAttributes.addFlashAttribute("message", "Task with ID " + applicant + " already exist in task");
				
				return "redirect:/tasks";
			}		
	
	}

	private String getRedirectURLToAffectedTask(Task task) {
		Integer taskId = task.getId();
		return "redirect:/tasks/page/1?sortField=id&sortDir=asc&keyword=" + taskId;
	}
	
	@GetMapping("/tasks/edit/{id}")
	public String editTask(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		Task task = service.get(id);
		
		List<User> listParticipants = service.listParticipants();
		List<User> listResponsible = service.listResponsible();
		List<User> listObservers = service.listObservers();
		
		model.addAttribute("task", task);
		model.addAttribute("pageTitle", "Edit Task (ID: " + id + ")");	
		model.addAttribute("listParticipants", listParticipants);
		model.addAttribute("listResponsible", listResponsible);
		model.addAttribute("listObservers", listObservers);
		
		return "tasks/task_form";
		
		} catch (TaskNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/tasks";
		}
		
	}
	
	
	@GetMapping("/tasks/view/{id}")
	public String viewTask(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		Task task = service.get(id);
		
		List<User> listParticipants = service.listParticipants();
		List<User> listResponsible = service.listResponsible();
		List<User> listObservers = service.listObservers();
		
		model.addAttribute("task", task);
		model.addAttribute("pageTitle", "View Task (ID: " + id + ")");	
		model.addAttribute("listParticipants", listParticipants);
		model.addAttribute("listResponsible", listResponsible);
		model.addAttribute("listObservers", listObservers);
		
		return "tasks/taskprofile";
		
		} catch (TaskNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
			return "redirect:/tasks";
		}
		
	}
	
	
	@GetMapping("/tasks/delete/{id}")
	public String deleteTask(@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
		service.delete(id);	
		
		redirectAttributes.addFlashAttribute("message", "The task ID" + id + " has been deleted successfully!");
		
		
		} catch (TaskNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
		}

		return "redirect:/tasks";
		
	}
	

	
	@GetMapping("/tasks/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		List<Task> listTasks = service.listAll();
		TaskCsvExporter exporter = new TaskCsvExporter();
		exporter.export(listTasks, response);
	}
	
	@GetMapping("/tasks/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		List<Task> listTasks = service.listAll();
		TaskExcelExporter exporter = new TaskExcelExporter();
		exporter.export(listTasks, response);
	}
	
}
