package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCard;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleRuntimeException;
import com.internetEnemies.combatCritters.objects.battles.CardState;

import java.util.LinkedList;
import java.util.List;

public class Board implements IBoard {
    private final BattleCard[] buffer;
    private final BattleCard[] enemy;
    private final BattleCard[] player;
    private final int size;

    public Board(int size, BattleCard[] initBuffer, BattleCard[] initEnemy, BattleCard[] initPlayer) {
        this.size = size;
        buffer = new BattleCard[size];
        enemy = new BattleCard[size];
        player = new BattleCard[size];
        initRow(buffer, initBuffer);
        initRow(enemy, initEnemy);
        initRow(player, initPlayer);
    }

    /**
     * initialize a row on the board from an input array
     * @param row output row
     * @param init input row
     */
    private void initRow(BattleCard[] row, BattleCard[] init){
        assert row.length == init.length && row.length == size;
        System.arraycopy(init, 0, row, 0, size);
    }

    /**
     * play a card at a position
     * @param pos position to play at
     * @param row row to play in
     * @param card card to play
     * @throws BattleException thrown when trying to play a card in an occupied space
     */
    private void playCard(int pos, int row, BattleCard card) throws BattleException {
        assert pos < size-1;
        BattleCard[] cards = rowFromIdx(row);
        if (cards[pos] != null) {
            throw new BattleException("Cannot play card on existing card");
        }

        cards[pos] = card;
        //todo fire event
    }

    //todo remove card methods
    @Override
    public void playCardBuffer(int pos, BattleCard card) throws BattleException {
        playCard(pos, 2,card);
    }

    @Override
    public void playCardEnemy(int pos, BattleCard card) throws BattleException {
        playCard(pos, 1,card);
    }

    @Override
    public void playCardPlayer(int pos, BattleCard card) throws BattleException {
        playCard(pos, 0,card);
    }

    /**
     * get the row from a row index
     */
    private BattleCard[] rowFromIdx(int row) {
        BattleCard[] rowArr;
        switch (row) {
            case 0:
                rowArr = player;
                break;
            case 1:
                rowArr = enemy;
                break;
            case 2:
                rowArr = buffer;
                break;
            default:
                throw new BattleRuntimeException("Invalid Row Index");
        }
        return rowArr;
    }

    @Override
    public BattleCard getCard(int pos, int row) {
        BattleCard[] cards = rowFromIdx(row);
        return cards[pos];
    }

    @Override
    public List<CardState> getBuffer() {
        return listFromRow(player);
    }

    @Override
    public List<CardState> getEnemy() {
        return listFromRow(enemy);
    }

    @Override
    public List<CardState> getPlayer() {
        return listFromRow(player);
    }

    /**
     *  get a list representation of a row array (used for rendering)
     */
    private List<CardState> listFromRow(BattleCard[] row) {
        List<CardState> cardList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            cardList.add(row[i] != null ? row[i].getCardState():null);// add nulls for null slots
        }
        return cardList;
    }
}
