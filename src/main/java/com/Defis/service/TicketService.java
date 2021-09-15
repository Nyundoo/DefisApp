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

import com.Defis.domain.Passport;
import com.Defis.domain.Ticket;
import com.Defis.exception.TicketNotFoundException;
import com.Defis.repository.TicketRepository;

@Service
@Transactional
public class TicketService {
	
	public static final int TICKETS_PER_PAGE = 15;
	@Autowired
	private TicketRepository ticketRepo;
	
	
	
	public List<Ticket> listAll()	{
		return (List<Ticket>) ticketRepo.findAll(Sort.by("applicant").ascending());
		
	}
	
	public List<Ticket> getById(Long id) {
		return ticketRepo.getUserById(id);
	}
	
	public List<Ticket> getByIdView(Long id) {
		return ticketRepo.getViewById(id);
	}

	public Ticket get(Integer id) throws TicketNotFoundException {
		try {
			return ticketRepo.findById(id).get();
		}catch (NoSuchElementException ex) {
			throw new TicketNotFoundException("Could not find any ticket with ID " + id);
		}
	}
	
	public Ticket getApplicant(Integer applicant) throws TicketNotFoundException {
		try {
			return ticketRepo.findById(applicant).get();
		}catch (DataIntegrityViolationException e) {
			throw new TicketNotFoundException("Trainer with ID " + applicant + "already exist");
		}
	}
	
	public void delete(Integer id) throws TicketNotFoundException {
		Long countById = ticketRepo.countById(id);
		if(countById == null || countById == 0) {
			throw new TicketNotFoundException("Could not find any ticket with ID " + id);
		}
		
		ticketRepo.deleteById(id);
	}
	
	public Ticket save(Ticket ticket) {		
		
		return ticketRepo.save(ticket);
		
	}
	
	public Page<Ticket> listByPage(int pageNum, String sortField, String sortDir, String keyword){
		Sort sort = Sort.by(sortField);
		
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNum - 1, TICKETS_PER_PAGE, sort);
		
		if(keyword != null) {
			return ticketRepo.findAll(keyword, pageable);
		}
		
		return ticketRepo.findAll(pageable);
	}
}
