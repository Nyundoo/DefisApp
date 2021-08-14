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

import com.Defis.domain.Medical;
import com.Defis.exception.MedicalNotFoundException;
import com.Defis.repository.MedicalRepository;

@Service
@Transactional
public class MedicalService {
	
public static final int MEDICALS_PER_PAGE = 6;
	
	@Autowired
	private MedicalRepository medicalRepo;
	
	public List<Medical> listAll()	{
		return (List<Medical>) medicalRepo.findAll(Sort.by("applicant").ascending());
		
	}
	
	public List<Medical> getById(Long id) {
		return medicalRepo.getUserById(id);
	}

	public Medical get(Integer id) throws MedicalNotFoundException {
		try {
			return medicalRepo.findById(id).get();
		}catch (NoSuchElementException ex) {
			throw new MedicalNotFoundException("Could not find any medical with ID " + id);
		}
	}
	
	public Medical getApplicant(Integer applicant) throws MedicalNotFoundException {
		try {
			return medicalRepo.findById(applicant).get();
		}catch (DataIntegrityViolationException e) {
			throw new MedicalNotFoundException("Applicant with ID " + applicant + "already exist");
		}
	}
	
	public void delete(Integer id) throws MedicalNotFoundException {
		Long countById = medicalRepo.countById(id);
		if(countById == null || countById == 0) {
			throw new MedicalNotFoundException("Could not find any medical with ID " + id);
		}
		
		medicalRepo.deleteById(id);
	}
	
	public Medical save(Medical medical) {		
		
		return medicalRepo.save(medical);
		
	}
	
	public Page<Medical> listByPage(int pageNum, String sortField, String sortDir, String keyword){
		Sort sort = Sort.by(sortField);
		
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNum - 1, MEDICALS_PER_PAGE, sort);
		
		if(keyword != null) {
			return medicalRepo.findAll(keyword, pageable);
		}
		
		return medicalRepo.findAll(pageable);
	}



	


}
