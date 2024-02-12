package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICardSearch;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.Map;

/**
 * CardCatalog contains logic layer queries for getting
 */
public class CardCatalog implements ICardCatalog {
    private final ICardSearch cardSearch;

    public CardCatalog() {
        this(Database.getInstance().getCardSearch());
    }
    public CardCatalog(ICardSearch cardSearch) {
        this.cardSearch = cardSearch;
    }

    @Override
    public Map<Card,Integer> getOwned() {
        return cardSearch.getOwned();
    }

    @Override
    public Map<Card,Integer> getAll() {
        return cardSearch.getAll();
    }

    //TODO
    //delete this test, only for testing
    public Map<Card,Integer> getOwned(Card.Rarity filter) {
        return cardSearch.getOwned(filter);
    }

    //TODO
    //delete this test, only for testing
    public Map<Card,Integer> getAll(Card.Rarity filter) {
        return cardSearch.getAll(filter);
    }
}
