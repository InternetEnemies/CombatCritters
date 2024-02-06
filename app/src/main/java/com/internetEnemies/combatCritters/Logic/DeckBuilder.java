/**
 * DeckBuilder.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     01-February-2024
 *
 * @PURPOSE:     As a deck modifying tool, functions includes addCard and removeCard, etc,
 *               need to initialize with a IDeck,
 *               therefore must be created by the DeckManager.getBuilder()
 */

package com.internetEnemies.combatCritters.Logic;


import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.data.IDeck;

import java.util.Map;

public class DeckBuilder{

    private IDeck deck;

    /**
     * Constructor for DeckBuilder class
     * @param deck the selected deck
     */
    public DeckBuilder(IDeck deck){
        this.deck = deck;
    }

    /**
     * Add a card to the selected deck
     * @param insert the card object to insert with
     * @return true if the card successfully added,
     *         false if the an error appear and the adding fails
     */
    public boolean addCard(Card insert){
        try{
            //check if we have this deck
            if(insert == null){throw new Exception();}
            if(deck == null){throw new Exception();}
            int indexToInsert = getNumOfCards();
            deck.addCard(indexToInsert,insert);
            if (deck.getCard(indexToInsert) != insert){throw new Exception();}
            return true;
        }catch(Exception x){return false;}
    }

    /**
     * Remove a card from the selected deck
     * @param remove the card object to remove with
     * @return true if the card successfully removed,
     *         false if the an error appear and the removing fails
     */
    public boolean removeCard(Card remove){
        try{
            if(remove == null){throw new Exception();}
            if(deck == null){throw new Exception();}
            int idToRemove = remove.getId();
            int index = getCardIndex(idToRemove);
            if(index == -1){throw new Exception();}
            deck.removeCard(index);
            return true;
        }catch(Exception x){
            return false;
        }
    }

    /**
     * get all cards from the selected deck
     * @return a map containing cards and its quantity,
     *         null if no deck is selected
     */
    public Map<Card, Integer> getCards() {
        return deck.countCards();
    }

    /**
     * Get the number of cards in the deck,
     * a public getter for private function getNumOfCards()
     * @return the number of cards
     */
    public int getTotalNumOfCards(){
        return getNumOfCards();
    }

    /**
     * Get the number of a card in the deck
     * @param card the card object wanted to get number from
     * @return the number of a card
     */
    public int getNumOfCard(Card card){
        return deck.countCard(card);
    }

    /**
     * Get the number of cards in a deck
     * @return the number of cards
     */
     private int getNumOfCards(){
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
     * @return the index of the card or '-1' stand for not found the card
     */
    private int getCardIndex(int id){
        int totalNum = getNumOfCards();
        if (totalNum == 0){ return -1; } //this should raise exception and abort the calling method
        for(int i=0;i<totalNum;i++) {
            if (deck.getCard(i).getId() == id) { return i; }
        }
        return -1; //this should raise exception and abort the mother method
    }
}
