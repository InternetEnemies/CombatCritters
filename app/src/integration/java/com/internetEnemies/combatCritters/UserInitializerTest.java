package com.internetEnemies.combatCritters;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.IUserDataFactory;
import com.internetEnemies.combatCritters.Logic.UserDataFactory;
import com.internetEnemies.combatCritters.Logic.inventory.packs.IPackInventoryManager;
import com.internetEnemies.combatCritters.Logic.users.IUserInitializer;
import com.internetEnemies.combatCritters.Logic.users.UserInitializer;
import com.internetEnemies.combatCritters.data.*;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.PackInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryPackHSQLDB;
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
        
        this.userDataFactory = new IUserDataFactory(){
            @Override
            public IDeckInventory getDeckInventory(User user) {
                return null;
            }

            @Override
            public ICardInventory getCardInventory(User user) {
                return new CardInventoryHSQLDB(path, user);
            }

            @Override
            public IPackInventory getPackInventory(User user) {
                return new PackInventoryHSQLDB(path, user);
            }

            @Override
            public IProfilesDB getProfilesDB(User user) {
                return null;
            }

            @Override
            public IFriendsDB getFriendsDB(User user) {
                return null;
            }

            @Override
            public IPackInventoryManager getPackInventoryManger(User user) {
                return null;
            }
        };
        
        this.userInitializer = new UserInitializer(this.packRegistry, this.cardRegistry, this.userDataFactory);
    }
    
    @Test
    public void test_initialize(){
        User dummy = TestUtils.getDummyUser(path);
        TestUtils.initFull(path);
        this.userInitializer.initialize(dummy);
        List<?> cards = userDataFactory.getCardInventory(dummy).getCards();
        assertFalse(cards.isEmpty());
    }
}
