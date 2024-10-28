package com.combatcritters.critterspring.payloads;

import com.internetEnemies.combatCritters.objects.Currency;

/**
 * WalletPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/28/24
 * 
 * @PURPOSE:    payload for a users wallet
 */
public record WalletPayload(int coins) {
    public static WalletPayload from(Currency currency) {
        return new WalletPayload(currency.getAmount());
    }
}
