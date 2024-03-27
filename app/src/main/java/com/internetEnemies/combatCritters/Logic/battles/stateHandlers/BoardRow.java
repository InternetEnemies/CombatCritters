package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCard;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.events.CardEvent;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.objects.battles.CardState;

import java.util.LinkedList;
import java.util.List;

public class BoardRow implements IBoardRow {
    private final IEventSystem eventSystem;
    private final IBattleCard[] row;
    private IBoardRow opposing;
    private final int size;
    public BoardRow(IEventSystem eventSystem, int size, BattleCard[] init) {
        this.eventSystem = eventSystem;
        this.size = size;
        this.row = new BattleCard[size];
        this.opposing = null;
        initRow(this.row, init);
    }

    /**
     * set the opposing row
     * @param opposing row to set to
     */
    public void setOpposing(IBoardRow opposing) {
        this.opposing = opposing;
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
        row[pos] = null; // remove
        card.kill(); //clean/kill card
        this.eventSystem.getOnCardKilled().fireEvent(//fire event
                new CardEvent(pos, this, card)
        );
    }

    @Override
    public void attack(int pos, int damage) {
        //todo
    }
}
