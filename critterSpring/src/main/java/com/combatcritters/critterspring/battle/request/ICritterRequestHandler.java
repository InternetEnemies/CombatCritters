package com.combatcritters.critterspring.battle.request;

import com.combatcritters.critterspring.battle.payloads.BattleRequest;
import com.combatcritters.critterspring.battle.playerSession.IPlayerSession;

/**
 * ICritterRequestHandler.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/28/24
 *
 * @PURPOSE:    interface for registering handlers and handling battle requests
 */
public interface ICritterRequestHandler {
    /**
     * register a new critter request controller
     * @param requestHandler controller to register
     */
    void register(Object requestHandler);

    /**
     * forward a request to its controller
     * @param battleRequest request to forward
     */
    void handle(IPlayerSession session, BattleRequest battleRequest);
}
