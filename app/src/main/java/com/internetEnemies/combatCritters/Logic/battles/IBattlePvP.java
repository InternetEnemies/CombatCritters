package com.internetEnemies.combatCritters.Logic.battles;

import com.internetEnemies.combatCritters.Logic.battles.player.IBattlePlayer;

/**
 * IBattlePvP.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     12/4/24
 *
 * @PURPOSE:    interface for pvp
 */
public interface IBattlePvP {
    IBattlePlayer getPlayer1();
    IBattlePlayer getPlayer2();
}
