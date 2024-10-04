package com.combatcritters.critterspring.payloads;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardFilter;
import com.internetEnemies.combatCritters.objects.CardOrder;

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
     * get the CardFilter from this query set
     */
    public CardFilter toFilter(){
        List<Card.Rarity> raritiesList;
        if (rarities != null) {
            //map ids to rarities
            raritiesList = rarities.stream().map(rarity -> Card.Rarity.values()[rarity]).toList();
        } else {
            raritiesList = List.of();
        }
        return new CardFilter(
                rarityInclude != null ? rarityInclude : false, 
                raritiesList, 
                owned != null ? owned : false, 
                cost,
                costLess!= null ? costLess: false);
    }
    
    public CardOrder toOrder(){
        if (order != null) {
            return CardOrder.valueOf(order); // this will throw an error if the order doesn't exist triggering a 400 
        } else {
            return DEFAULT_ORDER;
        }
    }
}
