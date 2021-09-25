package com.Defis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Defis.domain.Task;
import com.Defis.domain.User;
import com.Defis.exception.TaskNotFoundException;
import com.Defis.repository.TasKRepository;
import com.Defis.repository.UserRepository;

@Service
@Transactional
public class TaskService {


	public static final int TASKS_PER_PAGE = 15;
	@Autowired
	private TasKRepository taskRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	public List<Task> findAll() {
		List<Task> taskList = (List<Task>) taskRepo.findAll();
		List<Task> activeTaskList = new ArrayList();
		
		for (Task task: taskList) {
			
			activeTaskList.add(task);
		
	}
		
		return activeTaskList;
	}
	
	
	public List<Task> listAll()	{
		return (List<Task>) taskRepo.findAll(Sort.by("applicant").ascending());
		
	}

	public List<User> listParticipants(){
		return (List<User>) userRepo.findAll();
	}
	
	public List<User> listResponsible(){
		return (List<User>) userRepo.findAll();
	}
	
	public List<User> listObservers(){
		return (List<User>) userRepo.findAll();
	}
	
	public Task get(Integer id) throws TaskNotFoundException {
		try {
			return taskRepo.findById(id).get();
		}catch (NoSuchElementException ex) {
			throw new TaskNotFoundException("Could not find any task with ID " + id);
		}
	}
	
	public Task getApplicant(Integer applicant) throws TaskNotFoundException {
		try {
			return taskRepo.findById(applicant).get();
		}catch (DataIntegrityViolationException e) {
			throw new TaskNotFoundException("Trainer with ID " + applicant + "already exist");
		}
	}
	
	public void delete(Integer id) throws TaskNotFoundException {
		Long countById = taskRepo.countById(id);
		if(countById == null || countById == 0) {
			throw new TaskNotFoundException("Could not find any task with ID " + id);
		}
		
		taskRepo.deleteById(id);
	}
	
	public Task save(Task task) {		
		
		return taskRepo.save(task);
		
	}
	
	public Page<Task> listByPage(int pageNum, String sortField, String sortDir, String keyword){
		Sort sort = Sort.by(sortField);
		
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNum - 1, TASKS_PER_PAGE, sort);
		
		if(keyword != null) {
			return taskRepo.findAll(keyword, pageable);
		}
		
		return taskRepo.findAll(pageable);
	}
	
	
}
