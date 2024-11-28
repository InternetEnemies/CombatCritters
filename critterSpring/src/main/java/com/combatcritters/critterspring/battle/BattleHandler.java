package com.combatcritters.critterspring.battle;

import com.combatcritters.critterspring.battle.payloads.BattleMessage;
import com.combatcritters.critterspring.battle.payloads.BattleRequest;
import com.combatcritters.critterspring.battle.payloads.IBattleRequestHandler;
import com.combatcritters.critterspring.battle.playerSession.IPlayerSession;
import com.combatcritters.critterspring.battle.playerSession.IPlayerSessionManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleInputException;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.objects.User;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * BattleHandler.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/22/24
 * 
 * @PURPOSE:    handle websocket requests by parsing payloads and forwarding commands to the appropriate sessions
 */
public class BattleHandler extends TextWebSocketHandler {
    private final Map<String, IBattleRequestHandler> battleRequestHandlers;
    private final IPlayerSessionManager playerSessionManager;
    private final IUserManager userManager;
    
    public BattleHandler(IPlayerSessionManager playerSessionManager, IUserManager userManager){
        this.playerSessionManager = playerSessionManager;
        this.userManager = userManager;
        battleRequestHandlers = new HashMap<>();
        battleRequestHandlers.put("cmd_message",(payload, session) -> {
            BattleMessage message = new ObjectMapper().readValue(payload, BattleMessage.class);
            try {
                session.getPlayer().getOrchestrator().endTurn();
            } catch (BattleInputException e) {
                throw new RuntimeException("failed to end turn",e);
            }
            System.out.println(message);
        });
    }
    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        // get request object
        BattleRequest req = BattleRequest.fromRaw(message.getPayload());
        // get player session
        IPlayerSession playerSession = getPlayerSession(session);
        // send playerSession and payload to handler
        handleRequest(req, playerSession);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        getPlayerSession(session); // initializes the player session
    }

    private void handleRequest(BattleRequest req, IPlayerSession session) {
        try {
            battleRequestHandlers.get(req.resource()).handleRequest(req.payload(), session);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("bad request",e);
        }
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
