package com.internetEnemies.combatCritters.LogicUnitTests.battles.stateHandlers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCard;
import com.internetEnemies.combatCritters.Logic.battles.events.EventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Board;

/**
 * BoardTest.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-28
 *
 * @PURPOSE:    test the board interface
 */
public class BoardTest {
    IEventSystem eventSystem;
    @Before
    public void setup(){
        this.eventSystem = new EventSystem();
    }

    @Test
    public void test_advance(){
        Board board = new Board(this.eventSystem,null,null,1,new BattleCard[]{mock(BattleCard.class)},new BattleCard[]{null},new BattleCard[]{null});
        assertNull(board.getEnemy().getCard(0));
        board.advanceBuffer();
        assertNotNull(board.getEnemy().getCard(0));
    }
}
