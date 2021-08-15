package com.Defis.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Defis.domain.Birth;
import com.Defis.domain.Medical;

@Repository
public interface BirthRepository extends PagingAndSortingRepository<Birth, Integer> {

	public Long countById(Integer id);

	@Query("SELECT u FROM Birth u WHERE u.user2.id=?#{ principal.id }")
	public List<Birth> getBirthById(@Param("id") long id);
	
	@Query("SELECT u FROM Birth u")
	public List<Birth> getViewById(@Param("id") long id);

	@Query("SELECT u FROM Birth u WHERE CONCAT(u.id, ' ',u.applicant, ' ',u.cert_no, ' ',u.user2) LIKE %?1%")
	public Page<Birth> findAll(String keyword, Pageable pageable);

}
