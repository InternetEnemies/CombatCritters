package com.internetEnemies.combatCritters.Logic.battles.matchmaking;


import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;

/**
 * IMatchStateObserver.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/30/24
 * 
 * @PURPOSE:    provide interface for observing match state changes
 */
public interface IMatchStateObserver {
    /**
     * a match has been found for the player
     */
    void matchFound(IPlayer opponent);

    /**
     * the match has ended
     */
    void matchEnded(MatchEndType endType, List<ItemStack<?>> rewards);
}
