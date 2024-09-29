package com.internetEnemies.combatCritters.LogicUnitTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.exceptions.UserNotFoundException;
import com.internetEnemies.combatCritters.Logic.exceptions.UsernameConflictException;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.Logic.users.UserManager;
import com.internetEnemies.combatCritters.data.users.IUsersDB;
import com.internetEnemies.combatCritters.objects.User;
import org.junit.Before;
import org.junit.Test;

public class UserManagerTest {
    User sampleUser;
    
    IUsersDB users;
    IUserManager userManager;
    @Before
    public void setup(){
        sampleUser = new User(0,"user","pass");
        users = mock(IUsersDB.class);
        userManager = new UserManager(users);
    }
    @Test(expected = UserNotFoundException.class)
    public void test_getNXUser(){
        when(users.getUserByUsername("user")).thenReturn(null);
        userManager.getUserByUsername("user");
    }
    
    @Test
    public void test_getUserByName(){
        when(users.getUserByUsername(sampleUser.getUsername()))
                .thenReturn(sampleUser);
        User user = userManager.getUserByUsername(sampleUser.getUsername());
        assertEquals(sampleUser, user);
    }
    
    @Test
    public void test_existsByName(){
        when(users.getUserByUsername(sampleUser.getUsername())).thenReturn(sampleUser);
        when(users.getUserByUsername("notAUser")).thenReturn(null);
        assertTrue(userManager.existsByUsername(sampleUser.getUsername()));
        assertFalse(userManager.existsByUsername("notAUser"));
    }
    
    @Test
    public void test_createUser(){
        when(users.getUserByUsername(sampleUser.getUsername()))
                .thenReturn(null);
        when(users.createUser(sampleUser.getUsername(), sampleUser.getPassword()))
                .thenReturn(sampleUser);
        User user = userManager.createUser(sampleUser.getUsername(), sampleUser.getPassword());
        assertEquals(sampleUser, user);
        verify(users).createUser(sampleUser.getUsername(), sampleUser.getPassword());
    }
    
    @Test(expected = UserNotFoundException.class)
    public void test_getNXUserById(){
        when(users.getUserById(-1))
                .thenReturn(null);
        userManager.getUserById(-1);
    }
    
    @Test
    public void test_getById(){
        when(users.getUserById(sampleUser.getId())).thenReturn(sampleUser);
        User user = userManager.getUserById(sampleUser.getId());
        assertEquals(sampleUser, user);
    }
    
    @Test(expected = UsernameConflictException.class)
    public void test_createExistingUser(){
        when(users.getUserByUsername(sampleUser.getUsername())).thenReturn(sampleUser);
        userManager.createUser(sampleUser.getUsername(), sampleUser.getPassword());
    }
}
