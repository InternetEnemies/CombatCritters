package com.combatcritters.critterspring.battle;

import com.combatcritters.critterspring.battle.payloads.BattleMessage;
import com.combatcritters.critterspring.battle.playerSession.IPlayerSession;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.battles.CardState;

import java.util.List;

public class BattleSocketAdapter implements IBattleStateObserver {

    private final IPlayerSession session;

    public BattleSocketAdapter(IPlayerSession session) {
        this.session = session;
    }
    
    @Override
    public void setPlayerTurn(boolean isPlayerTurn) {
        session.sendPayload("battle_message", new BattleMessage("message"));
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
}
