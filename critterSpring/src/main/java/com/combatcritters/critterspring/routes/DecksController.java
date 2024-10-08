package com.combatcritters.critterspring.routes;

import com.combatcritters.critterspring.payloads.decks.*;
import com.internetEnemies.combatCritters.Logic.IUserDataFactory;
import com.internetEnemies.combatCritters.Logic.exceptions.UserNotFoundException;
import com.internetEnemies.combatCritters.Logic.inventory.cards.ICardRegistry;
import com.internetEnemies.combatCritters.Logic.inventory.decks.*;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.exception.NXDeckException;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.DeckValidity;
import com.internetEnemies.combatCritters.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
public class DecksController {
    
    private final ICardRegistry cardRegistry;
    private final IUserDataFactory userDataFactory;
    private final IDeckValidatorFactory validatorFactory;
    private final IUserManager userManager;
    
    @Autowired
    public DecksController(IUserDataFactory userDataFactory, IDeckValidatorFactory validatorFactory, IUserManager userManager, ICardRegistry cardRegistry) {
        this.userDataFactory = userDataFactory;
        this.validatorFactory = validatorFactory;
        this.userManager = userManager;
        this.cardRegistry = cardRegistry;
    }
    
    //* /users/[id]/decks
    @GetMapping("/users/{userid}/decks")
    public List<DeckDetailsPayload> getDecks(@PathVariable int userid){
        IDeckManager manager = getDeckManager(userid);

        return manager.getDecks().stream().map(DeckDetailsPayload::from).toList();
    }
    
    @PostMapping("/users/{userid}/decks")
    public DeckDetailsPayload createDeck(Principal principal, @PathVariable int userid, @RequestBody DeckDetailsPayload deckDetailsPayload){
        User reqUser;
        try {
            reqUser = this.userManager.getUserByUsername(principal.getName());
        } catch (UserNotFoundException e) {
            //if you get here I'm not sure how you did, but I think it's your fault
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (reqUser.getId() != userid){//you cant create a deck for another user
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        //create the new deck
        DeckManager manager = getDeckManager(reqUser);
        DeckDetails newDeck;
        try {
            newDeck = manager.createDeck(deckDetailsPayload.name());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        
        return DeckDetailsPayload.from(newDeck);
    }
    
    //* /users/[id]/decks/[id]
    @DeleteMapping("/users/{userid}/decks/{deckid}")
    public DeckDetailsPayload deleteDeck(@PathVariable int userid, @PathVariable int deckid){
        //at some point logic / data should probably support this 
        IDeckManager manager = getDeckManager(userid);
        DeckDetails details = getDeckDetails(manager, deckid);
        manager.deleteDeck(details);
        return DeckDetailsPayload.from(details);
    }
    
    //* /users/[id]/decks/[id]/cards
    @GetMapping("/users/{userid}/decks/{deckid}/cards")
    public DeckPayload getDeckCards(@PathVariable int userid, @PathVariable int deckid){
        IDeckManager manager = getDeckManager(userid);
        DeckDetails details = getDeckDetails(manager, deckid);
        
        IDeckBuilder builder = manager.getBuilder(details);
        List<Card> cards = builder.getCards();
        return DeckPayload.from(cards);
    }
    
    @PutMapping("/users/{userid}/decks/{deckid}/cards")
    public DeckUpdatedPayload updateDeckCards(@PathVariable int userid, @PathVariable int deckid, @RequestBody DeckPayload deckPayload){
        //get builder
        IDeckManager manager = getDeckManager(userid);
        DeckDetails details = getDeckDetails(manager, deckid);
        IDeckBuilder builder = manager.getBuilder(details);
        
        //set cards and get new deck objects
        builder.setCards(deckPayload.toCardList(cardRegistry));
        List<Card> cards = builder.getCards();
        DeckValidity validity = builder.validate();
        
        return new DeckUpdatedPayload(DeckPayload.from(cards),DeckValidityPayload.from(validity));
    }
    
    //* /users/[id]/decks/[id]/validity
    @GetMapping("/users/{userid}/deck/{deckid}/validity")
    public DeckValidityPayload getDeckValidity(@PathVariable int userid, @PathVariable int deckid){
        return null;
    }
    
    //* /decks/validity
    @GetMapping("/decks/validity")
    public DeckValidityRulesPayload getDeckValidityRules(){
        return null;
    }

    /**
     * util function for getting a deck manager for a user from their id
     * @param userid id of the user to get a manager for
     * @return deck manager for the user
     */
    private DeckManager getDeckManager(int userid){
        
        User user;
        try {
            user = this.userManager.getUserById(userid);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        
        return getDeckManager(user);
    }
    private DeckManager getDeckManager(User user){
        ICardInventory cardInventory = this.userDataFactory.getCardInventory(user);
        return new DeckManager(this.userDataFactory.getDeckInventory(user),cardInventory, validatorFactory.createValidator(cardInventory));
    }
    
    private DeckDetails getDeckDetails(IDeckManager manager, int deckid) {
        try{
            return manager.getDeckDetails(deckid);
        } catch (NXDeckException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
