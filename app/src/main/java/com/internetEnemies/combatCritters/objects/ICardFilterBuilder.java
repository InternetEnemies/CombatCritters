package com.internetEnemies.combatCritters.objects;

import java.util.List;

/**
 * ICardFilterBuilder.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/2/24
 * 
 * @PURPOSE:    interface for building card filters
 */
public interface ICardFilterBuilder {
    /**
     * set the rarity filter
     * @param rarity raritys to filter by
     * @param whitelist include or exclude?
     */
    ICardFilterBuilder setRarity(List<Card.Rarity> rarity, boolean whitelist);

    /**
     * set the user to filter by
     * @param user user to filter by
     * @param owned show only owned cards?
     */
    ICardFilterBuilder setUser(User user, boolean owned);

    /**
     * set play cost filter
     * @param cost play cost to filter by
     * @param lessThan filter by less than or greater than
     */
    ICardFilterBuilder setCost(int cost, boolean lessThan);
}
