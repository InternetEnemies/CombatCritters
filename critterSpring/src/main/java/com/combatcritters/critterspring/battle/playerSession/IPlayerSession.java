package com.combatcritters.critterspring.battle.playerSession;

import com.internetEnemies.combatCritters.Logic.battles.matchmaking.IPlayer;

/**
 * IPlayerSession.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/26/24
 * 
 * @PURPOSE:    Facilitate 2 way communications between a battle/game instance and a players connection to the api
 */
public interface IPlayerSession {

    /**
     * get player for this session
     */
    IPlayer getPlayer();

    /**
     * send a battle request to this player
     * @param resource resource of the request
     * @param payload payload of the request
     */
    void sendPayload(String resource, Object payload);

    /**
     * close player session
     */
    void close();
}
