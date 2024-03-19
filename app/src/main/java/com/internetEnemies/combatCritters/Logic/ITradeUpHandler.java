/**
 * ITradeUpHandler.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      15-March-2024
 *
 * @PURPOSE:     Interface of handling Trade Up, add/ remove card from Crd Inventoryy
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.TradeUpValidity;

import java.util.List;

public interface ITradeUpHandler {
    /**
     * get user owned cards filter by rarity
     *
     * @param rarity the chosen rarity filter
     * @return a list of owned cards with filtered rarity
     */
    List<ItemStack<Card>> getCards(Card.Rarity rarity);

    /**
     * add a owned card to selected cards
     *
     * @param card owned card
     */
    void addCard(Card card);

    /**
     * remove a selected card from selected cards
     *
     * @param card selected card
     */
    void removeCard(Card card);

    /**
     * get user selected cards
     *
     * @return a list of selected cards
     */
    List<ItemStack<Card>> getSelectedCards();

    /**
     * validate a tradeup and perform it
     *
     * @return true:    the trade up is valid and performed
     *         false:   the trade up is invalid
     */
    TradeUpValidity confirmTradeUp();
}
