package com.combatcritters.critterspring.routes;

import com.combatcritters.critterspring.auth.payloads.UserPayload;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    
}
