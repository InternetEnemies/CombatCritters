package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardOrder;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardSearchStub implements ICardSearch{

    private final ICardInventory inventory;
    private final IRegistry<Card> cards;
    private final List<CardOrder> orders;
    private final CardFilterBuilderStub filter;

    public CardSearchStub(ICardInventory inventory, IRegistry<Card> cards){
        this.inventory = inventory;
        this.cards = cards;
        this.orders = new ArrayList<>();
        this.filter = new CardFilterBuilderStub(this.inventory);
    }


    @Override
    public List<ItemStack<Card>> get() {
        List<ItemStack<Card>> result = getAll();
        this.filter.filter(result);
        //TODO ordering (waiting on #106)
        return result;
    } //! note that the way this is done here kinda sucks, SQL will be better

    @Override
    public void addOrder(CardOrder order) {
        this.orders.add(order);
    }

    @Override
    public ICardFilterBuilder getFilterBuilder() {
        return this.filter;
    }

    /**
     * get map of all cards and owned quantities so we can filter and order them
     *
     * @return list of all cards and owned quantities
     */
    private List<ItemStack<Card>> getAll(){
        List<ItemStack<Card>> all = new ArrayList<>();
        for(Card card : cards.getAll()) {
            all.add(new ItemStack<>(card));
        }
        all.addAll(inventory.getCards());
        return all;
    }
}
