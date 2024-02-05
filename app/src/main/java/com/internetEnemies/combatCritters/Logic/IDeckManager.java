/**
 * IDeckManger.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     05-February-2024
 *
 * PURPOSE:     An interface for Deck Manager, wrap-up class containing
 *              a Deck Builder, a Deck Handler
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.util.List;
import java.util.Map;

public interface IDeckManager {
    /**
     * @Private
     * @Properties a DeckHandler object
     * @Properties a DeckBuilder object
     */

    /**
     * Creates a new Deck into deckInventory
     * @param name name of the deck to be created
     * @return DeckDetails of the deck if the deck successfully created,
     *         null if the an error appear and the deck is not created
     */
    DeckDetails handlerCreateDeck(String name);

    /**
     * Delete the Deck with given DeckDetails
     * @param deckInfo the DeckDetails of the deck wanted to delete
     * @return true if the deck successfully deleted, false if the an error appear and the deck still remains
     */
    boolean handlerDeleteDeck(DeckDetails deckInfo);

    /**
     * get the list of the decks for further purpose
     * @return A list of DeckDetails of current stored decks,
     *         null if nothing inside the deckInventory
     */
    List<DeckDetails> handlerGetDecks();

    /**
     * Add a card to the selected deck
     * @param insert the card object to insert with
     * @param deckInfo the target deck deckDetails object
     * @return true if the card successfully added,
     *         false if the an error appear and the adding fails
     */
    boolean builderAddCard(Card insert, DeckDetails deckInfo);

    /**
     * Remove a card from the selected deck
     * @param remove the card object to remove with
     * @param deckInfo the target deck deckDetails object
     * @return true if the card successfully removed,
     *         false if the an error appear and the removing fails
     */
    boolean builderRemoveCard(Card remove, DeckDetails deckInfo);

    /**
     * get all cards from the selected deck
     * @param deckInfo the target deck deckDetails object
     * @return a map containing cards and its quantity,
     *         null if no deck is selected
     */
    Map<Card,Integer> builderGetCards(DeckDetails deckInfo);
}
