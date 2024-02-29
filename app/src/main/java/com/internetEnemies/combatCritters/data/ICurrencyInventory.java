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
    void addtoBalance(Currency value, int id);

    /**
     * Subtract to the amount stored in the value at id
     * @param value the currency object with the value to be added
     *
     */
    void removeFromBalance(Currency value, int id);




}
