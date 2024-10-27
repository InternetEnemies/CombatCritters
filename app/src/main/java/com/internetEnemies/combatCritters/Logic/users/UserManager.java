package com.internetEnemies.combatCritters.Logic.users;

import com.internetEnemies.combatCritters.Logic.exceptions.UserNotFoundException;
import com.internetEnemies.combatCritters.Logic.exceptions.UsernameConflictException;
import com.internetEnemies.combatCritters.data.users.IUsersDB;
import com.internetEnemies.combatCritters.objects.User;

import java.util.List;

/**
 * UserManager.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     9/28/24
 * 
 * @PURPOSE:    Provide implementation for IUserManager functionality IUsersDB
 */
public class UserManager implements IUserManager{ 
    private final IUsersDB users;
    private final IUserInitializer userInitializer;

    public UserManager(IUsersDB users, IUserInitializer userInitializer) {
        this.users = users;
        this.userInitializer = userInitializer;
    }
    @Override
    public User getUserById(int id) {
        User user = users.getUserById(id);
        if(user == null) {
            throw new UserNotFoundException("user with the given id was not found");
        }
        
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = users.getUserByUsername(username);
        if(user == null) {
            throw new UserNotFoundException("user '"+username+"' not found");
        }
        return user;
    }

    @Override
    public boolean existsByUsername(String username) {
        return users.getUserByUsername(username) != null;
    }

    @Override
    public User createUser(String username, String password) {
        if(existsByUsername(username)) {
            throw new UsernameConflictException("user '"+username+"' already exists");
        }
        User user = users.createUser(username, password);
        userInitializer.initialize(user);
        
        return user;
    }

    @Override
    public List<User> getUsers() {
        return users.getAllUsers();
    }

    @Override
    public void banUser(User user) {
        users.banUser(user);
    }
}
