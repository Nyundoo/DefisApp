package com.Defis.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Defis.domain.Medical;
import com.Defis.domain.Ticket;

@Repository
public interface TicketRepository extends PagingAndSortingRepository<Ticket, Integer> {
	
public Long countById(Integer id);	

@Query("SELECT u FROM Ticket u WHERE u.user4.id=?#{ principal.id }")
public List<Ticket> getUserById(@Param("id") long id);
	
	@Query("SELECT u FROM Ticket u WHERE CONCAT(u.applicant, ' ',u.ticket_date, ' ',u.destination, ' ',u.date_travel,' ',u.travel_status) LIKE %?1%")
	public Page<Ticket> findAll(String keyword, Pageable pageable);
}
