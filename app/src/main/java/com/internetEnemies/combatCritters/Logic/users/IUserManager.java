package com.internetEnemies.combatCritters.Logic.users;

import com.internetEnemies.combatCritters.Logic.exceptions.UserNotFoundException;
import com.internetEnemies.combatCritters.Logic.exceptions.UsernameConflictException;
import com.internetEnemies.combatCritters.objects.User;
import java.util.List;

/**
 * IUserManager.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     9/24/24
 * 
 * @PURPOSE:    provide methods for managing users
 */
public interface IUserManager {
    /**
     * get a user from its id
     * @param id id of the user to get
     * @return user with the given id if it exists
     * @throws UserNotFoundException if a user is not found with the id
     */
    User getUserById(int id);

    /**
     * get user from its name
     * @param username username to get
     * @return user with the given name
     * @throws UserNotFoundException if a user is not found with the username
     */
    User getUserByUsername(String username);

    /**
     * get whether a user with the given username exists
     * @param username username to check for
     * @return true iff the user exists
     */
    boolean existsByUsername(String username);

    /**
     * create a new user
     * @param username username of the user
     * @param password password for the user
     * @return newly created user
     * @throws UsernameConflictException if a user with the given username already exists
     */
    User createUser(String username, String password);


    /**
     * get all the users handled by this manager
     * @return list of all users
     */
    List<User> getUsers();

    /**
     * ban a user from the game
     * @param user user to ban
     */
    void banUser(User user);
}
