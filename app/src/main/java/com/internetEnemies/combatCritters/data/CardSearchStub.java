package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardOrder;

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
    public Map<Card, Integer> get() {
        Map<Card,Integer> result = getAll();
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
     * @return map of all cards
     */
    private Map<Card,Integer> getAll(){
        Map<Card,Integer> all = new HashMap<>();
        for(Card card : cards.getAll()) {
            all.put(card, 0);
        }
        all.putAll(inventory.getCards());
        return all;
    }
}
