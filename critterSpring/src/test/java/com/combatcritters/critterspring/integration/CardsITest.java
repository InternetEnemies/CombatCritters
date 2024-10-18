package com.combatcritters.critterspring.integration;

import com.combatcritters.critterspring.payloads.CardPayload;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@ActiveProfiles("integration")
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
