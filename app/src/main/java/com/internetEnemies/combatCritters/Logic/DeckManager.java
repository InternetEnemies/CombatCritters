package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.util.List;
import java.util.Map;

public class DeckManager{

    private IDeckInventory deckInventory;
    public DeckManager(){
        deckInventory = Database.getInstance().getDeckInventory();
    }

    public DeckManager(IDeckInventory deckInventory){
        //testing constructor
        this.deckInventory = deckInventory;
    }

    /**
     * Creates a new Deck into deckInventory
     * @param name name of the deck to be created
     * @return DeckDetails of the deck if the deck successfully created,
     *         null if the an error appear and the deck is not created
     */
    public DeckDetails createDeck(String name){
        return null;
    }

    /**
     * Delete the Deck with given DeckDetails
     * @param deckInfo the DeckDetails of the deck wanted to delete
     * @return true if the deck successfully deleted, false if the an error appear and the deck still remains
     */
    public boolean deleteDeck(DeckDetails deckInfo){
        return true;
    }

    /**
     * Return a DeckBuilder object for modifying the deck
     * @param deckInfo the DeckDetails of the deck wanted to modify with
     * @return a DeckBuilder object of the deck,
     *         null if the deck is no longer in the DeckInventory
     */
    DeckBuilder getBuilder(DeckDetails deckInfo){
        return null;
    }

    /**
     * get the list of the decks for further purpose
     * @return A list of DeckDetails of current stored decks,
     *         null if nothing inside the deckInventory
     */
    public List<DeckDetails> getDecks(){
        return null;
    }

    /**
     * Get the deck in the deck inventory, use after validateDeck()
     * @param deckInfo the DeckDetails of the deck
     * @return the Deck
     */
    private IDeck getDeck(DeckDetails deckInfo){
        try{
            IDeck deck = null;
            for (IDeck toCompare : deckInventory) {
                if (toCompare.getInfo() == deckInfo) {
                    deck = toCompare;
                    break;
                }
            }
            return deck;
        }catch (Exception e){return null;}
    }
}
