package com.combatcritters.critterspring.unit;

import com.combatcritters.critterspring.TestUtils;
import com.combatcritters.critterspring.auth.payloads.UserPayload;
import com.combatcritters.critterspring.payloads.ItemStackPayload;
import com.combatcritters.critterspring.payloads.packs.PackCreatorPayload;
import com.combatcritters.critterspring.payloads.packs.PackPayload;
import com.combatcritters.critterspring.payloads.packs.PackResultPayload;
import com.combatcritters.critterspring.routes.PacksController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.internetEnemies.combatCritters.Logic.IUserDataFactory;
import com.internetEnemies.combatCritters.Logic.inventory.cards.ICardRegistry;
import com.internetEnemies.combatCritters.Logic.inventory.packs.IPackCatalog;
import com.internetEnemies.combatCritters.Logic.inventory.packs.IPackInventoryManager;
import com.internetEnemies.combatCritters.Logic.inventory.packs.PackBuilder;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.Logic.users.UserManager;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.objects.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import java.util.ArrayList;
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
public class PacksTest {
    MockMvc mockMvc;
    IPackCatalog packCatalog;
    IUserManager userManager;
    IUserDataFactory userDataFactory;
    IPackInventoryManager packInventoryManager;
    ICardRegistry cardRegistry;
    
    @BeforeEach
    public void setup() {
        this.userManager = mock(IUserManager.class);
        this.packCatalog = mock(IPackCatalog.class);
        this.userDataFactory = mock(IUserDataFactory.class);
        this.packInventoryManager = mock(IPackInventoryManager.class);
        this.cardRegistry = mock(ICardRegistry.class);
        
        when(userDataFactory.getPackInventoryManger(any())).thenReturn(packInventoryManager);
        
        mockMvc = MockMvcBuilders.standaloneSetup(new PacksController(userDataFactory,packCatalog, userManager, cardRegistry)).build();
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
        List<PackPayload> packPayloads = TestUtils.fromJson(result.getResponse().getContentAsString(), new TypeReference<List<PackPayload>>() {});
        Assert.isTrue(packPayloads.size() ==3, "all packs should be returned");
    }
    
    @Test
    public void test_getPackById() throws Exception {
        Pack pack = mock(Pack.class);
        when(pack.getId()).thenReturn(1);
        when(pack.getName()).thenReturn("");
        when(pack.getImage()).thenReturn("");
        
        when(this.packCatalog.getPack(1)).thenReturn(pack);
        MvcResult result = mockMvc.perform(get("/packs/1")).andExpect(status().isOk()).andReturn();
        PackPayload packPayload = TestUtils.fromJson(result.getResponse().getContentAsString(), new TypeReference<PackPayload>() {});
        Assert.isTrue(packPayload.packid() == pack.getId(), "pack should have requested id");
    }
    
    @Test
    public void test_getUserPacks() throws Exception {
        List<ItemStack<Pack>> packs = List.of(
                new ItemStack<Pack>(mock(Pack.class),1),
                new ItemStack<Pack>(mock(Pack.class),1),
                new ItemStack<Pack>(mock(Pack.class),1)
        );
        when(this.userManager.getUserById(1)).thenReturn(mock(User.class));
        when(this.packInventoryManager.getPackCounts()).thenReturn(packs);
        MvcResult result = mockMvc.perform(get("/users/1/packs")).andExpect(status().isOk()).andReturn();
        List<ItemStackPayload<PackPayload>> packPayloads = TestUtils.fromJson(result.getResponse().getContentAsString(), new TypeReference<List<ItemStackPayload<PackPayload>>>() {});

        Assert.isTrue(packPayloads.size() ==3, "all packs should be returned");
    }
    
    @Test
    public void test_getPackContents() throws Exception {
        List<Card> contents = List.of(
                mock(Card.class),
                mock(Card.class),
                mock(Card.class)
        );
        Pack pack = mock(Pack.class);
        when(pack.getSetList()).thenReturn(contents);
        when(this.packCatalog.getPack(1)).thenReturn(pack);
        MvcResult result = mockMvc.perform(get("/packs/1/contents")).andExpect(status().isOk()).andReturn();
        
        List<Card> cards = TestUtils.fromJson(result.getResponse().getContentAsString(), new TypeReference<ArrayList<Card>>() {});
        Assert.isTrue(cards.size() ==3, "all cards should be returned");
    }
    
    @Test
    public void test_openPack() throws Exception {
        List<Card> contents = List.of(
                mock(Card.class),
                mock(Card.class),
                mock(Card.class)
        );
        Pack pack = mock(Pack.class);
        when(this.userManager.getUserById(1)).thenReturn(mock(User.class));
        when(this.packInventoryManager.openPack(any())).thenReturn(contents);
        when(this.packCatalog.getPack(1)).thenReturn(pack);
        MvcResult result = mockMvc.perform(post("/users/1/packs/1")).andExpect(status().isOk()).andReturn();
        PackResultPayload packResult = TestUtils.fromJson(result.getResponse().getContentAsString(), new TypeReference<PackResultPayload>() {});
        Assert.isTrue(packResult.cards().size() == 3, "all cards should be returned");
    }
    
    @Test
    public void test_createPack() throws Exception {
        PackCreatorPayload packCreator = new PackCreatorPayload(
                List.of(),
                new PackPayload(null, "TestName", "Image"),
                List.of()
        );
        
        when(this.cardRegistry.getCards(any())).thenReturn(List.of());
        Pack newPack = new Pack(1,"TestName", "Image", List.of(), List.of());
        when(this.packCatalog.createPack(any())).thenReturn(newPack);
        
        MvcResult result = mockMvc.perform(post("/admin/packs")
                .content(TestUtils.toJson(packCreator))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        PackPayload packPayload = TestUtils.fromJson(result.getResponse().getContentAsString(), new TypeReference<PackPayload>() {});
        Assert.isTrue(packPayload.packid() == 1, "packid should be 1");
    }
}
