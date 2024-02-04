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
     * @Properties a final IDeckInventory reference, pass-in from DeckHandler
     */

    /**
     * Add a card to the selected deck
     * @param insert the card object to insert with
     * @param deckInfo the target deck deckDetails object
     * @return true if the card successfully added,
     *         false if the an error appear and the adding fails
     */
    boolean addCard(Card insert, DeckDetails deckInfo);

    /**
     * Remove a card from the selected deck
     * @param remove the card object to remove with
     * @param deckInfo the target deck deckDetails object
     * @return true if the card successfully removed,
     *         false if the an error appear and the removing fails
     */
    boolean removeCard(Card remove, DeckDetails deckInfo);

    /**
     * get all cards from the selected deck
     * @param deckInfo the target deck deckDetails object
     * @return a map containing cards and its quantity,
     *         null if no deck is selected
     */
    Map<Card,Integer> getCards(DeckDetails deckInfo);
}
