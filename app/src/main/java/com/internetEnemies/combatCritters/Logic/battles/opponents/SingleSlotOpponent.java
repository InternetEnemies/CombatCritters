package com.internetEnemies.combatCritters.Logic.battles.opponents;

import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoard;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRow;
import com.internetEnemies.combatCritters.objects.CritterCard;

import java.util.List;

/**
 * SingleSlotOpponent.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/28/24
 *
 * @PURPOSE:    an opponent that just keeps playing cards in the same slot
 */
public class SingleSlotOpponent extends BasicOpponent {
    private final int pos;
    public SingleSlotOpponent(IBattleCardFactory cardFactory, int pos, List<CritterCard> deck) {
        super(cardFactory, deck);
        this.pos = pos;
    }

    @Override
    public void play(IBoard board) throws BattleException {
        IBoardRow buffer = board.getBuffer();
        if(!deck.isEmpty() && buffer.getCard(pos) == null) {
            buffer.playCard(this.pos,pullCard());
        }
    }

}
