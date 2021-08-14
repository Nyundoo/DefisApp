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
import com.Defis.exception.BirthNotFoundException;
import com.Defis.repository.BirthRepository;

@Service
@Transactional
public class BirthService {

	public static final int BIRTHCERTS_PER_PAGE = 6;

	@Autowired
	private BirthRepository birthcertRepo;

	public List<Birth> listAll() {
		return (List<Birth>) birthcertRepo.findAll(Sort.by("applicant").ascending());

	}

	public List<Birth> getById(Long id) {
		return birthcertRepo.getBirthById(id);
	}

	public Birth get(Integer id) throws BirthNotFoundException {
		try {
			return birthcertRepo.findById(id).get();
		} catch (NoSuchElementException ex) {
			throw new BirthNotFoundException("Could not find any birthcert with ID " + id);
		}
	}

	public Birth getApplicant(Integer applicant) throws BirthNotFoundException {
		try {
			return birthcertRepo.findById(applicant).get();
		} catch (DataIntegrityViolationException e) {
			throw new BirthNotFoundException("Applicant with ID " + applicant + "already exist");
		}
	}

	public void delete(Integer id) throws BirthNotFoundException {
		Long countById = birthcertRepo.countById(id);
		if (countById == null || countById == 0) {
			throw new BirthNotFoundException("Could not find any birthcert with ID " + id);
		}

		birthcertRepo.deleteById(id);
	}

	public Birth save(Birth birthcert) {

		return birthcertRepo.save(birthcert);

	}

	public Page<Birth> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);

		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

		Pageable pageable = PageRequest.of(pageNum - 1, BIRTHCERTS_PER_PAGE, sort);

		if (keyword != null) {
			return birthcertRepo.findAll(keyword, pageable);
		}

		return birthcertRepo.findAll(pageable);
	}

}
