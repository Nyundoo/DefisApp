package com.Defis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.Defis.repository.UserRepository;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class EnableUserTest {
	
//	@Autowired
//	private UserRepository repo;
//	
//	@Test
//	public void testEnableUser() {
//		Integer id = 1;
//		repo.updateEnabledStatus(id, true);
//	}

}
