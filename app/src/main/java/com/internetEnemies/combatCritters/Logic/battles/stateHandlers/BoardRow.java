package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCard;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.objects.battles.CardState;

import java.util.LinkedList;
import java.util.List;

public class BoardRow implements IBoardRow {
    private final BattleCard[] row;
    private final int size;
    public BoardRow(int size, BattleCard[] init) {
        this.size = size;
        this.row = new BattleCard[size];
        initRow(this.row, init);
    }

    /**
     * initialize the row
     * @param row row to copy to
     * @param init initial state for the row
     */
    private void initRow(BattleCard[] row, BattleCard[] init) {
        assert init.length == size && row.length == size;
        System.arraycopy(init, 0,row,0,size);
    }

    @Override
    public void playCard(int pos, BattleCard card) throws BattleException {
        assert pos < size;
        if (row[pos] != null) {
            throw new BattleException("Cannot play card on existing card");
        }
        row[pos] = card;
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
    public BattleCard getCard(int pos) {
        assert pos < size;
        return this.row[pos];
    }

    //todo add remove card method
}
