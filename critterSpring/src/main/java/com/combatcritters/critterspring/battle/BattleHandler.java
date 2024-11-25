package com.combatcritters.critterspring.battle;

import com.combatcritters.critterspring.battle.payloads.BattleMessage;
import com.combatcritters.critterspring.battle.payloads.BattleRequest;
import com.combatcritters.critterspring.battle.payloads.IBattleRequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    
    public BattleHandler(){
        battleRequestHandlers = new HashMap<>();
        battleRequestHandlers.put("cmd_message",payload -> {
            BattleMessage message = new ObjectMapper().readValue(payload, BattleMessage.class);
            System.out.println(message);
        });
    }
    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println("RECV MESSAGE!");
        // get request object
        BattleRequest req = BattleRequest.fromRaw(message.getPayload());
        // get player session
        // send playerSession and payload to handler
        handleRequest(req);
    }
    
    private void handleRequest(BattleRequest req) {
        try {
            battleRequestHandlers.get(req.resource()).handleRequest(req.payload());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("bad request",e);
        }
    }
    
    
}
