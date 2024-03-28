package com.internetEnemies.combatCritters.Logic.battles.opponents;

import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCardFactory;
import com.internetEnemies.combatCritters.objects.CritterCard;

import java.util.List;

/**
 * BasicOpponent.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/28/24
 *
 * @PURPOSE:    common class for basic opponents
 */
public abstract class BasicOpponent implements IBattleOpponent {
    protected final IBattleCardFactory cardFactory;
    protected final List<CritterCard> deck;

    public BasicOpponent(IBattleCardFactory cardFactory, List<CritterCard> deck) {
        this.cardFactory = cardFactory;
        this.deck = deck;
    }

    /**
     * get a card from the 'deck'
     */
    protected IBattleCard pullCard() {
        CritterCard card = this.deck.remove(0);
        return cardFactory.getCard(card);
    }
}
