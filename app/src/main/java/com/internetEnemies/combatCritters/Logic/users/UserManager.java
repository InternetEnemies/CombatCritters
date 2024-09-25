package com.internetEnemies.combatCritters.Logic.users;

import com.internetEnemies.combatCritters.Logic.exceptions.UserNotFoundException;
import com.internetEnemies.combatCritters.Logic.exceptions.UsernameConflictException;
import com.internetEnemies.combatCritters.objects.User;

import java.util.Dictionary;
import java.util.Hashtable;

public class UserManager implements IUserManager{ //todo implement this properly
    private final Dictionary<String, User> users;
    public UserManager() {
        this.users = new Hashtable<String, User>();
    }
    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = users.get(username);
        if(user == null) {
            throw new UserNotFoundException("user '"+username+"' not found");
        }
        return user;
    }

    @Override
    public boolean existsByUsername(String username) {
        return users.get(username) != null;
    }

    @Override
    public User createUser(String username, String password) {
        User user = users.get(username);
        if(user != null) {
            throw new UsernameConflictException("user '"+username+"' already exists");
        }
        user = new User(0,username,password);
        users.put(username,user);
        
        return user;
    }
}
