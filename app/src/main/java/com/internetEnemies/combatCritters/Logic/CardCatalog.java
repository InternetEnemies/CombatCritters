package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICardFilterBuilder;
import com.internetEnemies.combatCritters.data.ICardSearch;
import com.internetEnemies.combatCritters.data.ICardSearchProvider;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;
import java.util.Map;

/**
 * CardCatalog contains logic layer queries for getting
 */
public class CardCatalog implements ICardCatalog {
    private final ICardSearchProvider searchProvider;
    public CardCatalog(ICardSearchProvider searchProvider) {
        this.searchProvider = searchProvider;
    }
    public CardCatalog(){
        this(Database.getInstance());
    }

    //todo rewrite this interface to be better with the new ICardSearch (this may be a seperate issue
    @Override
    public List<ItemStack<Card>> getOwned() {
        ICardSearch search = searchProvider.getCardSearch();
        ICardFilterBuilder filter = search.getFilterBuilder();
        filter.setOwned();
        return get(search);
    }

    @Override
    public List<ItemStack<Card>> getAll() {
        ICardSearch search = searchProvider.getCardSearch();
        return get(search);
    }

    public List<ItemStack<Card>> get(ICardSearch search) {
        return search.get();
    }
}
