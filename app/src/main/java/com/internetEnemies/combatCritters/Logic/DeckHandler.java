package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.DeckInventoryStub;
import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
class DeckHandler implements IDeckHandler {

    private IDeckInventory deckInventory;

    public DeckHandler() {
        deckInventory = new DeckInventoryStub();
    }

    @Override
    public DeckDetails createDeck(String name) {
        if (!(name instanceof String)) {return null;}
        try {
            //create the deck in the inventory
            IDeck deck = deckInventory.createDeck(name);
            return deck.getInfo();
        } catch (Exception x) {return null;}
    }

    @Override
    public boolean deleteDeck(DeckDetails deckInfo) {
        if (!(deckInfo instanceof DeckDetails)) {
            return false;
        }
        try {
            IDeck deck = deckInventory.getDeck(deckInfo.getId());
            if (deck.getInfo() != deckInfo) {throw new Exception();}
            deckInventory.deleteDeck(deckInfo.getId());
            return true;
        } catch (Exception x) {
            return false;
        }
    }

    @Override
    public List<DeckDetails> getDecks() {
        try {
            Iterator<IDeck> listIterator = deckInventory.iterator();
            List<DeckDetails> list = new ArrayList<>();
            while (listIterator.hasNext()) {
                list.add(listIterator.next().getInfo());
            }
            return list;
        } catch (Exception x) {return null;}
    }

    protected IDeckInventory getInventory() {
        return deckInventory;
    }
}
