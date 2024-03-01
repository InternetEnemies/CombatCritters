package com.internetEnemies.combatCritters.Logic;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;

/**
 * IItemVisitor.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-01
 *
 * @PURPOSE:    Generic visitor class for Item Objects.
 */

public interface IItemVisitor {
    /**
     * Performs the necessary transaction operations for a TransactionItem holding Critter Cards.
     *
     */
    void visitCritterCard(CritterCard card);
    /**
     * Performs the necessary transaction operations for a TransactionItem holding Item Cards.
     *
     */
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
