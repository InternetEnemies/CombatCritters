package com.internetEnemies.combatCritters.LogicUnitTests.battles.cards;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCard;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.events.EventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Board;
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
        Board board = new Board(this.eventSystem,1,new BattleCard[]{null},new BattleCard[]{null},new BattleCard[]{null});
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

    @Test
    public void testBadBoardStateError(){
        //todo
    }

    @Test
    public void testNoBoardError(){
        //todo
    }
}
