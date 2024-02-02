package com.internetEnemies.combatCritters.data;

import androidx.annotation.NonNull;

import com.internetEnemies.combatCritters.objects.Deck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    public IDeck getDeck(int deckId) {
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

    @NonNull
    @Override
    public Iterator<IDeck> iterator() {
        return new ArrayList<>(deckDb.values()).iterator();
    }
}
