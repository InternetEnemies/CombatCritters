package com.internetEnemies.combatCritters.LogicUnitTests.battles.stateHandlers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCard;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.events.EventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.BoardRow;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Health;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRow;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IHealth;
import com.internetEnemies.combatCritters.LogicUnitTests.battles.EventFlag;

public class BoardRowTest {
    private static final int ROW_SIZE = 5;
    IBoardRow row;
    IEventSystem eventSystem;
    IBattleCard sampleCard;
    @Before
    public void setup() {
        sampleCard = mock(BattleCard.class);
        eventSystem = new EventSystem();
        row = new BoardRow(
                eventSystem,
                null,
                ROW_SIZE,
                new BattleCard[]{
                        null,
                        null,
                        null,
                        null,
                        null
        });
    }

    @Test
    public void testPlayGetCard() throws BattleException {
        assertNull(row.getCard(0));
        row.playCard(0, sampleCard);
        assertNotNull(row.getCard(0));
    }

    @Test(expected = BattleException.class)
    public void testFailPlayCard() throws BattleException{
        row.playCard(0,sampleCard);
        row.playCard(0,sampleCard);
    }

    @Test
    public void testPlayCardEvent() throws BattleException {
        EventFlag flag = new EventFlag();
        eventSystem.getOnPlayCard().subscribe(event -> {
            assertEquals(sampleCard, event.getCard());
            assertEquals(1, event.getPos());
            assertEquals(row, event.getRow());
            flag.fire();
        });
        row.playCard(1,sampleCard);
        assertTrue(flag.hasFired());
    }

    @Test
    public void testKillCard() throws BattleException {
        row.playCard(0,sampleCard);
        assertNotNull(row.getCard(0));
        row.killCard(0);
        assertNull(row.getCard(0));
    }

    @Test(expected = BattleException.class)
    public void testKillCardFailure() throws BattleException {
        row.killCard(0);
    }

    @Test
    public void testKillCardEvent() throws BattleException{
        EventFlag flag = new EventFlag();
        eventSystem.getOnCardKilled().subscribe(event -> {
            assertEquals(sampleCard, event.getCard());
            assertEquals(1, event.getPos());
            assertEquals(row, event.getRow());
            flag.fire();
        });
        row.playCard(1,sampleCard);
        row.killCard(1);
        assertTrue(flag.hasFired());
    }

    @Test
    public void runAttackPhase() {
        BattleCard card1 = mock(BattleCard.class);
        BattleCard card2 = mock(BattleCard.class);
        BoardRow row = new BoardRow(this.eventSystem,null, 3, new BattleCard[]{card1, null, card2});
        row.runAttackPhase();
        verify(card1).attack();
        verify(card2).attack();
    }

    @Test
    public void test_attackCard() {
        BattleCard card1 = mock(BattleCard.class);
        BoardRow row = new BoardRow(this.eventSystem,null, 3, new BattleCard[]{card1, null, null});
        row.attack(0,5);
        verify(card1).damage(5);
    }

    @Test
    public void test_attackNoCard() {
        IHealth health = mock(Health.class);
        BoardRow row = new BoardRow(this.eventSystem,health, 3, new BattleCard[]{null, null, null});
        row.attack(0,5);
        verify(health).damage(5);
    }

    @Test
    public void test_attackNoCardNoHealth() {
        BoardRow row = new BoardRow(this.eventSystem,null, 3, new BattleCard[]{null, null, null});
        row.attack(0,5);
        //this should run without error
    }
}
