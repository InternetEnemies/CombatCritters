package com.combatcritters.critterspring;

import com.combatcritters.critterspring.auth.payloads.LoginPayload;
import com.combatcritters.critterspring.auth.payloads.RegisterPayload;
import com.combatcritters.critterspring.auth.payloads.UserPayload;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@ActiveProfiles("integration")
public class UsersITest extends IntegrationTest{
    @Test
    public void test_getUsers()throws Exception{
        createUsers(5);
        
        MvcResult result = mockMvc.perform(get("/admin/users")).andExpect(status().isOk()).andReturn();
        List<UserPayload> payloads = TestUtils.fromJson(result.getResponse().getContentAsString());
        Assert.isTrue(payloads.size() == 5, "wrong number of users");
    }
    
    @Test
    public void test_banUser() throws Exception {
        String username = "username";
        String password = "password";
        //create user
        mockMvc.perform(post("/users/auth/register")
                .content(TestUtils.toJson(new RegisterPayload(username,password)))
                .contentType(MediaType.APPLICATION_JSON));
        MvcResult loginResult = mockMvc.perform(post("/users/auth/login")
                .content(TestUtils.toJson(new LoginPayload(username,password)))
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        // ban user
        Map<String,?> user = TestUtils.fromJson(loginResult.getResponse().getContentAsString());
        mockMvc.perform(delete("/admin/users/" + user.get("id"))).andExpect(status().isNoContent());
        // try to login as banned user
        mockMvc.perform(post("/users/auth/login")
                .content(TestUtils.toJson(new LoginPayload(username,password)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
    
    private void createUsers(int n) throws Exception {
        for (int i = 0; i < n; i++) {
            mockMvc.perform(post("/users/auth/register")
                    .content(TestUtils.toJson(new RegisterPayload("user" + i, "pass")))
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isCreated());
        }
    }
}
