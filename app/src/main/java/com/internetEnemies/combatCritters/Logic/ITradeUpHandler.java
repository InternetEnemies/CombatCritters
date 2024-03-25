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
     * when no card is selected, it will return every cards owned (excluding legendary card)
     * When there is a card selected, it will return cards owned with same rarity as the selected card
     * dynamically return cards that subtract selected card
     *
     * @return when selected cards: a list of owned cards with filtered rarity - selected cards
     *         when no cards selected: a list of owned cards that excluding legendary cards
     *         null list when the requirement is met
     */
    List<ItemStack<Card>> getCards();

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
    List<Card> getSelectedCards();

    /**
     * validate a tradeUp and perform it
     * reset the selected card list when the trade is valid
     * throw a IllegalArgumentException if the trade does not valid in transactionHandler
     *
     * @return true:    the trade up is valid and performed
     *         false:   the trade up is invalid
     */
    TradeUpValidity confirmTradeUp();
}
