package com.internetEnemies.combatCritters.objects;

import java.util.List;

/**
 * CardFilter.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-02
 *
 * @PURPOSE:    Data needed for filtering cards
 */
public class CardFilter {
    private final boolean RarityWhitelist;
    private final List<Card.Rarity> rarities;
    private final boolean owned;
    private final Integer cost;
    private final boolean lessThan;

    public CardFilter(boolean rarityWhitelist, List<Card.Rarity> rarities, boolean owned, Integer cost, boolean lessThan) {
        RarityWhitelist = rarityWhitelist;
        this.rarities = rarities;
        this.owned = owned;
        this.cost = cost;
        this.lessThan = lessThan;
    }

    public boolean isRarityWhitelist() {
        return RarityWhitelist;
    }

    public List<Card.Rarity> getRarities() {
        return rarities;
    }

    public boolean isOwned() {
        return owned;
    }

    public Integer getCost() {
        return cost;
    }

    public boolean isLessThan() {
        return lessThan;
    }
}
