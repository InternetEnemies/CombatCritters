package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;

/**
 * BoardFactory.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/28/24
 *
 * @PURPOSE:    make boards
 */
public class BoardFactory implements IBoardFactory{
    @Override
    public IBoard getBoard(IEventSystem eventSystem, int size, IBoardRow buffer, IBoardRow enemy, IBoardRow player) {
        return new Board(eventSystem, size, buffer, enemy, player);
    }
}
