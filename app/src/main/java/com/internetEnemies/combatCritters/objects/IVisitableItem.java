package com.internetEnemies.combatCritters.objects;

import com.internetEnemies.combatCritters.Logic.IItemVisitor;

public interface IVisitableItem {
    void accept(IItemVisitor visitor);

}
