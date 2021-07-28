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
		Role roleSalesPerson = new Role(null, "ICT", "input users data, Update records,"
				+ "customers");
		Role roleEditor = new Role(null, "Assistant", "Carry out timely required tasks,"
				+ "articles and menus");
		Role roleShipper = new Role(null, "Receptionist", "welcomes clients, maintain documents,"
				+ "and update customers status");
		Role roleAssistance = new Role(null, "Manager", "manage questions and reviews");
		
		 repo.saveAll(List.of(roleSalesPerson, roleEditor, roleShipper, roleAssistance));
		
	}

}
