package com.internetEnemies.combatCritters;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.IUserDataFactory;
import com.internetEnemies.combatCritters.Logic.exceptions.UserNotFoundException;
import com.internetEnemies.combatCritters.Logic.inventory.packs.IPackInventoryManager;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.Logic.users.UserInitializer;
import com.internetEnemies.combatCritters.Logic.users.UserManager;
import com.internetEnemies.combatCritters.data.*;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.PackInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryPackHSQLDB;
import com.internetEnemies.combatCritters.data.init.SQLInitializer;
import com.internetEnemies.combatCritters.data.users.IUsersDB;
import com.internetEnemies.combatCritters.data.users.UsersDB;
import com.internetEnemies.combatCritters.objects.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class UserIntegrationTest {
    static final String USERNAME = "username";
    static final String PASSWORD = "password";
    
    IUsersDB usersDB;
    IUserManager userManager;
    String path;
    @Before
    public void setup() throws IOException {
        path = TestUtils.getDBPath();
        usersDB = new UsersDB(path);
        userManager = new UserManager(usersDB, _->{}); // we mock the initializer until we want to test the initialization functionality
    }
    @Test
    public void test_createUser(){
        User user = userManager.createUser(USERNAME, PASSWORD);
        assertEquals(USERNAME, user.getUsername());
        assertEquals(PASSWORD, user.getPassword());
    }
    
    @Test
    public void test_getUserByUsername(){
        User newUser = userManager.createUser(USERNAME, PASSWORD);
        User user = userManager.getUserByUsername(newUser.getUsername());
        assertEquals(newUser, user);
    }
    
    @Test(expected = UserNotFoundException.class)
    public void test_getNXUserByName(){
        userManager.getUserByUsername(USERNAME);
    }
    
    @Test
    public void test_getUserById(){
        User newUser = userManager.createUser(USERNAME, PASSWORD);
        User user = userManager.getUserById(newUser.getId());
        assertEquals(newUser, user);
    }
    
    void createUsers(int n) {
        for (int i = 0; i < n; i++) {
            this.userManager.createUser("user"+i, "pass");
        }
    }
    @Test
    public void test_createManyUsers(){
        createUsers(10);
    }
    
    @Test
    public void test_getAllUsers(){
        createUsers(10);
        List<User> users = userManager.getUsers();
        assertEquals(10, users.size());
    }
    
    @Test
    public void test_exists(){
        assertFalse(userManager.existsByUsername(USERNAME));
        userManager.createUser(USERNAME, PASSWORD);
        assertTrue(userManager.existsByUsername(USERNAME));
    }
    
    @Test
    public void test_banUser(){
        User user = userManager.createUser(USERNAME, PASSWORD);
        assertFalse(user.isBanned());
        userManager.banUser(user);
        user = userManager.getUserByUsername(USERNAME);
        assertTrue(user.isBanned());
        
        
    }
    
    @Test
    public void test_initOnCreate(){
        var factory =
                new IUserDataFactory() {
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
        userManager = new UserManager(usersDB, new UserInitializer(new RegistryPackHSQLDB(path),
                new RegistryCardHSQLDB(path),
                factory));
        
        new SQLInitializer(path).initRows();
        
        var user = userManager.createUser(USERNAME, PASSWORD);
        var cards = factory.getCardInventory(user).getCards();
        assertFalse(cards.isEmpty());
    }
}
