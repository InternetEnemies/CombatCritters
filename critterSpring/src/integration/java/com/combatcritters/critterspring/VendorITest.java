package com.combatcritters.critterspring;

import com.combatcritters.critterspring.payloads.market.VendorPayload;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
public class VendorITest extends IntegrationTest{
    @Test
    public void test_getVendors() throws Exception {
        login();
        MvcResult result = mockMvc.perform(get("/vendors").principal(getPrincipal())).andExpect(status().isOk()).andReturn();
        List<VendorPayload> vendors = TestUtils.fromJson(result.getResponse().getContentAsString(), new TypeReference<List<VendorPayload>>() {});
        Assertions.assertFalse(vendors.isEmpty());
    }
    
    @Test
    public void test_getOffers() throws Exception {
        login();
        mockMvc.perform(get("/vendors/1/offers").principal(getPrincipal())).andExpect(status().isOk());
    }
}
