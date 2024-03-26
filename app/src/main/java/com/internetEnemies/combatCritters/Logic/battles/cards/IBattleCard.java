package com.internetEnemies.combatCritters.Logic.battles.cards;

import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRow;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IHealth;
import com.internetEnemies.combatCritters.objects.battles.CardState;

public interface IBattleCard {
    /**
     * @return CardState representation of this card
     */
    CardState getCardState();

    /**
     * @return health object for this card
     */
    IHealth getHealth();

    /**
     * move the card to a position in a row with an opposing row
     *
     * @param slot     slot card is played at
     * @param parent   row this card belongs to
     * @param opposing row this card is against (can be null)
     */
    void moveTo(int slot, IBoardRow parent, IBoardRow opposing);
}
