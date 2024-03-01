package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.TransactionCard;
import com.internetEnemies.combatCritters.objects.TransactionCurrency;
import com.internetEnemies.combatCritters.objects.TransactionPack;

public interface ITransactionVisitor {
    /**
     * Performs the necessary transaction operations for a TransactionItem holding a card(s).
     *
     */
    void visitCard(TransactionCard card);
    /**
     * Performs the necessary transaction operations for a TransactionItem holding a Pack(s).
     *
     */
    void visitPack(TransactionPack pack);
    /**
     * Performs the necessary transaction operations for a TransactionItem holding Currency.
     *
     */
    void visitCurrency(TransactionCurrency currency);
}
