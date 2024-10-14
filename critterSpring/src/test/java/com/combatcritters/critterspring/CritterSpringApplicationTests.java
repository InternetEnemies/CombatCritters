package com.combatcritters.critterspring;

import com.combatcritters.critterspring.auth.payloads.LoginPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
class CritterSpringApplicationTests {
	
	MockHttpSession session;
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(new CritterSpringApplication()).build();
		session = new MockHttpSession();
		mockMvc.perform(post("/users/auth/login").session(session).content(mapper.writeValueAsString(new LoginPayload("test","test"))));
	}

	@Test
	void test_ping() throws Exception {
		mockMvc.perform(get("/ping").session(session));
	}

}
