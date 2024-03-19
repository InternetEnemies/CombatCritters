/**
 * TradeUpHandler.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-19
 *
 * @PURPOSE:    implementation of ITradeUpValidator
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;

public class TradeUpHandler implements ITradeUpHandler{
    @Override
    public List<ItemStack<Card>> getCards(Card.Rarity rarity) {
        return null;
    }

    @Override
    public void addCard(Card card) {

    }

    @Override
    public void removeCard(Card card) {

    }

    @Override
    public List<ItemStack<Card>> getSelectedCards() {
        return null;
    }

    @Override
    public boolean confirmTradeUp() {
        return false;
    }
}
