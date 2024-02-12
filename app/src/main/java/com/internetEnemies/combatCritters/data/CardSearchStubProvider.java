package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;

public class CardSearchStubProvider implements ICardSearchProvider{
    private ICardInventory inventory;
    private IRegistry<Card> cards;

    public CardSearchStubProvider(ICardInventory inventory, IRegistry<Card> cards) {
        this.inventory = inventory;
        this.cards = cards;
    }
    @Override
    public ICardSearch getCardSearch() {
        return new CardSearchStub(inventory,cards);
    }
}
