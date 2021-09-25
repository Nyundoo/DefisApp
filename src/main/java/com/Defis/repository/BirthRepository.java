package com.Defis.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Defis.domain.Birth;

@Repository
public interface BirthRepository extends PagingAndSortingRepository<Birth, Integer> {

	public Long countById(Integer id);
	
	@Query("SELECT u FROM Birth u WHERE (u.user2.id=?#{ principal.id } AND u.status = false) OR (u.user2.id=?#{ principal.id } AND u.status = true AND year(u.cert_reception_date) - year(CURRENT_DATE) = 0 AND month(u.cert_reception_date) - month(CURRENT_DATE) = 0 AND day(u.cert_reception_date) - day(CURRENT_DATE) BETWEEN -1 AND 1)")
	public List<Birth> getBirthById(@Param("id") long id);
	
	@Query("SELECT u FROM Birth u WHERE u.status = false OR (u.status = true AND year(u.cert_reception_date) - year(CURRENT_DATE) = 0 AND month(u.cert_reception_date) - month(CURRENT_DATE) = 0 AND day(u.cert_reception_date) - day(CURRENT_DATE) BETWEEN -1 AND 1)")
	public List<Birth> getViewById(@Param("id") long id);

	@Query("SELECT u FROM Birth u WHERE CONCAT(u.id, ' ',u.applicant, ' ',u.cert_no, ' ',u.user2) LIKE %?1%")
	public Page<Birth> findAll(String keyword, Pageable pageable);
	
	

}
