package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.events.IEventHost;
import com.internetEnemies.combatCritters.Logic.battles.events.TurnEvent;

/**
 * ITurn.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     12/4/24
 *
 * @PURPOSE:    provide interface for turn state
 */
public interface ITurn {
    /**
     * is the turn active
     */
    boolean isTurn();

    /**
     * set turn state
     */
    void setTurn(boolean turn);

    /**
     * get the event host for this turn handler
     */
    IEventHost<TurnEvent> getEventHost();
}
