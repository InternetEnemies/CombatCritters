package com.internetEnemies.combatCritters.Logic.battles.matchmaking;

import com.internetEnemies.combatCritters.Logic.battles.IBattleOrchestrator;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.objects.User;

/**
 * Player.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/27/24
 *
 * @PURPOSE:    provide player I/O interfaces
 */
public class Player implements IPlayer{
    private final IBattleStateObserver battleStateObserver;
    private final User user;
    private IBattleOrchestrator battleOrchestrator;

    public Player(IBattleStateObserver observer, User user) {
        battleStateObserver=observer;
        this.user = user;
    }

    @Override
    public IBattleOrchestrator getOrchestrator() {
        return this.battleOrchestrator;
    }

    @Override
    public void setOrchestrator(IBattleOrchestrator orchestrator) {
        this.battleOrchestrator = orchestrator;
    }

    @Override
    public IBattleStateObserver getStateObserver() {
        return battleStateObserver;
    }

    @Override
    public User getUser() {
        return user;
    }
}
