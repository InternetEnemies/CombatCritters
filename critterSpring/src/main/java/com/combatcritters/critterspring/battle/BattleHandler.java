package com.combatcritters.critterspring.battle;

import com.combatcritters.critterspring.battle.payloads.BattleRequest;
import com.combatcritters.critterspring.battle.playerSession.IPlayerSession;
import com.combatcritters.critterspring.battle.playerSession.IPlayerSessionManager;
import com.combatcritters.critterspring.battle.request.ICritterRequestHandler;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.objects.User;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * BattleHandler.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/22/24
 * 
 * @PURPOSE:    handle websocket requests by parsing payloads and forwarding commands to the appropriate sessions
 */
public class BattleHandler extends TextWebSocketHandler {
    private final IPlayerSessionManager playerSessionManager;
    private final IUserManager userManager;
    private final ICritterRequestHandler critterRequestHandler;
    
    public BattleHandler(IPlayerSessionManager playerSessionManager, IUserManager userManager, ICritterRequestHandler critterRequestHandler){
        this.playerSessionManager = playerSessionManager;
        this.userManager = userManager;
        this.critterRequestHandler = critterRequestHandler;
    }
    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        // get request object
        BattleRequest req = BattleRequest.fromRaw(message.getPayload());
        // get player session
        IPlayerSession playerSession = getPlayerSession(session);
        // send playerSession and payload to handler
        critterRequestHandler.handle(playerSession, req);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        getPlayerSession(session); // initializes the player session
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // stop matchmaking / match / game
        playerSessionManager.closePlayerSession(session);
        super.afterConnectionClosed(session, status);
    }

    private IPlayerSession getPlayerSession(WebSocketSession session) {
        IPlayerSession playerSession = playerSessionManager.getPlayerSession(session);
        if (playerSession == null) {// if no session is found create one
            User user = userManager.getUserByUsername(session.getPrincipal().getName());
            playerSession = playerSessionManager.createPlayerSession(user, session);
        }
        return playerSession;
    }
    
    
}
