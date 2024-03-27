package com.internetEnemies.combatCritters.LogicUnitTests.battles.events;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.events.BoardEvent;
import com.internetEnemies.combatCritters.Logic.battles.events.CardEvent;
import com.internetEnemies.combatCritters.Logic.battles.events.CardHealthEvent;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRow;

/**
 * EventTests.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/26/24
 *
 * @PURPOSE:    Test all the Events
 */
public class EventTests {
    @Test
    public void testBoardEvent() {
        IBoardRow row = mock(IBoardRow.class);
        BoardEvent event = new BoardEvent(1, row);
        assertEquals(1, event.getPos());
        assert row == event.getRow();
    }

    @Test
    public void testCardEvent() {
        IBoardRow row = mock(IBoardRow.class);
        IBattleCard card = mock(IBattleCard.class);
        CardEvent event = new CardEvent(1, row, card);
        assertEquals(card, event.getCard());
    }
    @Test
    public void test_CardHealthEvent() {
        IBoardRow row = mock(IBoardRow.class);
        IBattleCard card = mock(IBattleCard.class);
        CardHealthEvent event = new CardHealthEvent(1, row, card, 2);
        assertEquals(2, event.getHealthChange());

    }
}
