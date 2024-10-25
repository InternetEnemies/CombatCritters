package com.combatcritters.critterspring.routes;

import com.combatcritters.critterspring.auth.payloads.UserPayload;
import com.internetEnemies.combatCritters.Logic.exceptions.UserNotFoundException;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UsersController {
    private final IUserManager userManager;
    
    @Autowired
    public UsersController(IUserManager userManager) {
        this.userManager = userManager;
    }
    
    @GetMapping("/admin/users")
    public List<UserPayload> getUsers() {
        List<User> users = this.userManager.getUsers();
        return users.stream().map(UserPayload::from).toList();
    }
    
    @DeleteMapping("/admin/users/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable int userid) {
        User user;
        try {
            user = this.userManager.getUserById(userid);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        this.userManager.banUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
