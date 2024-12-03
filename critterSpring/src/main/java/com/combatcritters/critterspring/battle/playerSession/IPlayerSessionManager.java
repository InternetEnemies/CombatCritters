package com.combatcritters.critterspring.battle.playerSession;

import com.internetEnemies.combatCritters.objects.User;
import org.springframework.web.socket.WebSocketSession;

/**
 * IPlayerSessionManager.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/26/24
 * 
 * @PURPOSE:    manager player sessions
 */
public interface IPlayerSessionManager {
    /**
     * create a new player session from a user
     */
    IPlayerSession createPlayerSession(User user, WebSocketSession session);

    /**
     * get the player session for a websocket session
     */
    IPlayerSession getPlayerSession(WebSocketSession session);

    /**
     * close a player session
     */
    void closePlayerSession(User user);

    /**
     * close a player session
     */
    void closePlayerSession(WebSocketSession session);
}
