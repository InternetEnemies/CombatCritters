package com.internetEnemies.combatCritters.Logic.battles.matchmaking;

import com.internetEnemies.combatCritters.objects.User;

/**
 * IMatchmakingService.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/27/24
 *
 * @PURPOSE:    Match players into Matches
 */
public interface IMatchmakingService {
    /**
     * create player instance for a user
     * @param  player player to matchmake
     */
    void matchMake(IPlayer player);

    /**
     * remove a player from matchmaking / match
     * @param user user of player to remove
     */
    void removePlayer(User user);
}
