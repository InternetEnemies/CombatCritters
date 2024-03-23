package com.internetEnemies.combatCritters.LogicUnitTests.battles;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.battles.BattleFactory;
import com.internetEnemies.combatCritters.Logic.battles.IBattleFactory;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.registry.BattleRegistry;
import com.internetEnemies.combatCritters.Logic.battles.registry.IBattleRegistry;

import java.util.ArrayList;


public class BattleFactoryTest {
    IBattleFactory factory;
    IBattleRegistry registry;
    @Before
    public void setup(){
        registry = new BattleRegistry();

        factory = new BattleFactory(registry);
    }

    @Test
    public void testGetBattle(){
        assertNotNull(factory.getBattle(mock(IBattleStateObserver.class),0, new ArrayList<>()));
    }
    //todo more tests as functions are added
}
