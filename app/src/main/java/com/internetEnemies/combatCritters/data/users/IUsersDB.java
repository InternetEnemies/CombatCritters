package com.internetEnemies.combatCritters.data.users;

import com.internetEnemies.combatCritters.objects.User;

import java.util.List;

/**
 * IUsersDB.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     9/26/24
 * 
 * @PURPOSE:    provide interface to the Users DB table
 */
public interface IUsersDB {
    /**
     * create a new user in the database
     * @param username name of the user
     * @param password password of the user
     * @return the newly created user
     */
    User createUser(String username, String password);

    /**
     * get a user by its username
     * @param username username of the user to get
     * @return user with the given name. Returns null if no such user is found
     */
    User getUserByUsername(String username);

    /**
     * get a user by its id
     * @param id id of the user to get
     * @return usere with the given id or null if it doesn't exist
     */
    User getUserById(int id);

    /**
     * get all users in the db
     * @return list of users
     */
    List<User> getAllUsers();
}
