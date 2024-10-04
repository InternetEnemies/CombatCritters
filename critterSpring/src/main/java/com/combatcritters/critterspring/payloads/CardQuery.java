package com.combatcritters.critterspring.payloads;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardFilter;
import com.internetEnemies.combatCritters.objects.CardOrder;
import com.internetEnemies.combatCritters.objects.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * CardQuery.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/4/24
 * 
 * @PURPOSE:    definition of query for routes like GET /cards
 */
public record CardQuery(
        List<Integer> id,  
        String order,  
        List<Integer> rarities,  
        Boolean costLess,  
        Integer cost,  
        Boolean rarityInclude,  
        Boolean owned
) {
    public static final CardOrder DEFAULT_ORDER = CardOrder.ID;
    /**
     * get the CardFilter from this query set without a specific user
     */
    public CardFilter toFilter(){
        return toFilter(null);
    }
    /**
     * get the CardFilter from this query set for a specific user
     */
    public CardFilter toFilter(User user){
        List<Card.Rarity> raritiesList;
        if (rarities != null) {
            //map ids to rarities
            try {
                raritiesList = rarities.stream().map(rarity -> Card.Rarity.values()[rarity]).toList();
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        } else {
            raritiesList = List.of();
        }
        return new CardFilter(
                rarityInclude != null ? rarityInclude : false, 
                raritiesList, 
                owned != null ? owned : false, 
                cost,
                costLess!= null ? costLess: false,
                user);
    }
    
    public CardOrder toOrder(){
        if (order != null) {
            try {
                return CardOrder.valueOf(order); 
            } catch (Exception e) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "order: " + order + " is not a valid order"
                );
            }
        } else {
            return DEFAULT_ORDER;
        }
    }
}
