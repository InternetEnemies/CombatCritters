package com.combatcritters.critterspring.battle.playerSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internetEnemies.combatCritters.Logic.battles.matchmaking.IMatchmakingService;
import com.internetEnemies.combatCritters.Logic.battles.matchmaking.IPlayer;
import com.internetEnemies.combatCritters.objects.User;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public class PlayerSession implements IPlayerSession {
    private WebSocketSession session;
    private final User user;
    private final IMatchmakingService matchmakingService;
    private final IPlayer player;

    public PlayerSession(WebSocketSession session, User user, IMatchmakingService matchmakingService, IPlayerFactory playerFactory) {
        this.session = session;
        this.user = user;
        this.matchmakingService = matchmakingService;
        this.player = playerFactory.createPlayer(user, this);
    }

    @Override
    public IPlayer getPlayer() {
        return this.player;
    }

    @Override
    public void sendPayload(String resource, Object payload) {
        try {
            if(session.isOpen()) {
                String body = new ObjectMapper().writeValueAsString(payload);
                session.sendMessage(new TextMessage(resource + "\n" +body));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to send Battle Payload",e);
        } catch (IllegalStateException e) {
            // do nothing,
            // as far as I can tell isOpen does not behave correctly
            // in testing it is returning true for a stack that exists from an afterConnectionClosed call which to me should definitely mean the session is closed
            // possibly related issue : https://github.com/spring-projects/spring-framework/issues/30944
        }
    }

    @Override
    public void close() {
        try {
            this.session.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to close session",e);
        } catch (IllegalStateException e) {
            // see issues in sendPayload
        }
    }

    public User getUser() {
        return user;
    }

    public WebSocketSession getSession() {
        return session;
    }
    
    public void setSession(WebSocketSession session) {
        this.session = session;
    }
}
