package com.combatcritters.critterspring.unit;

import com.combatcritters.critterspring.TestUtils;
import com.combatcritters.critterspring.payloads.packs.PackPayload;
import com.combatcritters.critterspring.routes.PacksController;
import com.internetEnemies.combatCritters.Logic.IUserDataFactory;
import com.internetEnemies.combatCritters.Logic.inventory.packs.IPackCatalog;
import com.internetEnemies.combatCritters.Logic.inventory.packs.PackBuilder;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.Logic.users.UserManager;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.objects.Pack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
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
public class PacksTest {
    MockMvc mockMvc;
    IPackCatalog packCatalog;
    IUserManager userManager;
    IUserDataFactory userDataFactory;
    
    @BeforeEach
    public void setup() {
        this.userManager = mock(IUserManager.class);
        this.packCatalog = mock(IPackCatalog.class);
        this.userDataFactory = mock(IUserDataFactory.class);
        
        mockMvc = MockMvcBuilders.standaloneSetup(new PacksController(userDataFactory,packCatalog, userManager)).build();
    }
    
    @Test
    public void test_getPacks() throws Exception {
        List<Pack> packs = List.of(
                mock(Pack.class),
                mock(Pack.class),
                mock(Pack.class)
        );
        
        when(this.packCatalog.getListOfPacks()).thenReturn(packs);
        MvcResult result = mockMvc.perform(get("/packs")).andExpect(status().isOk()).andReturn();
        List<PackPayload> packPayloads = TestUtils.fromJson(result.getResponse().getContentAsString());
        Assert.isTrue(packPayloads.size() ==3, "all packs should be returned");
    }
}
