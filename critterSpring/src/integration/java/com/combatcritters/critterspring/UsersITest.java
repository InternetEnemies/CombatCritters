package com.combatcritters.critterspring;

import com.combatcritters.critterspring.auth.payloads.RegisterPayload;
import com.combatcritters.critterspring.auth.payloads.UserPayload;
import com.combatcritters.critterspring.routes.UsersController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
@ActiveProfiles("integration")
public class UsersITest extends IntegrationTest{
    @Test
    public void test_getUsers()throws Exception{
        createUsers(5);
        
        MvcResult result = mockMvc.perform(get("/admin/users")).andExpect(status().isOk()).andReturn();
        List<UserPayload> payloads = TestUtils.fromJson(result.getResponse().getContentAsString(), new TypeReference<List<UserPayload>>() {});
        Assert.isTrue(payloads.size() == 5, "wrong number of users");
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
