package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;

public interface IItemVisitor {
    /**
     * Performs the necessary transaction operations for a TransactionItem holding a card(s).
     *
     */
    void visitCritterCard(CritterCard card);
    void visitItemCard(ItemCard card);
    /**
     * Performs the necessary transaction operations for a TransactionItem holding a Pack(s).
     *
     */
    void visitPack(Pack pack);
    /**
     * Performs the necessary transaction operations for a TransactionItem holding Currency.
     *
     */
    void visitCurrency(Currency currency);
}
