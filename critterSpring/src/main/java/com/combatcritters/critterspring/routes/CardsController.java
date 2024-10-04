package com.combatcritters.critterspring.routes;

import com.combatcritters.critterspring.payloads.CardCritter;
import com.combatcritters.critterspring.payloads.CardPayload;
import com.combatcritters.critterspring.payloads.CardQuery;
import com.combatcritters.critterspring.payloads.ItemStackPayload;
import com.internetEnemies.combatCritters.Logic.inventory.cards.CardCatalog;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    
    private final CardCatalog catalog;
    
    @Autowired
    public CardsController(CardCatalog catalog) {
        this.catalog = catalog;
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
        return new CardPayload<>(
                        id,
                        "name",
                        0,
                        1,
                        "image",
                        "critter",
                        "description",
                        new CardCritter(1, 1, List.of())
                );
    }
    
    @GetMapping("/users/{userid}/cards")
    ItemStackPayload<CardPayload<?>>[] getUserCards(@PathVariable int userid){
        return new ItemStackPayload[]{
                new ItemStackPayload<CardPayload<?>>(
                        1, 
                        new CardPayload<>(
                            1,
                            "name",
                            0,
                            1,
                            "image",
                            "critter",
                            "description",
                            new CardCritter(1, 1, List.of())
                        )
                )
        };
        
    }
}
