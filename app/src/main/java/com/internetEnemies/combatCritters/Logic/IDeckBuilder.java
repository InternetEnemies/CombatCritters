/**
 * IDeckBuilder.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     01-February-2024
 *
 * PURPOSE:     An interface for Deck builder, this should be a deck editor,
 *              mainly control of the modification of a single deck
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.util.Map;

public interface IDeckBuilder {
    /**
     * @Private
     * @Properties a current deck, can be changed
     * @Properties a final IDeckInventory reference, pass-in from DeckHandler
     */


    /**
     * select the given deck for further modify, using deckInfo to search from the DeckInventory
     * @param deckInfo the target deck deckDetails object
     * @return true if the deck successfully selected, false if the an error appear and the deck is not selected
     */
    boolean selectDeck(DeckDetails deckInfo);


    /**
     * Add a card to the selected deck
     * @param insert the card object to insert with
     * @return true if the card successfully added, false if the an error appear and the adding fails
     */
    boolean addCard(Card insert);

    /**
     * Remove a card from the selected deck
     * @param remove the card object to remove with
     * @return true if the card successfully removed, false if the an error appear and the removing fails
     */
    boolean removeCard(Card remove);

    /**
     * get all cards from the selected deck
     * @return a map containing cards and its quantity, null if no deck is selected
     */
    Map<Card,Integer> getCards();
}
