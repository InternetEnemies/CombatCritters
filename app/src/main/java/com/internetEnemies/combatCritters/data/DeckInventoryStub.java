package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Deck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckInventoryStub implements IDeckInventory{
    Map<Integer,IDeck> deckDb;
    private int deckIdx;
    public DeckInventoryStub(){
        deckDb = new HashMap<>();
        deckIdx = 0;
    }

    @Override
    public List<Deck> getDecks() {
        List<Deck> decks = new ArrayList<>();
        for ( IDeck deck : deckDb.values()) {
            decks.add(deck.getInfo());
        }
        return decks;
    }

    @Override
    public IDeck getIDeck(int deckId) {
        return deckDb.get(deckId);
    }

    @Override
    public IDeck createDeck(String name) {
        int id = deckIdx++;
        IDeck deck = new DeckStub(new Deck(id,name));
        deckDb.put(id,deck);
        return deck;
    }

    @Override
    public void deleteDeck(int deckId) {
        deckDb.remove(deckId);
    }
}
