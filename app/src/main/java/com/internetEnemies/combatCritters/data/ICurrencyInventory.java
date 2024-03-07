/**
 * ICurrencyInventory.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-29
 *
 * @PURPOSE:    Inventory of currency of the user
 */

package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Currency;

public interface ICurrencyInventory {
    /**
     * Get the current balance of a specific currency
     * @return Currency object with the current balanced stored.
     */
    Currency getCurrentBalance();

    /**
     * Add to the amount stored in the value
     * @param value the currency object with the value to be added
     *
     */
    void addToBalance(Currency value);

    /**
     * Subtract to the amount stored in the value
     * @param value the currency object with the value to be added
     *
     */
    void removeFromBalance(Currency value);

    /**
     * Sets the user's current currency balance.
     * @param value the currency object with stored value to be changed to
     */

    void setBalance(Currency value);




}
