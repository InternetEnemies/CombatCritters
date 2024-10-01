package com.combatcritters.critterspring.routes;

import com.combatcritters.critterspring.payloads.CardCritter;
import com.combatcritters.critterspring.payloads.CardItem;
import com.combatcritters.critterspring.payloads.CardPayload;
import com.combatcritters.critterspring.payloads.ItemStackPayload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {
    @GetMapping("/cards")
    CardPayload<?>[] getCards(){
        return new CardPayload[]{
                new CardPayload<>(
                        1,
                        "name",
                        0,
                        1,
                        "image",
                        "critter",
                        "description",
                        new CardCritter(1, 1, new int[]{})
                ),
                new CardPayload<>(
                        1,
                        "name",
                        0,
                        1,
                        "image",
                        "critter",
                        "description",
                        new CardItem(1)
                )
        };
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
                        new CardCritter(1, 1, new int[]{})
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
                            new CardCritter(1, 1, new int[]{})
                        )
                )
        };
        
    }
}
