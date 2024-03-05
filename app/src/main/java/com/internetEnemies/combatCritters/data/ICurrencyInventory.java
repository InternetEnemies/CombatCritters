package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Currency;

public interface ICurrencyInventory {
    /**
     * Get the current balance of a specific currency by its id.
     * @param id the id of the currency
     * @return Currency object with the current balanced stored.
     */
    Currency getCurrentBalance(int id);

    /**
     * Add to the amount stored in the value at id
     * @param value the currency object with the value to be added
     *
     */
    void addToBalance(Currency value, int id);

    /**
     * Subtract to the amount stored in the value at id
     * @param value the currency object with the value to be added
     *
     */
    void removeFromBalance(Currency value, int id);

    /**
     * Sets the user's current currency balance.
     * @param value the currency object with stored value to be changed to
     * @param id id of the currency being operated on
     */

    void setBalance(Currency value, int id);




}
