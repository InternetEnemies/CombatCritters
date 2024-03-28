package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;

/**
 * BoardRowFactory.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/28/24
 *
 * @PURPOSE:    make board rows
 */
public class BoardRowFactory implements IBoardRowFactory {
    @Override
    public IBoardRow getRow(IEventSystem eventSystem, IHealth health, int size, IBattleCard[] init){
        return new BoardRow(eventSystem, health, size, init);
    }
}
