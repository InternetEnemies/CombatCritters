package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.Map;

/**
 * CardCatalog contains logic layer queries for getting
 */
public class CardCatalog {
    private final ICardInventory inventory;

    public CardCatalog() {
        this(Database.getInstance().getCardInventory());
    }
    public CardCatalog(ICardInventory inventory) {
        this.inventory = inventory;
    }

    public Map<Card,Integer> getCards() {
        return this.inventory.getCards();
    }
}
