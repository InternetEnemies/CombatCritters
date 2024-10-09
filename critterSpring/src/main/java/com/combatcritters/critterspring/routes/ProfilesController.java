package com.combatcritters.critterspring.routes;

import com.combatcritters.critterspring.payloads.ProfilePayload;
import com.combatcritters.critterspring.payloads.decks.DeckDetailsPayload;
import com.internetEnemies.combatCritters.Logic.IUserDataFactory;
import com.internetEnemies.combatCritters.Logic.exceptions.UserNotFoundException;
import com.internetEnemies.combatCritters.Logic.inventory.decks.IDeckManager;
import com.internetEnemies.combatCritters.Logic.inventory.decks.IDeckManagerFactory;
import com.internetEnemies.combatCritters.Logic.users.IProfileManager;
import com.internetEnemies.combatCritters.Logic.users.IProfileManagerFactory;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

/**
 * ProfilesController.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/9/24
 * 
 * @PURPOSE:    provide profiles routes
 */
@RestController
public class ProfilesController {
    
    IUserDataFactory userDataFactory;
    IUserManager userManager;
    IProfileManagerFactory profileManagerFactory;
    IDeckManagerFactory deckManagerFactory;
    
    @Autowired
    public ProfilesController(IUserDataFactory userDataFactory, IUserManager userManager, IProfileManagerFactory profileManagerFactory, IDeckManagerFactory deckManagerFactory) {

        this.userDataFactory = userDataFactory;
        this.userManager = userManager;
        this.profileManagerFactory = profileManagerFactory;
        this.deckManagerFactory = deckManagerFactory;
    }
    
    
    @GetMapping("/users/{userid}/profile")
    public ProfilePayload getProfile(@PathVariable int userid){
        User user;
        try {
            user = userManager.getUserById(userid);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        IProfileManager profileManager = profileManagerFactory.getProfileManager(user);
        UserProfile profile = profileManager.getProfile();
        ProfilePayload profilePayload;
        if (profile.deckId() != 0) {
            IDeckManager deckManager = this.deckManagerFactory.create(user);
            DeckDetails details = deckManager.getDeckDetails(profile.deckId());
            profilePayload = new ProfilePayload(DeckDetailsPayload.from(details));
        } else {
            profilePayload = new ProfilePayload(null);
        }
        
        return profilePayload;
    }
    
    @PutMapping("/users/{userid}/profile")
    public void setProfile(Principal principal, @PathVariable int userid, @RequestBody ProfilePayload payload){
        User user;
        try {
            user = userManager.getUserById(userid);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        User requester = userManager.getUserByUsername(principal.getName());
        if (!requester.equals(user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot modify another users profile");
        }
        
        IProfileManager profileManager = profileManagerFactory.getProfileManager(user);
        profileManager.updateProfile(payload.toUserProfile());
    }
}
