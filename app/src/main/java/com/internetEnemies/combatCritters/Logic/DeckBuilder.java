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

import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

public class DeckBuilder implements IDeckBuilder{

    private IDeckInventory deckInventory;

    public DeckBuilder(){
        deckInventory = new DeckInventoryStub();
    }

    @Override
    public boolean createNewDeck(String name) {

        try{
            //search if there is a duplicate deck's name

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

            return true;
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
}
