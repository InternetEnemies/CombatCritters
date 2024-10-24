/**
 * CardSlot.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-01
 *
 * @PURPOSE:    Stores the probability of pulling a card rarity for each card in a pack.
 *              Allows for different cards in a pack to have different odds of rarity
 */

package com.internetEnemies.combatCritters.objects;

import java.io.Serializable;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CardSlot implements Serializable {
    private final NavigableMap<Double,Card.Rarity> cardPullChances = new TreeMap<>(); //Stores Weight as key and card rarity (ex. [Rare, 25])
    private final NavigableMap<Double,Card.Rarity> rarityWeights;

    /**
     * Constructor
     * @param chances map of card and their chances
     */
    public CardSlot(NavigableMap<Double, Card.Rarity> chances) {
        this.rarityWeights = chances;

        double sum = 0;
        for (Map.Entry<Double, Card.Rarity> value: chances.entrySet()) {
            sum += value.getKey();
            cardPullChances.put(sum, value.getValue());
        }

    }

    /**
     * get the Navigable map used for rolling what cards are obtained from a pack
     * @return map of weight sums
     */
    public NavigableMap<Double,Card.Rarity> getCardPullChances(){
        return cardPullChances;
    }

    /**
     * get the map of rarity weights for the pack
     * @return map of rarity weights
     */
    public NavigableMap<Double, Card.Rarity> getRarityWeights() {
        return this.rarityWeights;
    }

}
