package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCard;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.events.CardEvent;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.objects.battles.CardState;

import java.util.LinkedList;
import java.util.List;

/**
 * BoardRow.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-24
 *
 * @PURPOSE:    implementation of IBoardRow
 */

public class BoardRow implements IBoardRow {
    private final IEventSystem eventSystem;
    private final IBattleCard[] row;
    private IBoardRow opposing;
    private final int size;
    private final IHealth ownerHealth;
    public BoardRow(IEventSystem eventSystem, IHealth ownerHealth, int size, IBattleCard[] init, IBattleCard[] row) {
        this.eventSystem = eventSystem;
        this.size = size;
        this.row = row;
        this.opposing = null;
        this.ownerHealth = ownerHealth;
        initRow(this.row, init);
    }

    public BoardRow(IEventSystem eventSystem, IHealth ownerHealth, int size, IBattleCard[] init) {
        this(
                eventSystem,
                ownerHealth,
                size,
                init,
                new BattleCard[size]
        );
    }

    @Override
    public void setOpposing(IBoardRow opposing) {
        this.opposing = opposing;
        this.forEachNonNull((i,card) -> card.moveTo(i, this, this.opposing)); // set opposing for all the cards
    }

    /**
     * initialize the row
     * @param row row to copy to
     * @param init initial state for the row
     */
    private void initRow(IBattleCard[] row, IBattleCard[] init) {
        assert init.length == size && row.length == size;
        for (int i = 0; i < size; i++) {
            if (init[i] != null){
                row[i] = init[i];
                row[i].moveTo(i,this,opposing);
            } else {
                row[i] = null;
            }
        }
    }

    @Override
    public void playCard(int pos, IBattleCard card) throws BattleException {
        assert pos < size;
        if (row[pos] != null) {
            throw new BattleException("Cannot play card on existing card");
        }
        row[pos] = card;
        if(card != null){
            card.moveTo(pos,this,opposing);
        }

        this.eventSystem.getOnPlayCard().fireEvent(
                new CardEvent(pos, this, card)
        );
    }

    @Override
    public List<CardState> getCardStateList() {
        List<CardState> cardList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            cardList.add(row[i] != null ? row[i].getCardState():null);// add nulls for null slots
        }
        return cardList;
    }

    @Override
    public IBattleCard getCard(int pos) {
        assert pos < size;
        return this.row[pos];
    }

    @Override
    public void killCard(int pos) throws BattleException {
        IBattleCard card = getCard(pos);
        if(card == null) {
            throw new BattleException("No card to kill at position");
        }
        removeCard(pos);
        card.kill(); //clean/kill card
        this.eventSystem.getOnCardKilled().fireEvent(//fire event
                new CardEvent(pos, this, card)
        );
    }

    @Override
    public void transfer(IBoardRow destination, int to, int from) throws BattleException {
        IBattleCard card = getCard(from);
        if(card == null) {
            throw new BattleException("Cannot move a null card to a new location");
        }
        if(destination.getCard(to) != null) {
            throw new BattleException("Cannot transfer to a non empty slot");
        }
        removeCard(from);
        destination.playCard(to, card);
    }

    /**
     * silently remove a card
     */
    private void removeCard(int pos){
        assert row[pos] != null;
        row[pos] = null;
    }

    @Override
    public void attack(int pos, int damage) {
        IBattleCard target = getCard(pos);
        if (target != null) {
            target.damage(damage);
        } else if (this.ownerHealth != null){
            this.ownerHealth.damage(damage);
        } // if we don't have an owner health we do nothing (maybe an error should be thrown)
    }

    @Override
    public void runAttackPhase(){
        forEachNonNull(((i, card) -> card.attack()));
    }

    @Override
    public IHealth getHealth() {
        return this.ownerHealth;
    }

    /**
     * perform an action on every non null card
     * @param action action to perform
     */
    private void forEachNonNull(CardAction action) {
        for (int i = 0; i < size; i++) {
            IBattleCard card = row[i];
            if(card != null) {
                action.exec(i,card);
            }
        }
    }
}
interface CardAction {
    void exec(int i, IBattleCard card);
}
