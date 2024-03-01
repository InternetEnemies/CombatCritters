package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.TransactionCard;
import com.internetEnemies.combatCritters.objects.TransactionCurrency;
import com.internetEnemies.combatCritters.objects.TransactionPack;

public interface ITransactionVisitor {
    void visitCard(TransactionCard card);

    void visitPack(TransactionPack pack);

    void visitCurrency(TransactionCurrency currency);
}
