package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.util.List;
import java.util.Map;

public class DeckManager implements IDeckManager{

    private DeckHandler deckHandler;
    private DeckBuilder deckBuilder;

    public DeckManager(){
        deckHandler = new DeckHandler();
        deckBuilder = new DeckBuilder(deckHandler.getInventory());
    }

    @Override
    public DeckDetails handlerCreateDeck(String name) {
        return deckHandler.createDeck(name);
    }

    @Override
    public boolean handlerDeleteDeck(DeckDetails deckInfo) {
        return deckHandler.deleteDeck(deckInfo);
    }

    @Override
    public List<DeckDetails> handlerGetDecks() {
        return deckHandler.getDecks();
    }

    @Override
    public boolean builderAddCard(Card insert, DeckDetails deckInfo) {
        return deckBuilder.addCard(insert,deckInfo);
    }

    @Override
    public boolean builderRemoveCard(Card remove, DeckDetails deckInfo) {
        return deckBuilder.removeCard(remove,deckInfo);
    }

    @Override
    public Map<Card, Integer> builderGetCards(DeckDetails deckInfo) {
        return deckBuilder.getCards(deckInfo);
    }
}
