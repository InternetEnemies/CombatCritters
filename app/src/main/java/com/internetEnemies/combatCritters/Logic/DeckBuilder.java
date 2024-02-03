/**
 * DeckBuilder.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     01-February-2024
 *
 * PURPOSE:     perform an implementation of IDeckBuilder
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.DeckInventoryStub;
import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.objects.Card;

public class DeckBuilder implements IDeckBuilder{

    private IDeckInventory deckInventory;

    public DeckBuilder(){
        deckInventory = new DeckInventoryStub();
    }

    @Override
    public boolean createNewDeck(String name) {

        try{

            return true;
        }catch(Exception x){
            return false;
        }
    }

    @Override
    public boolean deleteExistingDeck(int deckIndex){
        try{

            return true;
        }catch(Exception x){
            return false;
        }
    }

    @Override
    public boolean addCard(Card insert, int deckIndex){
        try{

            return true;
        }catch(Exception x){
            return false;
        }
    }

    @Override
    public boolean removeCard(Card remove, int deckIndex){
        try{

            return true;
        }catch(Exception x){
            return false;
        }
    }
}
