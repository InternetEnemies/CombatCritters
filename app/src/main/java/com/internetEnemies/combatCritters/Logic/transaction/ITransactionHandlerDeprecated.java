package com.internetEnemies.combatCritters.Logic.transaction;

import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.TradeTransaction;
import com.internetEnemies.combatCritters.objects.MultiReceiveTransaction;

/**
 * ITransactionHandler.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-06
 *
 * @PURPOSE:    Interface for transaction Handler.
 */

@Deprecated
public interface ITransactionHandlerDeprecated {

    /**
     * Performs the necessary operations for a Market Transaction
     * @param transaction the MarketTransaction being performed.
     * @return true if the transaction is valid, false if it is invalid.
     */
    boolean performTransaction(MarketTransaction transaction);
    /**
     * Performs the necessary operations for a Trade Transaction
     * @param transaction the TradeTransaction being performed.
     * @return true if the transaction is valid, false if it is invalid.
     */
    boolean performTransaction(TradeTransaction transaction);

    /**
     * Verifies if the given transaction can be performed.
     * @param transaction the TradeTransaction being performed.
     * @return true if the transaction is valid, false if it is invalid.
     */

    boolean verifyTransaction(TradeTransaction transaction);
    /**
     * Verifies if the given transaction can be performed.
     * @param transaction the MarketTransaction being performed.
     * @return true if the transaction is valid, false if it is invalid.
     */
    boolean verifyTransaction(MarketTransaction transaction);
    /**
     * Adds the items stored in the transaction in each respective inventory
     * @param transaction the transaction being performed.
     */
    void addItems(MultiReceiveTransaction transaction);

}
