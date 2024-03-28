package com.internetEnemies.combatCritters.Logic.battles.events;

import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRow;

/**
 * CardEvent.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-25
 *
 * @PURPOSE:    a BoardEvent that involves a specific card
 */
public class CardEvent extends BoardEvent{
    private final IBattleCard card;
    public CardEvent(int pos, IBoardRow row, IBattleCard card) {
        super(pos, row);
        this.card = card;
    }

    /**
     * @return Card related to the event
     */
    public IBattleCard getCard() {
        return card;
    }
}
