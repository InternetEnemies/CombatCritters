package com.combatcritters.critterspring.battle.playerSession;

import com.combatcritters.critterspring.battle.BattleSocketAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.internetEnemies.combatCritters.Logic.battles.IBattleOrchestrator;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.matchmaking.IMatchmakingService;
import com.internetEnemies.combatCritters.Logic.battles.matchmaking.IPlayer;
import com.internetEnemies.combatCritters.objects.User;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public class PlayerSession implements IPlayerSession {
    private final WebSocketSession session;
    private final User user;
    private IBattleOrchestrator battleOrchestrator;
    private final IBattleStateObserver battleStateObserver;
    private final IMatchmakingService matchmakingService;
    private final IPlayer player;

    public PlayerSession(WebSocketSession session, User user, IMatchmakingService matchmakingService) {
        this.session = session;
        this.user = user;
        this.battleStateObserver = new BattleSocketAdapter(this);
        this.matchmakingService = matchmakingService;
        this.player = matchmakingService.getPlayer(user, this.battleStateObserver);
    }

    @Override
    public IBattleStateObserver getBattleStateObserver() {
        return battleStateObserver;
    }

    @Override
    public IBattleOrchestrator getBattleOrchestrator() {
        return battleOrchestrator;
    }

    @Override
    public IPlayer getPlayer() {
        return this.player;
    }

    @Override
    public void sendPayload(String resource, Object payload) {
        try {
            String body = new ObjectMapper().writeValueAsString(payload);
            session.sendMessage(new TextMessage(resource + "\n" +body));
        } catch (IOException e) {
            throw new RuntimeException("Failed to send Battle Payload",e);
        }
    }

    public User getUser() {
        return user;
    }

    public WebSocketSession getSession() {
        return session;
    }
}
