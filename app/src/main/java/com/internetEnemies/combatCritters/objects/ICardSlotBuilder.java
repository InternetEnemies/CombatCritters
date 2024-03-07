/**
 * ICardSlotBuilder.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-07
 *
 * @PURPOSE:    Builder for card slot
 */

package com.internetEnemies.combatCritters.objects;

import java.util.NavigableMap;

public interface ICardSlotBuilder {
    /**
     * Adds the key and value from a given NavigableMap to the one stored in class.
     * @param weightMap given map to add to
     */
    void addProbabilityMap(NavigableMap<Double, Card.Rarity> weightMap);

    /**
     * Adds weight and rarity as a key and value respectively to the stored NavigableMap
     * @param weight probability of obtaining the rarity
     * @param rarity rarity enum
     */
    void addProbability(double weight, Card.Rarity rarity);

    /**
     * Creates a new instance of CardSlot to from class variables.
     * @return CardSlot object.
     */
    CardSlot build();

    /**
     * Resets all class instance variables.
     */
    void reset();
}
