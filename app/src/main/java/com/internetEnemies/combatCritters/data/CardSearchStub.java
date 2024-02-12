package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardOrder;

import java.util.HashMap;
import java.util.Map;

public class CardSearchStub implements ICardSearch{

    private final ICardInventory inventory;
    private final IRegistry<Card> cards;
    public CardSearchStub(ICardInventory inventory, IRegistry<Card> cards){
        this.inventory = inventory;
        this.cards = cards;
    }


    @Override
    public Map<Card, Integer> get() {
        return null;
    }

    @Override
    public void addOrder(CardOrder order) {

    }

    @Override
    public ICardFilterBuilder getFilterBuilder() {
        return null;
    }
}
