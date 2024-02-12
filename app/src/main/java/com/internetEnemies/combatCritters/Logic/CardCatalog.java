package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICardSearch;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;
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
    public List<ItemStack<Card>> getOwned() {
        return cardSearch.getOwned();
    }

    @Override
    public List<ItemStack<Card>> getAll() {
        return cardSearch.getAll();
    }
}
