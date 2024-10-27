package com.combatcritters.critterspring;

import com.combatcritters.critterspring.auth.payloads.RegisterPayload;
import com.combatcritters.critterspring.payloads.CardPayload;
import com.combatcritters.critterspring.payloads.ItemStackPayload;
import com.combatcritters.critterspring.payloads.packs.CardSlotPayload;
import com.combatcritters.critterspring.payloads.packs.PackCreatorPayload;
import com.combatcritters.critterspring.payloads.packs.PackPayload;
import com.combatcritters.critterspring.payloads.packs.RarityWeightPayload;
import com.fasterxml.jackson.core.type.TypeReference;
import com.internetEnemies.combatCritters.objects.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import java.util.List;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
public class PacksITest extends IntegrationTest {
    @Test
    public void test_getPacks() throws Exception {
        MvcResult result = mockMvc.perform(get("/packs")).andExpect(status().isOk()).andReturn();
        List<PackPayload> packs = TestUtils.fromJson(result.getResponse().getContentAsString(),new TypeReference<List<PackPayload>>(){});
        Assert.isTrue(!packs.isEmpty(), "packs should not be empty");
    }
    
    @Test
    public void test_getPack() throws Exception {
        MvcResult result = mockMvc.perform(get("/packs/1")).andExpect(status().isOk()).andReturn();
        PackPayload packPayload = TestUtils.fromJson(result.getResponse().getContentAsString(), new TypeReference<PackPayload>() {});
        Assert.isTrue(packPayload.packid() == 1, "pack id should match requested");
    }
    
    @Test
    public void test_getUserPacks() throws Exception {
        mockMvc.perform(post("/users/auth/register")
                .content(TestUtils.toJson(new RegisterPayload("user","user")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        
        MvcResult result = mockMvc.perform(get("/users/1/packs")).andExpect(status().isOk()).andReturn();
        List<ItemStackPayload<PackPayload>> packs = TestUtils.fromJson(result.getResponse().getContentAsString(), new TypeReference<List<ItemStackPayload<PackPayload>>>() {});
        Assert.isTrue(packs.isEmpty(), "user packs should be empty");
    }
    
    @Test
    public void test_getPackContents() throws Exception {
        MvcResult result = mockMvc.perform(get("/packs/1/contents")).andExpect(status().isOk()).andReturn();
        List<CardPayload<?>> contents = TestUtils.fromJson(result.getResponse().getContentAsString(), new TypeReference<List<CardPayload<?>>>() {});
        Assert.isTrue(!contents.isEmpty(), "contents should not be empty");
    }
    
    @Test
    public void test_createPack() throws Exception {
        PackCreatorPayload packCreator = new PackCreatorPayload(
                List.of(1,2,3),
                new PackPayload(null, "TestPack", "Image"),
                List.of(
                        new CardSlotPayload(List.of(
                                new RarityWeightPayload(Card.Rarity.COMMON.ordinal(), 0.5)
                        ))
                )
        );
        MvcResult result = mockMvc.perform(post("/admin/packs")
                .content(TestUtils.toJson(packCreator))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        PackPayload packPayload = TestUtils.fromJson(result.getResponse().getContentAsString(), new TypeReference<PackPayload>() {});
        Assert.isTrue(packPayload.packid() !=0, "pack id should not be zero");
    }
}
