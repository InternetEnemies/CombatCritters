/**
 * DeckManager.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     06-February-2024
 *
 * @PURPOSE:     Manage the deck Inventory, functions include createDeck and deleteDeck, etc,
 *               provide DeckBuilder class for each deck using getBuilder
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeckManager{

    /**
     * Constructor for DeckManager class
     */
    private IDeckInventory deckInventory;
    public DeckManager(){
        deckInventory = Database.getInstance().getDeckInventory();
    }

    /**
     * Test Constructor for DeckManager class with assigned deckInventory
     */
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
        if (!(name instanceof String)) {return null;}
        try {
            //create the deck in the inventory
            IDeck deck = deckInventory.createDeck(name);
            return deck.getInfo();
        } catch (Exception x) {return null;}
    }

    /**
     * Delete the Deck with given DeckDetails
     * @param deckInfo the DeckDetails of the deck wanted to delete
     * @return true if the deck successfully deleted, false if the an error appear and the deck still remains
     */
    public boolean deleteDeck(DeckDetails deckInfo){
        if (!(deckInfo instanceof DeckDetails)) {return false;}
        try {
            IDeck deck = deckInventory.getDeck(deckInfo.getId());
            if (deck.getInfo() != deckInfo) {throw new Exception();}
            deckInventory.deleteDeck(deckInfo.getId());
            return true;
        } catch (Exception x) {return false;}
    }

    /**
     * Return a DeckBuilder object for modifying the deck
     * @param deckInfo the DeckDetails of the deck wanted to modify with
     * @return a DeckBuilder object of the deck,
     *         null if the deck is no longer in the DeckInventory
     */
    DeckBuilder getBuilder(DeckDetails deckInfo){
        if (!(deckInfo instanceof DeckDetails)) {return null;}
        try{
            IDeck deck;
            deck = getDeck(deckInfo);
            return new DeckBuilder(deck);
        }catch(Exception x){return null;}
    }

    /**
     * get the list of the decks for further purpose
     * @return A list of DeckDetails of current stored decks,
     *         null if nothing inside the deckInventory
     */
    public List<DeckDetails> getDecks(){
        try {
            Iterator<IDeck> listIterator = deckInventory.iterator();
            List<DeckDetails> list = new ArrayList<>();
            while (listIterator.hasNext()) {
                list.add(listIterator.next().getInfo());
            }
            return list;
        } catch (Exception x) {return null;}
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
