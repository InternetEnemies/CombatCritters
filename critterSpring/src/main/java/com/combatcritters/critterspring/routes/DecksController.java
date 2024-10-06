package com.combatcritters.critterspring.routes;

import com.combatcritters.critterspring.payloads.decks.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DecksController {
    //* /users/[id]/decks
    @GetMapping("/users/{userid}/decks")
    public List<DeckDetailsPayload> getDecks(@PathVariable int userid){
        return null;
    }
    
    @PostMapping("/users/{userid}/decks")
    public DeckDetailsPayload createDeck(@PathVariable int userid, @RequestBody DeckDetailsPayload deckDetailsPayload){
        return null;
    }
    
    //* /users/[id]/decks/[id]
    @DeleteMapping("/users/{userid}/decks/{deckid}")
    public DeckDetailsPayload deleteDeck(@PathVariable int userid, @PathVariable int deckid){
        return null;
    }
    
    //* /users/[id]/decks/[id]/cards
    @GetMapping("/users/{userid}/decks/{deckid}/cards")
    public DeckPayload getDeckCards(@PathVariable int userid, @PathVariable int deckid){
        return null;
    }
    
    @PutMapping("/users/{userid}/decks/{deckid}/cards")
    public DeckUpdatedPayload updateDeckCards(@PathVariable int userid, @PathVariable int deckid, @RequestBody DeckPayload deckPayload){
        return null;
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
}
