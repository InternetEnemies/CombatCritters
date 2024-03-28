package com.internetEnemies.combatCritters.Logic.battles.cards;

import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRow;
import com.internetEnemies.combatCritters.objects.battles.CardState;

public interface IBattleCard {
    /**
     * @return CardState representation of this card
     */
    CardState getCardState();

    /**
     * @return health of this card
     */
    int getHealth();

    /**
     * move the card to a position in a row with an opposing row
     *
     * @param slot     slot card is played at
     * @param parent   row this card belongs to
     * @param opposing row this card is against (can be null)
     */
    void moveTo(int slot, IBoardRow parent, IBoardRow opposing);

    /**
     * runs whatever logic this card needs when it is killed
     */
    void kill();

    /**
     * attack the card
     * @param amount amount of damage to deal
     */
    void damage(int amount);

    /**
     * heal the card
     * @param amount amount of health to heal
     */
    void heal(int amount);

    /**
     * tell the card to use its attack
     */
    void attack();
}
