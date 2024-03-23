package com.internetEnemies.combatCritters.presentation.battles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.battles.CardState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BattleViewModel extends ViewModel implements IBattleStateObserver {
    private final MutableLiveData<Boolean> isPlayerTurn;
    private final MutableLiveData<Integer> playerHealth;
    private final MutableLiveData<Integer> enemyHealth;
    private final MutableLiveData<Integer> energy;
    private final List<Card> hand; // ? this may need to be mutable live data or might need a callback for changes
    private final MutableLiveData<Integer> drawPileSize;
    private final MutableLiveData<List<CardState>> buffer;
    private final MutableLiveData<List<CardState>> enemy;
    private final MutableLiveData<List<CardState>> player;

    public BattleViewModel(){
        //these initial values don't really matter, they are here to prevent any null state, these will be set by the Orchestrator
        this.isPlayerTurn = new MutableLiveData<>(true);
        this.playerHealth = new MutableLiveData<>(0);
        this.enemyHealth = new MutableLiveData<>(0);
        this.energy = new MutableLiveData<>(0);
        this.hand = new ArrayList<>();
        this.drawPileSize = new MutableLiveData<>(0);

        //initial state of these kinda matters, again trying to prevent any null pointers
        this.buffer = new MutableLiveData<>(GET_INITIAL_ARRAY());
        this.enemy = new MutableLiveData<>(GET_INITIAL_ARRAY());
        this.player = new MutableLiveData<>(GET_INITIAL_ARRAY());
    }

    private static List<CardState> GET_INITIAL_ARRAY() {
        List<CardState> states = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            states.add(null);
        }
        return states;
    }

    //getters
    public LiveData<Boolean> getIsPlayerTurn() {
        return isPlayerTurn;
    }

    public LiveData<Integer> getPlayerHealth() {
        return playerHealth;
    }

    public LiveData<Integer> getEnemyHealth() {
        return enemyHealth;
    }

    public LiveData<Integer> getEnergy() {
        return energy;
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }

    public LiveData<Integer> getDrawPileSize() {
        return drawPileSize;
    }

    public LiveData<List<CardState>> getBuffer() {
        return buffer;
    }

    public LiveData<List<CardState>> getEnemy() {
        return enemy;
    }

    public LiveData<List<CardState>> getPlayer() {
        return player;
    }

    //setters
    @Override
    public void setPlayerTurn(boolean isPlayerTurn) {
        this.isPlayerTurn.setValue(isPlayerTurn);
    }

    @Override
    public void setPlayerHealth(int health) {
        this.playerHealth.setValue(health);
    }

    @Override
    public void setEnemyHealth(int health) {
        this.enemyHealth.setValue(health);
    }

    @Override
    public void setEnergy(int energy) {
        this.energy.setValue(energy);
    }

    @Override
    public void setHand(List<Card> cards) {
        this.hand.clear();
        this.hand.addAll(cards);
    }

    @Override
    public void setDrawPileSize(int size) {
        this.drawPileSize.setValue(size);
    }

    @Override
    public void setBufferCards(List<CardState> cardStates) {
        this.buffer.setValue(cardStates);
    }

    @Override
    public void setEnemyCards(List<CardState> cardStates) {
        this.enemy.setValue(cardStates);
    }

    @Override
    public void setPlayerCards(List<CardState> cardStates) {
        this.player.setValue(cardStates);
    }

}
