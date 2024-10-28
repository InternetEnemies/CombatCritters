package com.combatcritters.critterspring.payloads;

import com.internetEnemies.combatCritters.objects.Currency;

public record WalletPayload(int coins) {
    public static WalletPayload from(Currency currency) {
        return new WalletPayload(currency.getAmount());
    }
}
