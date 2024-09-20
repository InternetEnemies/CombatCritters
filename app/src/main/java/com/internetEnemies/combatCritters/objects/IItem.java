package com.internetEnemies.combatCritters.objects;

import com.internetEnemies.combatCritters.Logic.visitor.IItemVisitor;

/**
 * IVisitableItem.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-01
 *
 * @PURPOSE:    Interface for items. Any items being used in a visitor should implement
 * this interface.
 */

public interface IItem {
    /**
     * Allows an object to be used by a visitor by implementing an accept() method.
     * @param visitor the visitor where the specific visit function for this item is being used.
     */
    void accept(IItemVisitor visitor);

}
