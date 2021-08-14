package com.Defis.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Defis.domain.Job;
import com.Defis.exception.JobNotFoundException;
import com.Defis.repository.JobRepository;

@Service
@Transactional
public class JobService {

public static final int JOBS_PER_PAGE = 6;

	
	@Autowired
	private JobRepository jobRepo;
	

	
	public List<Job> listAll()	{
		return (List<Job>) jobRepo.findAll(Sort.by("jobTitle").ascending());
		
	}
	
	public Page<Job> listByPage(int pageNum, String sortField, String sortDir, String keyword){
		Sort sort = Sort.by(sortField);
		
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNum - 1, JOBS_PER_PAGE, sort);
		
		if(keyword != null) {
			return jobRepo.findAll(keyword, pageable);
		}
		
		return jobRepo.findAll(pageable);
	}
	
	

	public Job save(Job job) {		
	
		return jobRepo.save(job);
		
	}
	


	public Job get(Integer id) throws JobNotFoundException {
		try {
			return jobRepo.findById(id).get();
		}catch (NoSuchElementException ex) {
			throw new JobNotFoundException("Could not find any job with ID " + id);
		}
	}
	
	public void delete(Integer id) throws JobNotFoundException {
		Long countById = jobRepo.countById(id);
		if(countById == null || countById == 0) {
			throw new JobNotFoundException("Could not find any job with ID " + id);
		}
		
		jobRepo.deleteById(id);
	}
	
}
