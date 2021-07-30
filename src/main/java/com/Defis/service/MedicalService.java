package com.Defis.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Defis.domain.Medical;
import com.Defis.repository.MedicalRepository;

@Service
@Transactional
public class MedicalService {
public static final int MEDICALS_PER_PAGE = 6;

	
	@Autowired
	private MedicalRepository medicalRepo;

	


	
	public List<Medical> listAll()	{
		return (List<Medical>) medicalRepo.findAll(Sort.by("firstName").ascending());
		
	}
	



//	public Medical get(Integer id) throws MedicalNotFoundException {
//		try {
//			return medicalRepo.findById(id).get();
//		}catch (NoSuchElementException ex) {
//			throw new MedicalNotFoundException("Could not find any medical with ID " + id);
//		}
//	}
//	
//	public void delete(Integer id) throws MedicalNotFoundException {
//		Long countById = medicalRepo.countById(id);
//		if(countById == null || countById == 0) {
//			throw new MedicalNotFoundException("Could not find any medical with ID " + id);
//		}
//		
//		medicalRepo.deleteById(id);
//	}
	

}
