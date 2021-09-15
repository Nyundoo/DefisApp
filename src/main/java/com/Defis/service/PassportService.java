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

import com.Defis.domain.Birth;
import com.Defis.domain.Passport;
import com.Defis.exception.PassportNotFoundException;
import com.Defis.repository.PassportRepository;

@Service
@Transactional
public class PassportService {

public static final int PASSPORTS_PER_PAGE = 15;
	
	@Autowired
	private PassportRepository passportRepo;
	
	public List<Passport> listAll()	{
		return (List<Passport>) passportRepo.findAll(Sort.by("applicant").ascending());
		
	}
	
	public List<Passport> getById(Long id) {
		return passportRepo.getUserById(id);
	}
	
	public List<Passport> getByIdView(Long id) {
		return passportRepo.getViewById(id);
	}

	public Passport get(Integer id) throws PassportNotFoundException {
		try {
			return passportRepo.findById(id).get();
		}catch (NoSuchElementException ex) {
			throw new PassportNotFoundException("Could not find any passport with ID " + id);
		}
	}
	
	public Passport getApplicant(Integer applicant) throws PassportNotFoundException {
		try {
			return passportRepo.findById(applicant).get();
		}catch (DataIntegrityViolationException e) {
			throw new PassportNotFoundException("Applicant with ID " + applicant + "already exist");
		}
	}
	
	public void delete(Integer id) throws PassportNotFoundException {
		Long countById = passportRepo.countById(id);
		if(countById == null || countById == 0) {
			throw new PassportNotFoundException("Could not find any passport with ID " + id);
		}
		
		passportRepo.deleteById(id);
	}
	
	public Passport save(Passport passport) {		
		
		return passportRepo.save(passport);
		
	}
	
	public Page<Passport> listByPage(int pageNum, String sortField, String sortDir, String keyword){
		Sort sort = Sort.by(sortField);
		
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNum - 1, PASSPORTS_PER_PAGE, sort);
		
		if(keyword != null) {
			return passportRepo.findAll(keyword, pageable);
		}
		
		return passportRepo.findAll(pageable);
	}
}
