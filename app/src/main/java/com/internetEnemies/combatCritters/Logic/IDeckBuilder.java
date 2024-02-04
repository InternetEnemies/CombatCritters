/**
 * IDeckBuilder.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     01-February-2024
 *
 * PURPOSE:     An interface for Deck builder
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.Iterator;

public interface IDeckBuilder {

    /**
     * Creates a new Deck and store the deckId in "givenIndex"
     * @param name name of the deck to be created
     * @return true if the deck successfully created, false if the an error appear and the deck is not created
     */
    boolean createNewDeck(String name);


    /**
     * Add a card to a specific deck
     * @param insert the card object to insert with
     * @param deck the deck wanted to add a card
     * @return true if the card successfully added, false if the an error appear and the adding fails
     */
    boolean addCard(Card insert, IDeck deck);

    /**
     * Remove a card to a specific deck
     * @param remove the card object to remove with
     * @param deck the deck wanted to remove a card
     * @return true if the card successfully removed, false if the an error appear and the removing fails
     */
    boolean removeCard(Card remove, IDeck deck);

    /**
     * View to the deck inventory
     * @return an iterator to get the current decks
     */
    Iterator<IDeck> iterator();
}
