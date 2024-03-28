package com.internetEnemies.combatCritters.Logic.battles.opponents;

import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoard;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRow;
import com.internetEnemies.combatCritters.objects.CritterCard;

import java.util.List;

public class SingleSlotOpponent implements IBattleOpponent{
    private final IBattleCardFactory cardFactory;
    private final List<CritterCard> deck;
    private final int pos;
    public SingleSlotOpponent(IBattleCardFactory cardFactory, int pos, List<CritterCard> deck) {
        this.cardFactory = cardFactory;
        this.pos = pos;
        this.deck = deck;
    }

    @Override
    public void play(IBoard board) throws BattleException {
        IBoardRow buffer = board.getBuffer();
        if(!deck.isEmpty() && buffer.getCard(pos) == null) {
            buffer.playCard(this.pos,pullCard());
        }
    }

    /**
     * get a card from the 'deck'
     */
    private IBattleCard pullCard(){
        CritterCard card = this.deck.remove(0);
        return cardFactory.getCard(card);
    }
}
