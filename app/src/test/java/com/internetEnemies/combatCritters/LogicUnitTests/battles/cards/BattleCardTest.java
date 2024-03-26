package com.internetEnemies.combatCritters.LogicUnitTests.battles.cards;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCard;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.events.EventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
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
            1,
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
        assertNotNull(sample.getHealth());
    }

    @Test
    public void testKill() {
        //no error should be thrown
        sample.kill();
    }
}
