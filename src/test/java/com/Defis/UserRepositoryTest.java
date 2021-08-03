package com.Defis;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.Defis.domain.Role;
import com.Defis.domain.User;
import com.Defis.repository.UserRepository;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	@Autowired
	private UserRepository repo;
	
//	@Autowired
//	private TestEntityManager entityManager;

//	@Test
//	public void testCreateNewUserWithOneRole() {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String rawPassword = "12345678";
//		String encodedPassword = passwordEncoder.encode(rawPassword); 
//		
//		System.out.print(encodedPassword);
//		Role roleAdmin = entityManager.find(Role.class, 1);
//		User userName = new User("arthur@gmail.com", "art2021", "Arthur", "Lukhoni");
//		userName.addRole(roleAdmin);
//		
//		User savedUser = repo.save(userName);
//		
//		assertThat(savedUser.getId()).isGreaterThan(0);
//	}
//	
//	
	@Test
	public void testCreateNewUserWithTwoRoles() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = "admin2021";
		String encodedPassword = passwordEncoder.encode(rawPassword); 
		
		System.out.print(encodedPassword);
		
		User userRavi = new User("admin3@gmail.com",encodedPassword, "admin3", "admin3");
		Role roleEditor = new Role(1);
		Role roleAssistant = new Role(5);
		
		userRavi.addRole(roleEditor);
		userRavi.addRole(roleAssistant);
		
		User savedUser = repo.save(userRavi);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.print(user));
		
	}
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
//		Role roleEditor = new Role(1);
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
//	public void testEnableUser() {
//		Integer id = 1;
//		repo.updateEnabledStatus(id, true);
//	}
//	
//	
//	@Test
//	public void testListFirstPage() {
//		int pageNumber = 0;
//		int pageSize = 4;
//		
//		PageRequest pageable = PageRequest.of(pageNumber, pageSize);
//		Page<User> page = repo.findAll(pageable);
//		
//		List<User> listUsers = page.getContent();
//		
//		listUsers.forEach(user -> System.out.println(user));
//		
//		assertThat(listUsers.size()).isEqualTo(pageSize);
//	}
//	
	@Test
	public void testListSearchUsers() {
		String keyword = "admin";
		
		int pageNumber = 0;
		int pageSize = 4;
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = repo.findAll(keyword, pageable);
		
		List<User> listUsers = page.getContent();
		
		listUsers.forEach(user -> System.out.println(user));
		
		assertThat(listUsers.size()).isGreaterThan(0);
	}
}
