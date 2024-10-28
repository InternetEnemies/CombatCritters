package com.combatcritters.critterspring;

import com.combatcritters.critterspring.payloads.WalletPayload;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
public class WalletITest extends IntegrationTest{
    @Test
    public void test_getWallet() throws Exception {
        createUser();
        MvcResult result = mockMvc.perform(get("/users/1/wallet")).andExpect(status().isOk()).andReturn();
        WalletPayload payload = TestUtils.fromJson(result.getResponse().getContentAsString(), new TypeReference<WalletPayload>() {});
        Assert.notNull(payload, "payload should not be null");
    }
}
