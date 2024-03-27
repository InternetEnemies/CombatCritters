package com.internetEnemies.combatCritters.Logic.battles.events;

import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRow;

/**
 * CardHealthEvent.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-27
 *
 * @PURPOSE:    represents an card event where the health has changed by some amount
 */
public class CardHealthEvent extends CardEvent{
    private final int healthChange;
    public CardHealthEvent(int pos, IBoardRow row, IBattleCard card, int healthChange) {
        super(pos, row, card);
        this.healthChange = healthChange;
    }

    /**
     * @return magnitude of the health change related to this event
     */
    public int getHealthChange() {
        return healthChange;
    }
}
