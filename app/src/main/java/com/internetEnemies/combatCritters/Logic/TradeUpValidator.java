/**
 * TradeUpValidator.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-19
 *
 * @PURPOSE:    implementation of ITradeUpValidator
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.TradeTransaction;

public class TradeUpValidator implements ITradeUpValidator{

    public TradeUpValidator(){

    }
    @Override
    public boolean validate(TradeTransaction tradeUp) {
        return false;
    }
}
