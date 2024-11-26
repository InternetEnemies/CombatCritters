package com.combatcritters.critterspring.battle;

import com.combatcritters.critterspring.battle.payloads.BattleMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.battles.CardState;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

public class BattleSocketAdapter implements IBattleStateObserver {

    private final WebSocketSession session;

    public BattleSocketAdapter(WebSocketSession session) {
        this.session = session;
    }
    
    @Override
    public void setPlayerTurn(boolean isPlayerTurn) {
        sendPayload("battle_message", new BattleMessage("message"));
    }

    @Override
    public void setPlayerHealth(int health) {

    }

    @Override
    public void setEnemyHealth(int health) {

    }

    @Override
    public void setEnergy(int energy) {

    }

    @Override
    public void setHand(List<Card> cards) {

    }

    @Override
    public void setDrawPileSize(int size) {

    }

    @Override
    public void setBufferCards(List<CardState> cardStates) {

    }

    @Override
    public void setEnemyCards(List<CardState> cardStates) {

    }

    @Override
    public void setPlayerCards(List<CardState> cardStates) {

    }
    
    private void sendPayload(String resource, Object payload) {
        try {
            String body = new ObjectMapper().writeValueAsString(payload);
            session.sendMessage(new TextMessage(resource + "\n" +body));
        } catch (IOException e) {
            throw new RuntimeException("Failed to send Battle Payload",e);
        }
    }
}
