package com.Defis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Defis.domain.Applicant;
import com.Defis.domain.Jobs;
import com.Defis.exception.ApplicantNotFoundException;
import com.Defis.repository.ApplicantRepository;
import com.Defis.repository.JobsRepository;

@Service
@Transactional
public class ApplicantService {
	public static final int APPLICANTS_PER_PAGE = 15;

	@Autowired
	private ApplicantRepository applicantRepo;

	@Autowired
	private JobsRepository jobsRepo;

	public Applicant getByEmail(String email) {
		return applicantRepo.getApplicantByEmail(email);
	}
	
	public List<Applicant> findAll() {
		List<Applicant> applicantList = (List<Applicant>) applicantRepo.findAll();
		List<Applicant> activeApplicantList = new ArrayList();
		
		for (Applicant applicant: applicantList) {
			
			activeApplicantList.add(applicant);
		
	}
		
		return activeApplicantList;
	}

	public List<Applicant> listAll() {
		return (List<Applicant>) applicantRepo.findAll(Sort.by("id").ascending());

	}

	public Page<Applicant> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);

		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

		Pageable pageable = PageRequest.of(pageNum - 1, APPLICANTS_PER_PAGE, sort);

		if (keyword != null) {
			return applicantRepo.findAll(keyword, pageable);
		}

		return applicantRepo.findAll(pageable);
	}
	
	public List<Jobs> listJobss(){
		return (List<Jobs>) jobsRepo.findAll();
	}

	public Applicant save(Applicant applicant) {

		return applicantRepo.save(applicant);

	}

	public boolean isEmailUnique(Integer id, String email) {
		Applicant applicantByEmail = applicantRepo.getApplicantByEmail(email);

		if (applicantByEmail == null)
			return true;

		boolean isCreatingNew = (id == null);

		if (isCreatingNew) {
			if (applicantByEmail != null)
				return false;
		} else {
			if (applicantByEmail.getId() != id) {
				return false;
			}
		}

		return true;
	}

	public Applicant get(Integer id) throws ApplicantNotFoundException {
		try {
			return applicantRepo.findById(id).get();
		} catch (NoSuchElementException ex) {
			throw new ApplicantNotFoundException("Could not find any Applicant with ID " + id);
		}
	}

	public void delete(Integer id) throws ApplicantNotFoundException {
		Long countById = applicantRepo.countById(id);
		if (countById == null || countById == 0) {
			throw new ApplicantNotFoundException("Could not find any Applicant with ID " + id);
		}

		applicantRepo.deleteById(id);
	}

}
