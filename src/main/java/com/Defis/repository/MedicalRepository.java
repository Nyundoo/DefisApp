package com.Defis.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Defis.domain.Medical;

@Repository
public interface MedicalRepository extends PagingAndSortingRepository<Medical, Integer> {
	
public Long countById(Integer id);	

@Query("SELECT u FROM Medical u WHERE (u.user1.id=?#{ principal.id } AND u.m_status = false) OR (u.user1.id=?#{ principal.id } AND u.m_status = true AND year(u.result_date) - year(CURRENT_DATE) = 0 AND month(u.result_date) - month(CURRENT_DATE) = 0 AND day(u.result_date) - day(CURRENT_DATE) BETWEEN -1 AND 1)")
public List<Medical> getUserById(@Param("id") long id);

@Query("SELECT u FROM Medical u WHERE u.m_status = false OR (u.m_status = true AND year(u.result_date) - year(CURRENT_DATE) = 0 AND month(u.result_date) - month(CURRENT_DATE) = 0 AND day(u.result_date) - day(CURRENT_DATE) BETWEEN -1 AND 1)")
public List<Medical> getViewById();
	
	@Query("SELECT u FROM Medical u WHERE CONCAT(u.id, ' ',u.client_info, ' ',u.medical_center, ' ',u.medical_type) LIKE %?1%")
	public Page<Medical> findAll(String keyword, Pageable pageable);

	
}
