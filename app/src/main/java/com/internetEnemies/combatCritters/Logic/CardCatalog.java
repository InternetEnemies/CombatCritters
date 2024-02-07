package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICardSearch;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.Map;

/**
 * CardCatalog contains logic layer queries for getting
 */
public class CardCatalog {
    private final ICardSearch cardSearch;

    public CardCatalog() {
        this(Database.getInstance().getCardSearch());
    }
    public CardCatalog(ICardSearch cardSearch) {
        this.cardSearch = cardSearch;
    }

    /**
     * get cards from the players inventory
     * @return map of cards and their quantities
     */
    public Map<Card,Integer> getOwned() {
        return cardSearch.getOwned();
    }

    /**
     * get all cards owned and not owned with counts
     * @return map of cards and their quantities
     */
    public Map<Card,Integer> getAll() {
        return cardSearch.getAll();
    }

    public Map<Card,Integer> getOwned(Card.Rarity filter) {
        return cardSearch.getOwned(filter);
    }

    public Map<Card,Integer> getAll(Card.Rarity filter) {
        return cardSearch.getAll(filter);
    }
}
