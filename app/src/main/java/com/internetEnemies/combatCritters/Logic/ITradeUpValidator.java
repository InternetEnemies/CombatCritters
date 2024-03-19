/**
 * ITradeUpValidator.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      15-March-2024
 *
 * @PURPOSE:     Interface of validate trade up transaction
 */
package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.TradeTransaction;
import com.internetEnemies.combatCritters.objects.TradeUpValidity;

public interface ITradeUpValidator {
    /**
     * validate the TradeUp
     *
     * @param tradeUp the transaction passed in by TradeUpHandler
     * @return true:    if the TradeUp is valid
     *         false:   if the TradeUp is invalid
     */
    TradeUpValidity validate(TradeTransaction tradeUp);
}
