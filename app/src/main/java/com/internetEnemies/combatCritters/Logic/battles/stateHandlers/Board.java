package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCard;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleRuntimeException;

public class Board implements IBoard {
    private final IEventSystem eventSystem;
    private final BoardRow buffer;
    private final BoardRow enemy;
    private final BoardRow player;
    private final int size;

    public Board(IEventSystem eventSystem, IHealth healthPlayer, IHealth healthEnemy, int size, BattleCard[] initBuffer, BattleCard[] initEnemy, BattleCard[] initPlayer) {
        this.size = size;
        this.eventSystem = eventSystem;
        buffer = new BoardRow(eventSystem, null, size, initBuffer);
        enemy = new BoardRow(eventSystem, healthEnemy, size, initEnemy);
        player = new BoardRow(eventSystem, healthPlayer, size, initPlayer);
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
