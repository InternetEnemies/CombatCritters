package com.combatcritters.critterspring.battle.playerSession;

import com.internetEnemies.combatCritters.Logic.battles.matchmaking.IPlayer;
import com.internetEnemies.combatCritters.objects.User;

/**
 * IPlayerFactory.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/30/24
 * 
 * @PURPOSE:    provide interface for player instantiation
 */
public interface IPlayerFactory {
    IPlayer createPlayer(User user, IPlayerSession session);
}
