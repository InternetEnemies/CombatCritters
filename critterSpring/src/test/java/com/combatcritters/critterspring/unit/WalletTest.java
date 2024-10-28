package com.combatcritters.critterspring.unit;

import com.combatcritters.critterspring.TestUtils;
import com.combatcritters.critterspring.payloads.WalletPayload;
import com.combatcritters.critterspring.routes.WalletController;
import com.internetEnemies.combatCritters.Logic.IUserDataFactory;
import com.internetEnemies.combatCritters.Logic.inventory.IBank;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.C;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
@ActiveProfiles("unit")
public class WalletTest {
    
    MockMvc mockMvc;
    @MockBean
    IUserDataFactory userDataFactory;
    @MockBean
    IUserManager userManager;        
    
    IBank bank;
    
    @BeforeEach
    public void setup() {
        bank = mock(IBank.class);
        when(userDataFactory.getBank(any())).thenReturn(bank);
        
        mockMvc = MockMvcBuilders.standaloneSetup(new WalletController(userDataFactory, userManager)).build();
    }
    
    @Test
    public void test_getWallet() throws Exception {
        when(this.userManager.getUserById(1)).thenReturn(mock(User.class));
        when(this.bank.getCurrentBalance()).thenReturn(new Currency(123));
        MvcResult result = mockMvc.perform(get("/users/1/wallet")).andExpect(status().isOk()).andReturn();
        WalletPayload payload = TestUtils.fromJson(result.getResponse().getContentAsString(), new TypeReference<WalletPayload>() {});
        Assertions.assertEquals(123, payload.coins());
    }
}
