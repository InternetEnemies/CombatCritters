package com.combatcritters.critterspring.battle.payloads;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * IBattleRequestHandler.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/25/24
 * 
 * @PURPOSE:    interface for handling a request payload and forwarding it to a player session.  
 *              In general implementations of this interface should parse the request payload and forward that object to
 *              the player session method
 */
public interface IBattleRequestHandler {
    /**
     * handle the request payload and forward the result
     */
    void handleRequest(String payload) throws JsonProcessingException; //todo add Player session
}
