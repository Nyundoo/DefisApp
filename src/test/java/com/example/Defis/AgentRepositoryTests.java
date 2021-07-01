package com.example.Defis;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.Defis.domain.Agent;
import com.Defis.repository.AgentRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class AgentRepositoryTests {
	
	@Autowired
	private AgentRepository repo;
	
	@Test
	public void testCreateAgent() {
		Agent savedAgent = repo.save(new Agent("Electronics"));
		
		assertThat(savedAgent.getId()).isGreaterThan(0);
	}

}
