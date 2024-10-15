package com.combatcritters.critterspring.unit;

import com.combatcritters.critterspring.CritterSpringApplication;
import com.combatcritters.critterspring.DummyPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
class CritterSpringApplicationTests {
	
	private MockMvc mockMvc;
	private Principal user;
	
	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(new CritterSpringApplication()).build();
		user = new DummyPrincipal("user");
	}

	@Test
	void test_ping() throws Exception {
		mockMvc.perform(get("/ping").principal(user));
	}

}
