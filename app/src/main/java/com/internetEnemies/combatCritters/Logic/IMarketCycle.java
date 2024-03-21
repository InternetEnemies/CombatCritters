package com.internetEnemies.combatCritters.Logic;

import java.time.LocalDateTime;
import java.util.Map;

public interface IMarketCycle {
    /**
     * IMarketCycle.java
     * COMP 3350 A02
     * @Project     Combat Critters
     * @created     2024-03-19
     *
     * @PURPOSE:    Interface for MarketCycle, which handles the cycling of discounts in the marketplace.
     */

    /**
     * Applies the discounts to items with the given id, and value stored in the given Map.
     * @param discounts the map that stores the id of a MarketTransaction and the discount to be app;ied.
     */
    void applyDiscounts(Map<Integer, Double> discounts);

    void setCurrentTime();
    void setCurrentTime(LocalDateTime time);

    /**
     * Helper function to generate random discounts to be applied in the next cycle.
     * @param numDiscounts the number of discounts to be changed
     * @return he map that stores the id of a MarketTransaction and the discount to be app;ied.
     */
    Map<Integer, Double> generateDiscounts(int numDiscounts);


}
