package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.events.EventHost;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventHost;
import com.internetEnemies.combatCritters.Logic.battles.events.TurnEvent;

/**
 * Turn.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     12/4/24
 *
 * @PURPOSE:    hold turn state
 */
public class Turn implements ITurn{
    private boolean isTurn;
    private final IEventHost<TurnEvent> eventHost;
    public Turn(){
        this(true, new EventHost<>());
    }
    public Turn(boolean init, IEventHost<TurnEvent> eventHost){
        this.isTurn = init;
        this.eventHost = eventHost;
    }
    @Override
    public boolean isTurn() {
        return isTurn;
    }

    @Override
    public void setTurn(boolean turn) {
        this.isTurn = turn;
        eventHost.fireEvent(new TurnEvent(isTurn));
    }

    @Override
    public IEventHost<TurnEvent> getEventHost() {
        return this.eventHost;
    }
}
