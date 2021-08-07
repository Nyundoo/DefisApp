package com.Defis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Defis.domain.Ticket;

public interface TicketRepository extends PagingAndSortingRepository<Ticket, Integer> {
	
public Long countById(Integer id);	
	
	@Query("SELECT u FROM Ticket u WHERE CONCAT(u.applicant, ' ',u.ticket_date, ' ',u.destination, ' ',u.date_travel,' ',u.travel_status) LIKE %?1%")
	public Page<Ticket> findAll(String keyword, Pageable pageable);
}
