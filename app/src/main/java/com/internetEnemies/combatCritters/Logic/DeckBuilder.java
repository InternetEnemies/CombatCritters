/**
 * DeckBuilder.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     01-February-2024
 *
 * @PURPOSE:     perform an implementation of IDeckBuilder
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.util.Map;

class DeckBuilder{

    private IDeckInventory deckInventory;

    protected DeckBuilder(IDeckInventory givenInventory){
        deckInventory = givenInventory;
    }

    protected boolean addCard(Card insert, DeckDetails deckInfo){
        try{
            //check if we have this deck
            if(insert == null){throw new Exception();}
            IDeck currentDeck = getDeck(deckInfo);
            if(currentDeck == null){throw new Exception();}
            int indexToInsert = getNumOfCards(currentDeck);
            currentDeck.addCard(indexToInsert,insert);
            if (currentDeck.getCard(indexToInsert) != insert){throw new Exception();}
            return true;
        }catch(Exception x){return false;}
    }

    protected boolean removeCard(Card remove, DeckDetails deckInfo){
        try{
            if(remove == null){throw new Exception();}
            IDeck currentDeck = getDeck(deckInfo);
            if(currentDeck == null){throw new Exception();}
            int idToRemove = remove.getId();
            int index = getCardIndex(idToRemove,currentDeck);
            if(index == -1){throw new Exception();}
            currentDeck.removeCard(index);
            return true;
        }catch(Exception x){
            return false;
        }
    }

    protected Map<Card, Integer> getCards(DeckDetails deckInfo) {
        IDeck deck = getDeck(deckInfo);
        return deck.countCards();
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
