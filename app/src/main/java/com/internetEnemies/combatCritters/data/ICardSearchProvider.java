package com.internetEnemies.combatCritters.data;

/**
 * ICardSearchProvider.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-02
 *
 * @PURPOSE:    provide a CardSearch instance
 *
 * @NOTE        This interface isn't necessarily providing a singleton
 */
public interface ICardSearchProvider {
    /**
     * get a new card search object
     * @return new ICardSearch
     */
    ICardSearch getCardSearch();
}
