package com.internetEnemies.combatCritters.LogicUnitTests.battles.cards;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCard;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.events.EventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleRuntimeException;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Board;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.BoardRow;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Health;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRow;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IHealth;
import com.internetEnemies.combatCritters.LogicUnitTests.battles.EventFlag;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.battles.CardState;

import java.util.ArrayList;

public class BattleCardTest {
    IEventSystem eventSystem;
    IBattleCard sample;
    CritterCard sampleCritter = new CritterCard(
            1,
            "test",
            "",
            1,
            Card.Rarity.COMMON,
            1,
            10,
            new ArrayList<>()
    );
    @Before
    public void setup() {
        this.eventSystem = new EventSystem();
        this.sample = new BattleCard(
                eventSystem,
                sampleCritter
        );
    }

    @Test
    public void testGetCardState() {
        CardState state = sample.getCardState();
        assertNotNull(state);
        assertEquals(sampleCritter, state.getCard());
    }

    @Test
    public void testGetHealth() {
        assertEquals(sampleCritter.getHealth(), sample.getHealth());
    }

    @Test
    public void testKill() {
        //no error should be thrown
        sample.kill();
    }

    @Test
    public void testDamageHeal() throws BattleException {
        Board board = new Board(this.eventSystem,null, null, 1,new BattleCard[]{null},new BattleCard[]{null},new BattleCard[]{null});
        board.getPlayer().playCard(0,sample);
        EventFlag damageFlag = new EventFlag();
        EventFlag healFlag = new EventFlag();
        EventFlag deathFlag = new EventFlag();
        eventSystem.getOnCardDamaged().subscribe(e->damageFlag.fire());
        eventSystem.getOnCardHealed().subscribe(e->healFlag.fire());
        eventSystem.getOnCardKilled().subscribe(e->deathFlag.fire());

        sample.damage(5);
        sample.heal(5);
        sample.damage(sampleCritter.getHealth() + 5);
        assertTrue(damageFlag.hasFired());
        assertTrue(healFlag.hasFired());
        assertTrue(deathFlag.hasFired());
    }

    @Test(expected = BattleRuntimeException.class)
    public void testBadBoardStateError() throws BattleException {
        Board board = new Board(this.eventSystem,null, null,2,new BattleCard[]{null,null},new BattleCard[]{null,null},new BattleCard[]{null,null});
        board.getPlayer().playCard(0,sample);
        sample.moveTo(1, board.getPlayer(), null);//moving the card but not updating its actual position
        //? this feels a bit odd honestly but im not sure how to do it better (with regards to the way cards are storing the reference to their position)
        sample.damage(100);
    }

    @Test(expected = BattleRuntimeException.class)
    public void testNoBoardError(){
        IHealth health = new Health(1,1);
        new BattleCard(this.eventSystem, sampleCritter, health);
        health.damage(1);
        //? we have to do it this way since the damage methods in the card assert that the parent isnt null
    }

    @Test
    public void testAttack() {
        BattleCard card = new BattleCard(this.eventSystem, sampleCritter);
        IBoardRow board = mock(BoardRow.class);
        card.moveTo(1,null, board);
        card.attack();
        verify(board).attack(1,sampleCritter.getDamage());
    }
}
