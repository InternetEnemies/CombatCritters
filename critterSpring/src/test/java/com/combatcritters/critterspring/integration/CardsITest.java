package com.combatcritters.critterspring.integration;

import com.combatcritters.critterspring.TestUtils;
import com.combatcritters.critterspring.auth.payloads.RegisterPayload;
import com.combatcritters.critterspring.payloads.CardPayload;
import com.combatcritters.critterspring.payloads.ItemStackPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
public class CardsITest {
    MockMvc mockMvc;
    
    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    @WithMockUser(username = "test", authorities = {"USER"})
    public void test_getCards() throws Exception {
        MvcResult result = mockMvc.perform(get("/cards")).andExpect(status().isOk()).andReturn();
        ObjectMapper mapper = new ObjectMapper();
        List<CardPayload<?>> payloads = mapper.readValue(
                result.getResponse().getContentAsString(), 
                new TypeReference<List<CardPayload<?>>>() {});
        assert !payloads.isEmpty();
    }
}
