package com.internetEnemies.combatCritters.Logic.battles.matchmaking;

import com.internetEnemies.combatCritters.Logic.battles.IBattleOrchestrator;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleStateException;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.User;

import java.util.List;

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
    private final IMatchStateObserver matchStateObserver;
    private IBattleOrchestrator battleOrchestrator;
    private List<Card> cards;

    public Player(IBattleStateObserver observer, User user, IMatchStateObserver matchStateObserver) {
        battleStateObserver=observer;
        this.user = user;
        this.matchStateObserver = matchStateObserver;
        cards = List.of();
    }

    @Override
    public IBattleOrchestrator getOrchestrator() {
        return errorOnNull(this.battleOrchestrator, "Battle Not Active");
    }

    @Override
    public void setOrchestrator(IBattleOrchestrator orchestrator) {
        this.battleOrchestrator = orchestrator;
    }

    @Override
    public IBattleStateObserver getStateObserver() {
        return errorOnNull(battleStateObserver,"this should never happen"); // I feel like I should check though lol
    }

    @Override
    public User getUser() {
        return user;
    }

    private <T> T errorOnNull(T obj, String error) {
        if (obj == null) {
            throw new BattleStateException(error);
        }
        return obj;
    }

    @Override
    public IMatchStateObserver getMatchStateObserver() {
        return matchStateObserver;
    }

    @Override
    public void setDeck(List<Card> deck) {
        this.cards = deck;
    }

    @Override
    public List<Card> getDeck() {
        return cards;
    }
}
