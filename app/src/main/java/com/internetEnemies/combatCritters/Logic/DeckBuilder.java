/**
 * DeckBuilder.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     01-February-2024
 *
 * PURPOSE:     perform an implementation of IDeckBuilder
 */

package com.internetEnemies.combatCritters.Logic;



public class DeckBuilder implements IDeckBuilder{

    //private

    @Override
    public boolean createNewDeck(String name, int givenIndex) {

        try{

            return true;
        }catch(Exception x){
            return false;
        }
    }

    public boolean deleteDeck(int deckIndex){
        try{

            return true;
        }catch(Exception x){
            return false;
        }
    }
}
