package com.internetEnemies.combatCritters.Logic.users;

import com.internetEnemies.combatCritters.data.IFriendsDB;
import com.internetEnemies.combatCritters.objects.User;

import java.util.List;

/**
 * FriendsManager.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/9/24
 * 
 * @PURPOSE:    manage friends in a friends db
 */
public class FriendsManager implements IFriendsManager{

    private final IFriendsDB friendsManager;

    public FriendsManager(IFriendsDB friendsManager) {
        this.friendsManager = friendsManager;
    }
    
    @Override
    public List<User> getFriends() {
        return this.friendsManager.getFriends();
    }

    @Override
    public void addFriend(User user) {
        this.friendsManager.addFriend(user);
    }

    @Override
    public List<User> getPendingFriends() {
        return this.friendsManager.getPendingFriends();
    }
}
