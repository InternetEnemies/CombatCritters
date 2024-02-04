package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.DeckInventoryStub;
import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.util.List;

public class DeckHandler implements IDeckHandler{

    private IDeckInventory deckInventory;
    public DeckHandler(){
        deckInventory = new DeckInventoryStub();
    }

    @Override
    public DeckDetails createDeck(String name) {
        if(!(name instanceof String)){return null;}
        try{
            //create the deck in the inventory
            IDeck deck = deckInventory.createDeck(name);
            return deck.getInfo();
        }catch(Exception x){return null;}
    }

    @Override
    public boolean deleteDeck(DeckDetails deckInfo) {
        if(!(deckInfo instanceof DeckDetails)){return false;}
        try{
            IDeck deck = deckInventory.getDeck(deckInfo.getId());
            if(deck.getInfo() != deckInfo){throw new Exception();}
            deckInventory.deleteDeck(deckInfo.getId());
            return true;
        }catch (Exception x){return false;}
    }

    @Override
    public List<DeckDetails> getDecks() {
        return null;
    }

    @Override
    public IDeckInventory getInventory() {
        return deckInventory;
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
