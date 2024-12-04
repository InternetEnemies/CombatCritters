package com.combatcritters.critterspring.battle;

import com.combatcritters.critterspring.battle.payloads.CardStatePayload;
import com.combatcritters.critterspring.battle.payloads.events.*;
import com.combatcritters.critterspring.battle.payloads.matching.GameFoundEvent;
import com.combatcritters.critterspring.battle.payloads.matching.MatchEndedEvent;
import com.combatcritters.critterspring.battle.playerSession.IPlayerSession;
import com.combatcritters.critterspring.payloads.CardPayload;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.matchmaking.IMatchStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.matchmaking.IPlayer;
import com.internetEnemies.combatCritters.Logic.battles.matchmaking.MatchEndType;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.battles.CardState;

import java.util.List;

public class BattleSocketAdapter implements IBattleStateObserver, IMatchStateObserver {
    private static final String PLAYER = "player";
    private static final String PLAYER_BUFFER = "player_buffer";
    private static final String OPPONENT = "opponent";
    private static final String OPPONENT_BUFFER = "opponent_buffer";

    private final IPlayerSession session;

    public BattleSocketAdapter(IPlayerSession session) {
        this.session = session;
    }
    
    @Override
    public void setPlayerTurn(boolean isPlayerTurn) {
        session.sendPayload("player_turn_event",new PlayerTurnEvent(isPlayerTurn));
    }

    @Override
    public void setPlayerHealth(int health) {
        session.sendPayload("health_event", new HealthEvent(health, true));
    }

    @Override
    public void setEnemyHealth(int health) {
        session.sendPayload("health_event", new HealthEvent(health, false));
    }

    @Override
    public void setEnergy(int energy) {
        session.sendPayload("energy_event", new EnergyEvent(energy, true));
    }

    @Override
    public void setHand(List<Card> cards) {
        session.sendPayload("hand_event", new HandEvent(CardPayload.fromList(cards)));
    }

    @Override
    public void setDrawPileSize(int size) {
        session.sendPayload("draw_pile_event", new DrawPileEvent(size));
    }

    @Override
    public void setEnemyBufferCards(List<CardState> cardStates) {
        session.sendPayload("board_state_event",
                new BoardStateEvent(CardStatePayload.listFrom(cardStates),OPPONENT_BUFFER));
    }

    @Override
    public void setEnemyCards(List<CardState> cardStates) {
        session.sendPayload("board_state_event",
                new BoardStateEvent(CardStatePayload.listFrom(cardStates),OPPONENT));
    }

    @Override
    public void setPlayerCards(List<CardState> cardStates) {
        session.sendPayload("board_state_event",
                new BoardStateEvent(CardStatePayload.listFrom(cardStates),PLAYER));
    }

    @Override
    public void matchFound(IPlayer opponent) {
        session.sendPayload("game_found_event",
                new GameFoundEvent(opponent.getUser().getUsername()));
    }

    @Override
    public void matchEnded(MatchEndType endType) {
        String type = switch (endType) {
            case PLAYER_LEFT -> "player_left";
            case WIN -> "win";
            case LOSS -> "loss";
        };
        session.sendPayload("match_ended_event",
                new MatchEndedEvent(type));
        session.close();
    }
}
