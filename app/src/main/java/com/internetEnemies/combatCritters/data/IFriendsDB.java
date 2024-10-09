package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.User;

import java.util.List;

/**
 * IFriendsDB.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/9/24
 * 
 * @PURPOSE:    provide friends database
 */
public interface IFriendsDB {

    /**
     * get friends list
     */
    List<User> getFriends();

    /**
     * add new friend or send friend request
     * @param user user to add as friend
     */
    void addFriend(User user);

    /**
     * get the pending friends
     * @return list of pending friends
     */
    List<User> getPendingFriends();
}
