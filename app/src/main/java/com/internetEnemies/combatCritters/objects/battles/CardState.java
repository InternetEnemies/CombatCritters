package com.internetEnemies.combatCritters.objects.battles;

import com.internetEnemies.combatCritters.objects.CritterCard;

/**
 * GameCard.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/19/24
 *
 * @PURPOSE: add extra data to a card for use in battles
 */
public class CardState {
    private final int health;
    private final CritterCard card;

    public CardState(int health, CritterCard card) {
        this.health = health;
        this.card = card;
    }

    /**
     * get the current health of a card
     */
    public int getCurrHealth() {
        return health;
    }

    /**
     * Get Card contained in this CardState
     */
    public CritterCard getCard() {
        return card;
    }
}
