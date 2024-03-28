package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;

public interface IBoardRowFactory {
    /**
     * get a board row
     * @param eventSystem event system to use
     * @param health health object for the row
     * @param size size of the row
     * @param init initial row state
     * @return board row from provided params
     */
    IBoardRow getRow(IEventSystem eventSystem, IHealth health, int size, IBattleCard[] init);
}
