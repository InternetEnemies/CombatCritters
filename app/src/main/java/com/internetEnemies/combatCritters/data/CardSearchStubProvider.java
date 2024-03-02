package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;

/**
 * CardSearchStubProvider.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-02
 *
 * @PURPOSE:    this class is used exclusively in testing, provides an instance of cardsearch using
 *              its provided ICardInventory and Card registry
 */
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
