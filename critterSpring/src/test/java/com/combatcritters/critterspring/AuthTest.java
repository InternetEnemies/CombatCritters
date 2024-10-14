package com.combatcritters.critterspring;

import com.combatcritters.critterspring.auth.AuthController;
import com.combatcritters.critterspring.auth.payloads.LoginPayload;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.objects.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
public class AuthTest {
    private MockMvc mockMvc;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @MockBean
    private IUserManager userManager;
    
    @BeforeEach
    public void setup(){
        
        mockMvc = MockMvcBuilders.standaloneSetup(new AuthController(authenticationManager,passwordEncoder,userManager)).build();
    }
    
    @Test
    public void test_login() throws Exception {
        String pass = "password";
        User user = new User(1,"user",passwordEncoder.encode(pass));
        when(userManager.getUserByUsername(user.getUsername())).thenReturn(user);
        mockMvc.perform(post("/users/auth/login")
                .content(TestUtils.toJson(new LoginPayload(user.getUsername(),pass)))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}
