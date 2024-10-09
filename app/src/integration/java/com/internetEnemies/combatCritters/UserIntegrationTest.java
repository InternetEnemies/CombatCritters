package com.internetEnemies.combatCritters;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.exceptions.UserNotFoundException;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.Logic.users.UserManager;
import com.internetEnemies.combatCritters.data.users.IUsersDB;
import com.internetEnemies.combatCritters.data.users.UsersDB;
import com.internetEnemies.combatCritters.objects.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class UserIntegrationTest {
    static final String USERNAME = "username";
    static final String PASSWORD = "password";
    
    IUsersDB usersDB;
    IUserManager userManager;
    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        usersDB = new UsersDB(path);
        userManager = new UserManager(usersDB);
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
    @Test
    public void test_createManyUsers(){
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            this.userManager.createUser("user"+i, "pass");
            
        }
    }
}
