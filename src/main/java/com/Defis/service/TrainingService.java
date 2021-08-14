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

import com.Defis.domain.Training;
import com.Defis.exception.TrainingNotFoundException;
import com.Defis.repository.TrainingRepository;

@Service
@Transactional
public class TrainingService {


	public static final int TRAININGS_PER_PAGE = 6;
	@Autowired
	private TrainingRepository trainingRepo;
	
	
	
	public List<Training> listAll()	{
		return (List<Training>) trainingRepo.findAll(Sort.by("applicant").ascending());
		
	}
	
	public List<Training> getById(Long id) {
		return trainingRepo.getUserById(id);
	}

	public Training get(Integer id) throws TrainingNotFoundException {
		try {
			return trainingRepo.findById(id).get();
		}catch (NoSuchElementException ex) {
			throw new TrainingNotFoundException("Could not find any training with ID " + id);
		}
	}
	
	public Training getApplicant(Integer applicant) throws TrainingNotFoundException {
		try {
			return trainingRepo.findById(applicant).get();
		}catch (DataIntegrityViolationException e) {
			throw new TrainingNotFoundException("Trainer with ID " + applicant + "already exist");
		}
	}
	
	public void delete(Integer id) throws TrainingNotFoundException {
		Long countById = trainingRepo.countById(id);
		if(countById == null || countById == 0) {
			throw new TrainingNotFoundException("Could not find any training with ID " + id);
		}
		
		trainingRepo.deleteById(id);
	}
	
	public Training save(Training training) {		
		
		return trainingRepo.save(training);
		
	}
	
	public Page<Training> listByPage(int pageNum, String sortField, String sortDir, String keyword){
		Sort sort = Sort.by(sortField);
		
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNum - 1, TRAININGS_PER_PAGE, sort);
		
		if(keyword != null) {
			return trainingRepo.findAll(keyword, pageable);
		}
		
		return trainingRepo.findAll(pageable);
	}
	
	
}
