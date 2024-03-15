package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;

/**
 * ICardDeconstructor.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-15
 *
 * @PURPOSE:    Interface for CardDeconstructor class, which handles converting cards to currency.
 */

public interface ICardDeconstructor {
    /**
     * Adds a value of currency to the player's currency inventory based on the card's rarity.
     * @param card the card being deconstructed
     */
    void deconstruct(Card card);
    /**
     * Deconstructs a certain quantity of cards in the user's inventory.
     *
     * @param card   the card being deconstructed
     * @param amount the amount of the card to be removed
     */
    void deconstruct(Card card, int amount);

    /**
     * Gets the resulting amount of currency to be obtained from deconstructing an amount of cards
     * @param card the card(s) to be removed
     * @param amount the amount to be removed
     * @return the amount of currency to be received.
     */
    int getResult(Card card, int amount);
}
