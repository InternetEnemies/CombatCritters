package com.internetEnemies.combatCritters.Logic.battles.opponents;

import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoard;
import com.internetEnemies.combatCritters.objects.CritterCard;

import java.util.List;
import java.util.Random;

/**
 * RandomOpponent.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/28/24
 *
 * @PURPOSE:    An opponent that randomly plays cards
 */
public class RandomOpponent extends BasicOpponent {
    private final Random random;
    private final int max;
    public RandomOpponent(IBattleCardFactory cardFactory, List<CritterCard> deck, int max) {
        super(cardFactory, deck);
        this.random = new Random();
        this.max = max;
    }

    @Override
    public void play(IBoard board) throws BattleException {
        int slot = random.nextInt(max);
        if (!deck.isEmpty() && board.getBuffer().getCard(slot) == null){
            board.getBuffer().playCard(slot, pullCard());
        }
    }
}
