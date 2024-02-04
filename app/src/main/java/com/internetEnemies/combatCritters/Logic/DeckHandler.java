package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.DeckInventoryStub;
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
        return null;
    }

    @Override
    public boolean deleteDeck(DeckDetails deckInfo) {
        return false;
    }

    @Override
    public List<DeckDetails> getDecks() {
        return null;
    }

    @Override
    public IDeckInventory getInventory() {
        return deckInventory;
    }
}
