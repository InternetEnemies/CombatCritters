package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICardSearch;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardFilter;
import com.internetEnemies.combatCritters.objects.CardOrder;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;

/**
 * CardCatalog contains logic layer queries for getting
 */
public class CardCatalog implements ICardCatalog {
    private final ICardSearch cardSearch;
    public CardCatalog(ICardSearch cardSearch) {
        this.cardSearch = cardSearch;
    }
    public CardCatalog(){
        this(Database.getInstance().getCardSearch());
    }

    @Override
    public List<ItemStack<Card>> get(CardFilter filter, List<CardOrder> orders) {
        return this.cardSearch.get(orders, filter);
    }
}
