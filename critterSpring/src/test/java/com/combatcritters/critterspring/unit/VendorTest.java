package com.combatcritters.critterspring.unit;

import com.combatcritters.critterspring.DummyPrincipal;
import com.combatcritters.critterspring.routes.VendorController;
import com.internetEnemies.combatCritters.Logic.market.IVendorManager;
import com.internetEnemies.combatCritters.Logic.market.IVendorManagerFactory;
import com.internetEnemies.combatCritters.Logic.market.Vendor;
import com.internetEnemies.combatCritters.Logic.market.VendorManager;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.objects.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
@ActiveProfiles("unit")
public class VendorTest {
    MockMvc mockMvc;
    @MockBean
    IVendorManagerFactory vendorManagerFactory;
    @MockBean
    IUserManager userManager;
    IVendorManager vendorManager;
    
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new VendorController(userManager, vendorManagerFactory)).build();
        vendorManager = mock(IVendorManager.class);
        when(vendorManagerFactory.create(any())).thenReturn(vendorManager);
    }
    
    @Test
    public void test_getVendors() throws Exception {
        when(vendorManager.getVendors()).thenReturn(List.of());
        when(userManager.getUserByUsername(any())).thenReturn(mock(User.class));
        mockMvc.perform(get("/vendors").principal(new DummyPrincipal("name"))).andExpect(status().isOk());
    }
}
