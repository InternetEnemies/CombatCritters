package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;

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
    public Map<Card, Integer> getOwned() {
        return inventory.getCards();
    }

    @Override
    public Map<Card, Integer> getAll() {
        Map<Card,Integer> all = new HashMap<>();
        for(Card card : cards.getAll()) {
            all.put(card, 0);
        }
        all.putAll(inventory.getCards());
        return all;
    }

    @Override
    public Map<Card, Integer> getOwned(Card.Rarity filter) {
        return null;
    }

    @Override
    public Map<Card, Integer> getAll(Card.Rarity filter) {
        return null;
    }
}
