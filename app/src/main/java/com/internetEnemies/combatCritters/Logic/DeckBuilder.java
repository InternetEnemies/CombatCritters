/**
 * DeckBuilder.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     01-February-2024
 *
 * @PURPOSE:     perform an implementation of IDeckBuilder
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.DeckInventoryStub;
import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.data.IDeck;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class DeckBuilder implements IDeckBuilder{

    private final IDeckInventory deckInventory;

    public DeckBuilder(){
        deckInventory = new DeckInventoryStub();
    }

    @Override
    public boolean createNewDeck(String name) {

        try{
            //create the deck in the inventory
            deckInventory.createDeck(name);
            return true;
        }catch(Exception x){
            return false;
        }
    }

    @Override
    public boolean deleteExistingDeck(IDeck deck){
        try{

            return true;
        }catch(Exception x){
            return false;
        }
    }

    @Override
    public boolean addCard(Card insert, IDeck deck){
        try{
            //check if we have this deck
            if(validateDeck(deck)){
                IDeck currentDeck = getDeck(deck.getInfo().getId());
                if(currentDeck == null){
                    throw new Exception();
                }
                int indexToInsert = getNumOfCards(currentDeck);
                currentDeck.addCard(indexToInsert,insert);
                if (currentDeck.getCard(indexToInsert) != insert){
                    throw new Exception();
                }
            }else{
                throw new Exception();
            }
            return true;
        }catch(Exception x){
            return false;
        }
    }

    @Override
    public boolean removeCard(Card remove, IDeck deck){
        try{
            if(validateDeck(deck)){
                IDeck currentDeck = getDeck(deck.getInfo().getId());
                if(currentDeck == null){
                    throw new Exception();
                }
                int idToRemove = remove.getId();
                int index = getCardIndex(idToRemove,currentDeck);
                if(index == -1){
                    throw new Exception();
                }
                currentDeck.removeCard(index);
            }else{
                throw new Exception();
            }
            return true;
        }catch(Exception x){
            return false;
        }
    }

    @Override
    public Iterator<IDeck> iterator(){
        return deckInventory.iterator();
    }

    /**
     * Check if a deck is in our deck inventory
     * @param deck the deck wanted to validate with
     * @return true if the deck exist, false if the deck does not exist
     */
    private boolean validateDeck(IDeck deck){
        try{
            boolean bool = false ;
            for (IDeck toCompare : deckInventory) {
                if (toCompare.getInfo().getId() == deck.getInfo().getId()) {
                    bool = true;
                    break;
                }
            }
            return bool;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Get the deck in the deck inventory, use after validateDeck()
     * @param id the id of the deck
     * @return the Deck
     */
    private IDeck getDeck(int id){
        try{
            IDeck deck = null;
            for (IDeck toCompare : deckInventory) {
                if (toCompare.getInfo().getId() == id) {
                    deck = toCompare;
                    break;
                }
            }
            return deck;
        }catch (Exception e){
            //shouldn't happen
            return null;
        }
    }

    /**
     * Get the number of cards in a deck
     * @param deck the deck
     * @return the number of cards
     */
     private int getNumOfCards(IDeck deck){
        Map<Card,Integer> list = deck.countCards();
        int total = 0;
        for(Map.Entry<Card,Integer> entry: list.entrySet()){
            total += entry.getValue();
        }
        return total;
    }

    /**
     * Get the index of the card in a deck, depends on getNumOfCards()
     * @param id the card wanted to find
     * @param deck the deck
     * @return the index of the card or '-1' stand for not found the card
     */
    private int getCardIndex(int id, IDeck deck){
        int totalNum = getNumOfCards(deck);
        if (totalNum == 0){ return -1; } //this should raise exception and abort the calling method
        for(int i=0;i<totalNum;i++) {
            if (deck.getCard(i).getId() == id) { return i; }
        }
        return -1; //this should raise exception and abort the mother method
    }
}
