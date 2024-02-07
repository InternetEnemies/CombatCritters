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
        Map<Card,Integer> filtered = new HashMap<>();
        Map<Card,Integer> unfiltered = inventory.getCards();
        for(Card card : unfiltered.keySet()){
            if(card.getRarity() == filter){
                filtered.put(card,1);
            }
        }
        return filtered;
    }

    @Override
    public Map<Card, Integer> getAll(Card.Rarity filter) {
        Map<Card,Integer> filteredAll = new HashMap<>();
        for(Card card : cards.getAll()) {
            if(card.getRarity() == filter){
                filteredAll.put(card,0);
            }
        }
        filteredAll.putAll(getOwned(filter));
        return filteredAll;
    }
}
