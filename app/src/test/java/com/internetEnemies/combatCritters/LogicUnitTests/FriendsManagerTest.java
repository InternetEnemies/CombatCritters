package com.internetEnemies.combatCritters.LogicUnitTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.users.FriendsManager;
import com.internetEnemies.combatCritters.Logic.users.IFriendsManager;
import com.internetEnemies.combatCritters.data.IFriendsDB;
import com.internetEnemies.combatCritters.objects.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class FriendsManagerTest {
    IFriendsDB friendsDB;
    IFriendsManager friendsManager;
    @Before
    public void setup() {
        this.friendsDB = mock(IFriendsDB.class);
        this.friendsManager = new FriendsManager(friendsDB);
    }
    
    @Test
    public void test_getNoFriends(){
        when(this.friendsDB.getFriends()).thenReturn(List.of());
        assertEquals(0,this.friendsManager.getFriends().size());
    }
    
    @Test
    public void test_addAndGetFriends(){
        User dummy1 = new User(1,"","");
        User dummy2 = new User(2,"","");
        when(this.friendsDB.getFriends()).thenReturn(List.of(dummy1, dummy2));
        List<User> friends = this.friendsManager.getFriends();
        assertTrue(friends.contains(dummy1));
        assertTrue(friends.contains(dummy2));
    }
    @Test
    public void test_getPending(){
        User dummy1 = new User(1,"","");
        User dummy2 = new User(2,"","");
        when(this.friendsDB.getPendingFriends()).thenReturn(List.of(dummy1, dummy2));
        List<User> friends = this.friendsManager.getPendingFriends();
        assertTrue(friends.contains(dummy1));
        assertTrue(friends.contains(dummy2));
    }
}
