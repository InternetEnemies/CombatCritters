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

public class DeckBuilder implements IDeckBuilder{

    private IDeckInventory deckInventory;

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
            boolean bool;
            if(){

            }else{
                bool = false;
            }
            return bool;
        }catch(Exception x){
            return false;
        }
    }

    @Override
    public boolean removeCard(Card remove, IDeck deck){
        try{

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
     * Check if  a deck is in our deck inventory
     * @param deck the deck wanted to validate with
     * @return true if the deck exist, false if the deck does not exist
     */
    private boolean validateDeck(IDeck deck){
        try{
            boolean bool = false ;
            Iterator<IDeck> listIterator = deckInventory.iterator();
            while (listIterator.hasNext()) {
                IDeck toCompare = listIterator.next();
                if(toCompare.getInfo().getId() == deck.getInfo().getId()){
                    bool = true;
                }
            }
            return bool;
        }catch (Exception e){
            return false;
        }
    }
}
