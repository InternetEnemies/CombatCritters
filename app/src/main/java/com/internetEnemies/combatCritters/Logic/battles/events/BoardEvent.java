package com.internetEnemies.combatCritters.Logic.battles.events;

import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRow;

/**
 * BoardEvent.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-25
 *
 * @PURPOSE:    event for anything that happens on the board
 */
public class BoardEvent {
    private final int pos;
    private final IBoardRow row;

    public BoardEvent(int pos, IBoardRow row) {
        this.pos = pos;
        this.row = row;
    }

    /**
     * @return position in the row of the event
     */
    public int getPos() {
        return pos;
    }

    /**
     * @return row of the event
     */
    public IBoardRow getRow() {
        return row;
    }
}
