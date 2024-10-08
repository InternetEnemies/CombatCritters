/**
 * DeckInventoryStub.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-01
 *
 * @PURPOSE:    implementation of IDeckInventory
 */

package com.internetEnemies.combatCritters.data;


import com.internetEnemies.combatCritters.data.exception.NXDeckException;
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

    @Override
    public DeckDetails getDeckDetails(int id) {
        if (deckDb.containsKey(id)) {
            return deckDb.get(id).getInfo();
        } else {
            throw new NXDeckException("Deck not found");
        }
    }
}
