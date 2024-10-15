package com.combatcritters.critterspring.unit;

import com.combatcritters.critterspring.DummyPrincipal;
import com.combatcritters.critterspring.TestUtils;
import com.combatcritters.critterspring.payloads.CardPayload;
import com.combatcritters.critterspring.routes.CardsController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.internetEnemies.combatCritters.Logic.inventory.cards.ICardCatalog;
import com.internetEnemies.combatCritters.Logic.inventory.cards.ICardRegistry;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import java.security.Principal;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
public class CardsTest {
    private Principal principal;
    private User user;
    private MockMvc mockMvc;
    
    private ICardCatalog catalog;
    private ICardRegistry registry;
    private IUserManager userManager;
    
    @BeforeEach
    public void setup() {
        this.user = new User(1, "dummy","user");
        this.principal = new DummyPrincipal(user.getUsername());
        
        this.catalog = mock(ICardCatalog.class);
        this.registry = mock(ICardRegistry.class);
        this.userManager = mock(IUserManager.class);
        
        when(this.userManager.getUserByUsername(this.principal.getName())).thenReturn(this.user);
        
        
        mockMvc = MockMvcBuilders.standaloneSetup(new CardsController(catalog,registry,userManager)).build();
    }
    
    @Test
    public void test_getCards() throws Exception {
        List<ItemStack<Card>> cards = Stream.of(
                TestUtils.getDummyCritter(1),
                TestUtils.getDummyCritter(2),
                TestUtils.getDummyCritter(3),
                TestUtils.getDummyCritter(4)
        ).map(card-> new ItemStack<Card>(card,0)).toList();
        
        when(this.catalog.get(any(),any())).thenReturn(cards);
        MvcResult result = mockMvc.perform(get("/cards").principal(principal))
                .andExpect(status().isOk())
                .andReturn();
        
        result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        List<CardPayload<?>> payloads = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<CardPayload<?>>>(){});
        List<Integer> cardids = cards.stream().map(item -> item.getItem().getId()).toList();
        for(CardPayload<?> payload : payloads){
            Assert.isTrue(cardids.contains(payload.cardid()),"returned cards should contain id");
        }
    }
}
