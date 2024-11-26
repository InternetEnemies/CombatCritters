package com.combatcritters.critterspring.battle.playerSession;

import com.combatcritters.critterspring.battle.BattleSocketAdapter;
import com.internetEnemies.combatCritters.Logic.battles.IBattleOrchestrator;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.objects.User;
import org.springframework.web.socket.WebSocketSession;

public class PlayerSession implements IPlayerSession {
    private final WebSocketSession session;
    private final User user;
    private IBattleOrchestrator battleOrchestrator;
    private final IBattleStateObserver battleStateObserver;

    public PlayerSession(WebSocketSession session, User user) {
        this.session = session;
        this.user = user;
        this.battleStateObserver = new BattleSocketAdapter(session);
    }

    @Override
    public IBattleStateObserver getBattleStateObserver() {
        return battleStateObserver;
    }

    @Override
    public IBattleOrchestrator getBattleOrchestrator() {
        return battleOrchestrator;
    }

    public User getUser() {
        return user;
    }

    public WebSocketSession getSession() {
        return session;
    }
}
