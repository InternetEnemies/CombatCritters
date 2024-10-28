package com.combatcritters.critterspring;

import com.combatcritters.critterspring.auth.payloads.RegisterPayload;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
@ActiveProfiles("integration")
public class IntegrationTest {
    public MockMvc mockMvc;
    
    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    protected void createUser() throws Exception {
        mockMvc.perform(post("/users/auth/register")
                .content(TestUtils.toJson(new RegisterPayload("user", "pass")))
                .contentType(MediaType.APPLICATION_JSON));
    }
}
