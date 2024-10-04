package com.internetEnemies.combatCritters.Logic.inventory.cards;

import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.List;

public class CardRegistry implements ICardRegistry{
    private final IRegistry<Card> registry;
    public CardRegistry (IRegistry<Card> cardRegistry) {
        this.registry = cardRegistry;
    }

    @Override
    public Card getCard(int id) {
        return registry.getSingle(id);
    }

    @Override
    public List<Card> getCards(List<Integer> ids) {
        return registry.getListOf(ids);
    }

    @Override
    public List<Card> getAllCards() {
        return registry.getAll();
    }
}
