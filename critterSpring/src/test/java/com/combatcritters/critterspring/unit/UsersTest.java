package com.combatcritters.critterspring.unit;

import com.combatcritters.critterspring.TestUtils;
import com.combatcritters.critterspring.auth.payloads.UserPayload;
import com.combatcritters.critterspring.routes.UsersController;
import com.internetEnemies.combatCritters.Logic.exceptions.UserNotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.Logic.users.UserManager;
import com.internetEnemies.combatCritters.objects.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
@ActiveProfiles("unit")
public class UsersTest {
    public MockMvc mockMvc;
    public IUserManager userManager;
    @BeforeEach
    public void setup(){
        this.userManager = mock(UserManager.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new UsersController(this.userManager)).build();
    }
    
    @Test
    @WithMockUser(username = "test", authorities = {"USER"})
    public void test_getUsers() throws Exception {
        List<User> users = List.of(
                mock(User.class),
                mock(User.class),
                mock(User.class)
        );
        
        when(this.userManager.getUsers()).thenReturn(users);

        MvcResult result = mockMvc.perform(get("/admin/users")).andExpect(status().isOk()).andReturn();
        List<UserPayload> payloads = TestUtils.fromJson(result.getResponse().getContentAsString(), new TypeReference<List<UserPayload>>() {});
        Assert.isTrue(payloads.size() == users.size(), "Wrong number of users");
    }
    
    @Test
    @WithMockUser(username = "user", authorities = {"USER"})
    public void test_banNXUser() throws Exception {
        when(this.userManager.getUserById(0)).thenThrow(new UserNotFoundException("User not found with id"));
        mockMvc.perform(delete("/admin/users/0")).andExpect(status().isNotFound());
    }
    
    @Test
    public void test_banUser() throws Exception {
        User user = new User(1,"Test", "test");
        when(this.userManager.getUserById(1)).thenReturn(user);
        mockMvc.perform(delete("/admin/users/1")).andExpect(status().isNoContent());
    }
}
