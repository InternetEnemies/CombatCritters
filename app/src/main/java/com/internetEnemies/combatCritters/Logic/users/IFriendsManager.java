package com.internetEnemies.combatCritters.Logic.users;

import com.internetEnemies.combatCritters.objects.User;

import java.util.List;

/**
 * IFriendsManager.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/9/24
 * 
 * @PURPOSE:    interface for user friends
 */
public interface IFriendsManager {
    /**
     * get friends managed by the manager
     * @return list of friends
     */
    List<User> getFriends();

    /**
     * add new friend to the friends in this manager
     * @param user friend to add
     */
    void addFriend(User user);

    /**
     * get the pending friends for this friend manager
     * @return list of pending friend requests
     */
    List<User> getPendingFriends();
}
