package com.combatcritters.critterspring.battle.playerSession;

import com.internetEnemies.combatCritters.Logic.battles.matchmaking.IMatchmakingService;
import com.internetEnemies.combatCritters.objects.User;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

/**
 * PlayerSessionManager.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/26/24
 * 
 * @PURPOSE:    manage player sessions
 */
public class PlayerSessionManager implements IPlayerSessionManager {
    private final Map<String, PlayerSession> playerSessions;
    private final Map<Integer, PlayerSession> userSessions;
    private final IMatchmakingService matchmakingService;
    private final IPlayerFactory playerFactory;

    public PlayerSessionManager(IMatchmakingService matchmakingService, IPlayerFactory playerFactory) {
        this.matchmakingService = matchmakingService;
        this.playerFactory = playerFactory;
        this.playerSessions = new HashMap<>();
        this.userSessions = new HashMap<>();
    }
    @Override
    public IPlayerSession createPlayerSession(User user, WebSocketSession session) {
        PlayerSession playerSession = userSessions.get(user.getId());
        if (playerSession == null) {
            // create new session
            playerSession = new PlayerSession(session, user, matchmakingService, playerFactory);
            userSessions.put(user.getId(), playerSession);
            playerSessions.put(session.getId(), playerSession);
        } else {
            // update player session id
            playerSessions.remove(playerSession.getSession().getId());
            playerSessions.put(playerSession.getSession().getId(), playerSession);
            playerSession.setSession(session);
        }
        return playerSession;
    }

    @Override
    public IPlayerSession getPlayerSession(WebSocketSession session) {
        return playerSessions.get(session.getId());
    }

    @Override
    public void closePlayerSession(User user) {
        PlayerSession playerSession = userSessions.get(user.getId());
        if(playerSession != null) {
            playerSessions.remove(playerSession.getSession().getId());
            userSessions.remove(user.getId());
        }
    }
}
