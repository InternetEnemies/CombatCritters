/**
 * ITradeUpValidator.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      15-March-2024
 *
 * @PURPOSE:     Interface of validate trade up transaction
 */
package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.TradeUpValidity;

import java.util.List;

public interface ITradeUpValidator {
    /**
     * validate the TradeUp
     *
     * @param givenListStack the given List stack passed in by TradeUpHandler
     * @return true:    if the TradeUp is valid
     *         false:   if the TradeUp is invalid
     */
    TradeUpValidity validate(List<ItemStack<Card>> givenListStack);
}
