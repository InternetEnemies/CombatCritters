package com.internetEnemies.combatCritters.Logic.users;

import com.internetEnemies.combatCritters.Logic.exceptions.UserNotFoundException;
import com.internetEnemies.combatCritters.Logic.exceptions.UsernameConflictException;
import com.internetEnemies.combatCritters.data.users.IUsersDB;
import com.internetEnemies.combatCritters.objects.User;

public class UserManager implements IUserManager{ 
    private final IUsersDB users;
    public UserManager(IUsersDB users) {
        this.users = users;
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
        User user = users.getUserByUsername(username);
        if(user != null) {
            throw new UsernameConflictException("user '"+username+"' already exists");
        }
        user = users.createUser(username, password);
        
        return user;
    }
}
