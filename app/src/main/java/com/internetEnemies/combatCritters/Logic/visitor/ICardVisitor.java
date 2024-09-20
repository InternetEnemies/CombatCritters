package com.internetEnemies.combatCritters.Logic.visitor;

import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;

public interface ICardVisitor {
    void visitItemCard(ItemCard card);
    void visitCritterCard(CritterCard card);
}
