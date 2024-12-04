package com.internetEnemies.combatCritters.Logic.battles;

import com.internetEnemies.combatCritters.Logic.battles.player.IBattlePlayer;

/**
 * BattlePvP.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     12/4/24
 *
 * @PURPOSE:    pvp battle handler
 */
public class BattlePvP implements IBattlePvP {
    private final IBattlePlayer player1;
    private final IBattlePlayer player2;

    public BattlePvP(IBattlePlayer player1, IBattlePlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    } //todo

    @Override
    public IBattlePlayer getPlayer1() {
        return null;
    }

    @Override
    public IBattlePlayer getPlayer2() {
        return null;
    }
}
