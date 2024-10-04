package com.combatcritters.critterspring.routes;

import com.combatcritters.critterspring.payloads.CardPayload;
import com.combatcritters.critterspring.payloads.CardQuery;
import com.combatcritters.critterspring.payloads.ItemStackPayload;
import com.internetEnemies.combatCritters.Logic.exceptions.UserNotFoundException;
import com.internetEnemies.combatCritters.Logic.inventory.cards.CardCatalog;
import com.internetEnemies.combatCritters.Logic.inventory.cards.ICardCatalog;
import com.internetEnemies.combatCritters.Logic.inventory.cards.ICardRegistry;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * CardsController.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/4/24
 * 
 * @PURPOSE:    provide routes related to cards
 */
@RestController
public class CardsController {
    
    private final ICardCatalog catalog;
    private final ICardRegistry cardRegistry;
    private final IUserManager userManager;
    
    @Autowired
    public CardsController(CardCatalog catalog, ICardRegistry cardRegistry, IUserManager userManager) {
        this.catalog = catalog;
        this.cardRegistry = cardRegistry;
        this.userManager = userManager;
    }
    
    @GetMapping("/cards")
    List<CardPayload<?>> getCards(CardQuery query){
        List<Card> cards = catalog.get(
                query.toFilter(),
                List.of(query.toOrder())
        ).stream().map(ItemStack::getItem).toList();// convert the ItemStacks from card catalog to cards
        
        // convert cards to card payloads
        List<CardPayload<?>> cardPayloads = new ArrayList<>();//for some reason using a stream here doesn't work
        for (Card card : cards) {
            cardPayloads.add(CardPayload.from(card));
        }
        return cardPayloads;
    }
    
    @GetMapping("/cards/{id}")
    CardPayload<?> getCard(@PathVariable int id){
        Card card = this.cardRegistry.getCard(id);
        if (card == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
        return CardPayload.from(card);
    }
    
    @GetMapping("/users/{userid}/cards")
    List<ItemStackPayload<CardPayload<?>>> getUserCards(@PathVariable int userid, CardQuery query){
        User user;
        try {
            user = this.userManager.getUserById(userid);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        
        List<ItemStack<Card>> cards = catalog.get(query.toFilter(user), List.of(query.toOrder()));
        
        // translate stacks to stack payloads
        List<ItemStackPayload<CardPayload<?>>> cardPayloads = new ArrayList<>();
        for (ItemStack<Card> stack : cards) {
            cardPayloads.add(new ItemStackPayload<>(
                    stack.getAmount(), 
                    CardPayload.from(stack.getItem())
            ));
        }
        
        return cardPayloads;
        
    }
}
