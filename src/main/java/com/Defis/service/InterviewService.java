package com.Defis.service;

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

import com.Defis.domain.Interview;
import com.Defis.exception.InterviewNotFoundException;
import com.Defis.repository.InterviewRepository;

@Service
@Transactional
public class InterviewService {


	public static final int INTERVIEWS_PER_PAGE = 15;
	@Autowired
	private InterviewRepository interviewRepo;
	
	
	
	public List<Interview> listAll()	{
		return (List<Interview>) interviewRepo.findAll(Sort.by("id").ascending());
		
	}
	
	public List<Interview> getById(Long id) {
		return interviewRepo.getUserById(id);
	}
	
	public List<Interview> getByIdView(Long id) {
		return interviewRepo.getViewById(id);
	}

	public Interview get(Integer id) throws InterviewNotFoundException {
		try {
			return interviewRepo.findById(id).get();
		}catch (NoSuchElementException ex) {
			throw new InterviewNotFoundException("Could not find any interview with ID " + id);
		}
	}
	
	public Interview getApplicant(Integer applicant) throws InterviewNotFoundException {
		try {
			return interviewRepo.findById(applicant).get();
		}catch (DataIntegrityViolationException e) {
			throw new InterviewNotFoundException("Trainer with ID " + applicant + "already exist");
		}
	}
	
	public void delete(Integer id) throws InterviewNotFoundException {
		Long countById = interviewRepo.countById(id);
		if(countById == null || countById == 0) {
			throw new InterviewNotFoundException("Could not find any interview with ID " + id);
		}
		
		interviewRepo.deleteById(id);
	}
	
	public Interview save(Interview interview) {		
		
		return interviewRepo.save(interview);
		
	}
	
	public Page<Interview> listByPage(int pageNum, String sortField, String sortDir, String keyword){
		Sort sort = Sort.by(sortField);
		
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNum - 1, INTERVIEWS_PER_PAGE, sort);
		
		if(keyword != null) {
			return interviewRepo.findAll(keyword, pageable);
		}
		
		return interviewRepo.findAll(pageable);
	}
	
	
}
