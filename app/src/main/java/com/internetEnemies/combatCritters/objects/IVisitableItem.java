package com.internetEnemies.combatCritters.objects;

import com.internetEnemies.combatCritters.Logic.IItemVisitor;

public interface IVisitableItem {
    /**
     * Allows an object to be used by a visitor by implementing an accept() method.
     * @param visitor the visitor where the specific visit function for this item is being used.
     */
    void accept(IItemVisitor visitor);

}
