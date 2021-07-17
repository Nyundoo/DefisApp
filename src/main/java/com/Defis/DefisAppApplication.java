package com.Defis;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Defis.domain.User;
import com.Defis.domain.security.Role;
import com.Defis.domain.security.UserRole;
import com.Defis.service.UserService;
import com.Defis.utility.SecurityUtility;

@SpringBootApplication
public class DefisAppApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(DefisAppApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setFirstName("Arthur");
		user1.setLastName("Lukhoni");
		user1.setUsername("a");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user1.setEmail("lukhoniarthur@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1= new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
	}
}
