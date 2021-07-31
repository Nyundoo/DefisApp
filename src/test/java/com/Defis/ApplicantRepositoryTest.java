package com.Defis;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.Defis.domain.Applicant;
import com.Defis.repository.ApplicantRepository;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ApplicantRepositoryTest {
//	@Autowired
//	private ApplicantRepository repo;
//	
//	@Autowired
//	private TestEntityManager entityManager;
//
////	@Test
////	public void testCreateNewUserWithOneRole() {
////		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
////		String rawPassword = "12345678";
////		String encodedPassword = passwordEncoder.encode(rawPassword); 
////		
////		System.out.print(encodedPassword);
////		Role roleAdmin = entityManager.find(Role.class, 1);
////		User userName = new User("arthur@gmail.com", "art2021", "Arthur", "Lukhoni");
////		userName.addRole(roleAdmin);
////		
////		User savedUser = repo.save(userName);
////		
////		assertThat(savedUser.getId()).isGreaterThan(0);
////	}
//	
//	
//	@Test
//	public void testCreateNewApplicant() {	
//		
//		Applicant applicantRavi = new Applicant("Arte", "Two", "arte@gmail.com");
//		
//		Applicant savedApplicant = repo.save(applicantRavi);
//		
//		assertThat(savedApplicant.getId()).isGreaterThan(0);
//	}
//	
//	@Test
//	public void testListAllApplicants() {
//		Iterable<Applicant> listApplicants = repo.findAll();
//		listApplicants.forEach(applicant -> System.out.print(applicant));
//		
//	}
//	
//	@Test
//	public void testApplicantsById() {
//		
//		Applicant applicantName = repo.findById(1).get();
//		System.out.print(applicantName);
//		assertThat(applicantName).isNotNull();
//	}
//
//	@Test
//	public void testUpdateApplicantsDetails() {
//		Applicant applicantName = repo.findById(1).get();
//		applicantName.setEmail("arthur@gmail.com");
//		
//		repo.save(applicantName);
//	}
//	
//	@Test
//	public void testDeleteApplicants() {
//		Integer applicantId = 1;
//		repo.deleteById(applicantId);
//	}
//	
//	@Test
//	public void testGetApplicantByEmail() {
//		String email = "arte@gmail.com";
//		Applicant applicant = repo.getApplicantByEmail(email);
//		
//		assertThat(applicant).isNotNull();
//	}
//	
//
//	@Test
//	public void testListFirstPage() {
//		int pageNumber = 0;
//		int pageSize = 1;
//		
//		PageRequest pageable = PageRequest.of(pageNumber, pageSize);
//		Page<Applicant> page = repo.findAll(pageable);
//		
//		List<Applicant> listApplicants = page.getContent();
//		
//		listApplicants.forEach(applicant -> System.out.println(applicant));
//		
//		assertThat(listApplicants.size()).isEqualTo(pageSize);
//	}
//	
//	@Test
//	public void testListSearchApplicants() {
//		String keyword = "Two";
//		
//		int pageNumber = 0;
//		int pageSize = 4;
//		
//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
//		Page<Applicant> page = repo.findAll(keyword, pageable);
//		
//		List<Applicant> listApplicants = page.getContent();
//		
//		listApplicants.forEach(applicant -> System.out.println(applicant));
//		
//		assertThat(listApplicants.size()).isGreaterThan(0);
//	}
}
