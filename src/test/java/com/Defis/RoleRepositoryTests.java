package com.Defis;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.Defis.domain.Role;
import com.Defis.repository.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
	
	@Autowired
	private RoleRepository repo;
	
	@Test
	public void testCreateFirstRole() {
		Role roleAdmin = new Role(null, "Admin", "manage everything");
		Role savedRole = repo.save(roleAdmin);
		
		assertThat(savedRole.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateRestRoles() {
		Role rolePayment = new Role(null, "Payments", "Input users payment");
		Role roleMedical = new Role(null, "Medicals", "Input users medicals");
		Role roleJob = new Role(null, "Jobs", "Input user jobs");
		Role roleAgent = new Role(null, "Agents", "Input agent information");
		Role roleApplicant = new Role(null, "Applicants", "Input new applicant information");
		Role roleTraining = new Role(null, "Trainings", "Input training time schedules");
		Role roleTicket = new Role(null, "Tickets", "Inpu applicant traveling schedule");
		
		 repo.saveAll(List.of(rolePayment, roleMedical, roleJob, roleAgent, roleApplicant, roleTraining, roleTicket));
		
	}

}
