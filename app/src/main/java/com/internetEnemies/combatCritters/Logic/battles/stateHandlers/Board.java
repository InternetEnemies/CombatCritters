package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCard;

public class Board implements IBoard {
    private final BoardRow buffer;
    private final BoardRow enemy;
    private final BoardRow player;

    public Board(int size, BattleCard[] initBuffer, BattleCard[] initEnemy, BattleCard[] initPlayer) {
        buffer = new BoardRow(size, initBuffer);
        enemy = new BoardRow(size, initEnemy);
        player = new BoardRow(size, initPlayer);
        enemy.setOpposing(player);
        player.setOpposing(enemy);
    }

    @Override
    public IBoardRow getBuffer() {
        return this.buffer;
    }

    @Override
    public IBoardRow getEnemy() {
        return this.enemy;
    }

    @Override
    public IBoardRow getPlayer() {
        return this.player;
    }
}
