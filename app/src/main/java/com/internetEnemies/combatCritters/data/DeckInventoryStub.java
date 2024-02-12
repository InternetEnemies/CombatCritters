package com.internetEnemies.combatCritters.data;

import androidx.annotation.NonNull;

import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Deck inventory stub database for testing
 */
public class DeckInventoryStub implements IDeckInventory{
    Map<Integer,IDeck> deckDb;
    private int deckIdx;
    public DeckInventoryStub(){
        deckDb = new HashMap<>();
        deckIdx = 0;
    }


    @Override
    public IDeck getDeck(DeckDetails deckDetails) {
        return deckDb.get(deckDetails.getId());
    }

    @Override
    public IDeck createDeck(String name) {
        int id = deckIdx++;
        IDeck deck = new DeckStub(new DeckDetails(id,name));
        deckDb.put(id,deck);
        return deck;
    }

    @Override
    public void deleteDeck(DeckDetails deckDetails) {
        deckDb.remove(deckDetails.getId());
    }

    @Override
    public List<DeckDetails> getDeckDetails() {
        List<DeckDetails> decks = new ArrayList<>();
        for(IDeck deck : deckDb.values()) {
            decks.add(deck.getInfo());
        }
        return decks;
    }
}
