package com.internetEnemies.combatCritters;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.IUserDataFactory;
import com.internetEnemies.combatCritters.Logic.users.IUserInitializer;
import com.internetEnemies.combatCritters.Logic.users.UserInitializer;
import com.internetEnemies.combatCritters.data.*;
import com.internetEnemies.combatCritters.data.hsqldb.*;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.objects.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class UserInitializerTest {
    IUserInitializer userInitializer;
    IUserDataFactory userDataFactory;
    IRegistry<Pack> packRegistry;
    IRegistry<Card> cardRegistry;
    String path;
    
    @Before
    public void setup() throws IOException {
        path = TestUtils.getDBPath();
        this.packRegistry = new RegistryPackHSQLDB(path);
        this.cardRegistry = new RegistryCardHSQLDB(path);
        this.userDataFactory = mock(IUserDataFactory.class);
        
        this.userInitializer = new UserInitializer(this.packRegistry, this.cardRegistry, this.userDataFactory);
    }
    
    @Test
    public void test_initialize(){
        User dummy = TestUtils.getDummyUser(path);
        TestUtils.initFull(path);
        when(userDataFactory.getPackInventory(any())).thenAnswer(i -> new PackInventoryHSQLDB(path, i.getArgument(0)));
        when(userDataFactory.getCardInventory(any())).thenAnswer(i -> new CardInventoryHSQLDB(path, i.getArgument(0)));
        when(userDataFactory.getCurrencyInventory(any())).thenAnswer(i -> new CurrencyInventoryHSQLDB(path, i.getArgument(0)));
        
        this.userInitializer.initialize(dummy);
        List<?> cards = userDataFactory.getCardInventory(dummy).getCards();
        assertFalse(cards.isEmpty());
    }
}
