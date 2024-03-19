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
public class GameCard {
    private final int health;
    private final CritterCard card;

    public GameCard(int health, CritterCard card) {
        this.health = health;
        this.card = card;
    }

    public int getCurrHealth() {
        return health;
    }

    public CritterCard getCard() {
        return card;
    }
}
