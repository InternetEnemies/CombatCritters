package com.internetEnemies.combatCritters.LogicUnitTests.battles;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.registry.BattleRegistry;
import com.internetEnemies.combatCritters.Logic.battles.registry.IBattleRegistry;

import java.util.ArrayList;

public class BattleRegistryTest {

    private IBattleRegistry registry;
    @Before
    public void setup() {
        registry = new BattleRegistry();
    }
    @Test
    public void testGetBattle(){
        assertNotNull(registry.getBattle(mock(IBattleStateObserver.class),1, new ArrayList<>()));
    }
    //todo more tests here with more funtionality
}

