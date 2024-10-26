package com.combatcritters.critterspring.wrappers;

import com.internetEnemies.combatCritters.Logic.exceptions.UserNotFoundException;
import com.internetEnemies.combatCritters.Logic.users.UserManager;
import com.internetEnemies.combatCritters.data.users.IUsersDB;
import com.internetEnemies.combatCritters.objects.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


/**
 * UserWrapper.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/25/24
 * 
 * @PURPOSE:    wrap functions from usermanager with rest errors
 */
public class UserWrapper extends UserManager {
    public UserWrapper(IUsersDB users) {
        super(users);
    }
    
    @Override
    public User getUserByUsername(String username) {
        return this.runWithWrap(() -> super.getUserByUsername(username), "User not found with name: " + username);
    }

    @Override
    public User getUserById(int id) {
        return this.runWithWrap(() -> super.getUserById(id), "User not found with id: " + id);
    }

    /**
     * wraps a function that may throw an exception with an http error
     */
    private User runWithWrap(UserGetter runnable, String errorMessage){
        User user;
        try {
            user = runnable.get();
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage);
        }
        return user;
    }
    
}

interface UserGetter {
    User get();
}