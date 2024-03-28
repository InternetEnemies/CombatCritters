package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleRuntimeException;

/**
 * Board.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-28
 *
 * @PURPOSE:    implementation of IBoard
 */

public class Board implements IBoard {
    private final IEventSystem eventSystem;
    private final IBoardRow buffer;
    private final IBoardRow enemy;
    private final IBoardRow player;
    private final int size;

    public Board(IEventSystem eventSystem, IHealth healthPlayer, IHealth healthEnemy, int size, IBattleCard[] initBuffer, IBattleCard[] initEnemy, IBattleCard[] initPlayer) {
        this(
                eventSystem,
                size,
                new BoardRow(eventSystem, null, size, initBuffer),
                new BoardRow(eventSystem, healthEnemy, size, initEnemy),
                new BoardRow(eventSystem, healthPlayer, size, initPlayer)
        );
    }
    public Board(IEventSystem eventSystem, int size, IBoardRow buffer, IBoardRow enemy, IBoardRow player) {
        this.size = size;
        this.eventSystem = eventSystem;
        this.buffer = buffer;
        this.enemy = enemy;
        this.player = player;
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

    @Override
    public void advanceBuffer() {
        for (int i = 0; i < size; i++) {
            IBattleCard enemyCard = enemy.getCard(i);
            IBattleCard bufferCard = buffer.getCard(i);
            if (enemyCard == null && bufferCard != null) {
                try {
                    buffer.transfer(enemy, i,i);
                } catch (BattleException e) {
                    throw new BattleRuntimeException("failed to advance buffer",e);
                    //this shouldnt happen, I dont even know how to force this to happen for a test lol
                }
            }
        }
    }
}
