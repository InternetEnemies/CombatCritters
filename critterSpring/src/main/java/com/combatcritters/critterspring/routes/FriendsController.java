package com.combatcritters.critterspring.routes;

import com.combatcritters.critterspring.auth.payloads.UserPayload;
import com.combatcritters.critterspring.payloads.AddFriendPayload;
import com.internetEnemies.combatCritters.Logic.exceptions.UserNotFoundException;
import com.internetEnemies.combatCritters.Logic.users.IFriendsManager;
import com.internetEnemies.combatCritters.Logic.users.IFriendsManagerFactory;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * FriendsController.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/9/24
 * 
 * @PURPOSE:    provide friends routes
 */
@RestController
public class FriendsController {

    private final IFriendsManagerFactory friendsManagerFactory;
    private final IUserManager userManager;

    @Autowired
    public FriendsController(IFriendsManagerFactory friendsManagerFactory, IUserManager userManager) {
        this.friendsManagerFactory = friendsManagerFactory;
        this.userManager = userManager;
    }
    @GetMapping("/users/{userid}/friends")
    List<UserPayload> getFriends(@PathVariable int userid) {
        IFriendsManager manager = getFriendsManager(userid);
        return manager.getFriends().stream().map(UserPayload::from).toList();
    }
    
    @PostMapping("/users/{userid}/friends")
    void addFriend(@PathVariable int userid, @RequestBody AddFriendPayload user) {
        IFriendsManager manager = getFriendsManager(userid);
        User friend;
        try {
            friend = this.userManager.getUserByUsername(user.username());
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot add friend that doesnt exist");
        }
        manager.addFriend(friend);
    }
    
    @GetMapping("/users/{userid}/friends/pending")
    List<UserPayload> getPendingFriends(@PathVariable int userid) {
        IFriendsManager manager = getFriendsManager(userid);
        return manager.getPendingFriends().stream().map(UserPayload::from).toList();
    }
    
    private IFriendsManager getFriendsManager(int userid) {
        User user;
        try {
            user = userManager.getUserById(userid);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found");
        }
        return this.friendsManagerFactory.getFriendsManager(user);
    }
}
