package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;

public interface ICardFilterBuilder {

    /**
     * sets the rarities (added by addRarity) to be treated like a blacklist
     */
    void setRarityBlacklist();

    /**
     * add a rarity to the filter list
     * @param rarity rarity to filter
     */
    void addRarity(Card.Rarity rarity);

    /**
     * set filter to only include owned cards
     * by default all cards are included
     */
    void setOwned();

    /**
     * filter by the play cost of the card
     * @param cost int cost to filter by
     * @param less set true to get cards less than or equal to cost
     *             set false to get cards greater than or equal to cost
     */
    void setCost(int cost, boolean less);
}
