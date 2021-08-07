package com.Defis;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.Defis.domain.Agent;
import com.Defis.repository.AgentRepository;

public class AgentRepositoryTest {
	@Autowired
	private AgentRepository repo;
	
//	@Autowired
//	private TestEntityManager entityManager;

//	@Test
//	public void testCreateNewAgentWithOneRole() {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String rawPassword = "12345678";
//		String encodedPassword = passwordEncoder.encode(rawPassword); 
//		
//		System.out.print(encodedPassword);
//		Role roleAdmin = entityManager.find(Role.class, 1);
//		Agent agentName = new Agent("arthur@gmail.com", "art2021", "Arthur", "Lukhoni");
//		agentName.addRole(roleAdmin);
//		
//		Agent savedAgent = repo.save(agentName);
//		
//		assertThat(savedAgent.getId()).isGreaterThan(0);
//	}
//	
//	
//	@Test
//	public void testCreateNewAgentWithTwoRoles() {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String rawPassword = "admin2021";
//		String encodedPassword = passwordEncoder.encode(rawPassword); 
//		
//		System.out.print(encodedPassword);
//		
//		Agent agentRavi = new Agent("admin2@gmail.com",encodedPassword, "admin2", "admin2");
//		Role roleEditor = new Role(1);
//		Role roleAssistant = new Role(5);
//		
//		agentRavi.addRole(roleEditor);
//		userRavi.addRole(roleAssistant);
//		
//		User savedUser = repo.save(userRavi);
//		
//		assertThat(savedUser.getId()).isGreaterThan(0);
//	}
	
//	@Test
//	public void testListAllUsers() {
//		Iterable<User> listUsers = repo.findAll();
//		listUsers.forEach(user -> System.out.print(user));
//		
//	}
//	
//	@Test
//	public void testUserById() {
//		
//		User userName = repo.findById(1).get();
//		System.out.print(userName);
//		assertThat(userName).isNotNull();
//	}
//
//	@Test
//	public void testUpdateUserDetails() {
//		User userName = repo.findById(1).get();
//		userName.setEnabled(true);
//		userName.setEmail("arthur@gmail.com");
//		
//		repo.save(userName);
//	}
//	
//	@Test
//	public void testUpdateUserRoles() {
//		User userRavi = repo.findById(2).get();
//		Role roleEditor = new Role(3);
//		Role roleSalesPerson = new Role(2);
//		
//		userRavi.getRoles().remove(roleEditor);
//		userRavi.addRole(roleSalesPerson);
//		
//		repo.save(userRavi);
//	}
//	
//	@Test
//	public void testDeleteUser() {
//		Integer userId = 2;
//		repo.deleteById(userId);
//	}
//	
//	@Test
//	public void testGetUserByEmail() {
//		String email = "nyundoo@yahoo.com";
//		User user = repo.getUserByEmail(email);
//		
//		assertThat(user).isNotNull();
//	}
//	
//	@Test
//	public void testCountById() {
//		Integer id = 1;
//		Long countById = repo.countById(id);
//		
//		assertThat(countById).isNotNull().isGreaterThan(0);
//	}
//	
//	@Test
//	public void testDisableUser() {
//		Integer id = 4;
//		repo.updateEnabledStatus(id, false);
//	}
//	
//	
	
//	@Test
//	public void testListFirstPage() {
//		int pageNumber = 0;
//		int pageSize = 4;
//		
//		PageRequest pageable = PageRequest.of(pageNumber, pageSize);
//		Page<Agent> page = repo.findAll(pageable);
//		
//		List<Agent> listAgents = page.getContent();
//		
//		listAgents.forEach(agent -> System.out.println(agent));
//		
//		assertThat(listAgents.size()).isEqualTo(pageSize);
//	}
//	
//	@Test
//	public void testListSearchAgents() {
//		String keyword = "admin";
//		
//		int pageNumber = 0;
//		int pageSize = 4;
//		
//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
//		Page<Agent> page = repo.findAll(keyword, pageable);
//		
//		List<Agent> listAgents = page.getContent();
//		
//		listAgents.forEach(agent -> System.out.println(agent));
//		
//		assertThat(listAgents.size()).isGreaterThan(0);
//	}
}
