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

import com.Defis.domain.Visa;
import com.Defis.exception.VisaNotFoundException;
import com.Defis.repository.VisaRepository;

@Service
@Transactional
public class VisaService {

	
public static final int VISAS_PER_PAGE = 15;
	
	@Autowired
	private VisaRepository visaRepo;
	
	public List<Visa> listAll()	{
		return (List<Visa>) visaRepo.findAll(Sort.by("applicant").ascending());
		
	}
	
	public List<Visa> getById(Long id) {
		return visaRepo.getUserById(id);
	}
	
	public List<Visa> getByIdView(Long id) {
		return visaRepo.getViewById(id);
	}

	public Visa get(Integer id) throws VisaNotFoundException {
		try {
			return visaRepo.findById(id).get();
		}catch (NoSuchElementException ex) {
			throw new VisaNotFoundException("Could not find any visa with ID " + id);
		}
	}
	
	public Visa getApplicant(Integer applicant) throws VisaNotFoundException {
		try {
			return visaRepo.findById(applicant).get();
		}catch (DataIntegrityViolationException e) {
			throw new VisaNotFoundException("Applicant with ID " + applicant + "already exist");
		}
	}
	
	public void delete(Integer id) throws VisaNotFoundException {
		Long countById = visaRepo.countById(id);
		if(countById == null || countById == 0) {
			throw new VisaNotFoundException("Could not find any visa with ID " + id);
		}
		
		visaRepo.deleteById(id);
	}
	
	public Visa save(Visa visa) {		
		
		return visaRepo.save(visa);
		
	}
	
	public Page<Visa> listByPage(int pageNum, String sortField, String sortDir, String keyword){
		Sort sort = Sort.by(sortField);
		
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNum - 1, VISAS_PER_PAGE, sort);
		
		if(keyword != null) {
			return visaRepo.findAll(keyword, pageable);
		}
		
		return visaRepo.findAll(pageable);
	}
}
