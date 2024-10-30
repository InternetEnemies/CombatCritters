package com.combatcritters.critterspring;

import com.combatcritters.critterspring.auth.payloads.LoginPayload;
import com.combatcritters.critterspring.auth.payloads.RegisterPayload;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
@ActiveProfiles("integration")
public class IntegrationTest {
    public MockMvc mockMvc;
    protected static final String DUMMY = "Dummy";
    
    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    protected void createUser() throws Exception {
        mockMvc.perform(post("/users/auth/register")
                .content(TestUtils.toJson(new RegisterPayload(DUMMY, DUMMY)))
                .contentType(MediaType.APPLICATION_JSON));
    }
    
    protected void login() throws Exception {
        createUser();
        mockMvc.perform(post("/users/auth/login")
                .content(TestUtils.toJson(new LoginPayload(DUMMY, DUMMY)))
                .contentType(MediaType.APPLICATION_JSON));
    }
    
    protected Principal getPrincipal() {
        return new DummyPrincipal(DUMMY);
    }
}
