package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Currency;

public interface ICurrencyInventory {
    /**
     * Get the current balance of a specific currency by its id.
     * @param id the id of the currency
     * @return Currency object with the current balanced stored.
     */
    Currency getCurrentBalance(int id);


}
