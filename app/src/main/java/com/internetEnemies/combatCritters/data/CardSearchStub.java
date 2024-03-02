package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardSearchStub implements ICardSearch{

    private final ICardInventory inventory;
    private final IRegistry<Card> cards;
    public CardSearchStub(ICardInventory inventory, IRegistry<Card> cards){
        this.inventory = inventory;
        this.cards = cards;
    }
    @Override
    public List<ItemStack<Card>> getOwned() {
        return inventory.getCards();
    }

    @Override
    public List<ItemStack<Card>> getAll() {
        List<ItemStack<Card>> all = new ArrayList<>();
        for(Card card : cards.getAll()) {
            ItemStack<Card> cardStack = new ItemStack<>(card, inventory.getCardAmount(card));//This is terrible, but it is stub
            all.add(cardStack);
        }
        return all;
    }
}
