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
	
//	@Autowired
//	private RoleRepository repo;
//	
//	@Test
//	public void testCreateFirstRole() {
//		Role roleAdmin = new Role(null, "Admin", "manage everything");
//		Role savedRole = repo.save(roleAdmin);
//		
//		assertThat(savedRole.getId()).isGreaterThan(0);
//	}
//	
//	@Test
//	public void testCreateRestRoles() {
//		Role rolePayment = new Role(null, "Payments", "Input users payment");
//		Role roleMedical = new Role(null, "Medicals", "Input users medicals");
//		Role roleBirth = new Role(null, "Births", "Insert user birth certificate details");
//		Role roleJob = new Role(null, "Jobs", "Input user jobs");
//		Role roleAgent = new Role(null, "Agents", "Input agent information");
//		Role roleApplicant = new Role(null, "Applicants", "Input new applicant information");
//		Role roleTraining = new Role(null, "Trainings", "Input training time schedules");
//		Role roleTicket = new Role(null, "Tickets", "Input applicant traveling schedule");
//		Role rolePassports = new Role(null, "Passports", "Insert applicant passport information");
//		Role roleMarketers = new Role(null, "Marketers", "Deign poster for new job posting");
//		Role roleTasks = new Role(null, "Tasks", "Check for task schedule");
//		Role roleVisas = new Role(null, "Visas", "Insert applicant visa details");
//
//
//		
//		 repo.saveAll(List.of(rolePayment, roleMedical, roleJob, roleAgent, roleApplicant, roleTraining, roleTicket, roleMarketers, rolePassports, roleBirth, roleTasks, roleVisas));
//		
//	}

}
