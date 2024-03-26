package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCard;
import com.internetEnemies.combatCritters.Logic.battles.events.EventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;

public class Board implements IBoard {
    private final IEventSystem eventSystem;
    private final BoardRow buffer;
    private final BoardRow enemy;
    private final BoardRow player;

    public Board(IEventSystem eventSystem, int size, BattleCard[] initBuffer, BattleCard[] initEnemy, BattleCard[] initPlayer) {
        this.eventSystem = eventSystem;
        buffer = new BoardRow(eventSystem, size, initBuffer);
        enemy = new BoardRow(eventSystem, size, initEnemy);
        player = new BoardRow(eventSystem, size, initPlayer);
        enemy.setOpposing(player);
        player.setOpposing(enemy);
    }

    public Board(int size, BattleCard[] initBuffer, BattleCard[] initEnemy, BattleCard[] initPlayer) {
        this(EventSystem.getInstance(), size, initBuffer, initEnemy, initPlayer);
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
