/**
 * TradeUpValidity.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-19
 *
 * @PURPOSE:    properties of TradeUp validity
 */
package com.internetEnemies.combatCritters.objects;

public class TradeUpValidity {
    private final boolean isValid;
    private final int difference;

    public TradeUpValidity(boolean isValid, int difference){
        this.isValid = isValid;
        this.difference = difference;
    }

    public boolean isValid() {
        return isValid;
    }

    /**
     * The require number of cards - the actual numbers of cards
     * @return 0 = valid
     *         negative = too much cards
     *         positive = not enough cards
     */
    public int getDifference(){
        return difference;
    }
}
