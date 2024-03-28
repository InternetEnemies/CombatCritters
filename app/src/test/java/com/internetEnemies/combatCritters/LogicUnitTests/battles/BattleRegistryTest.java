package com.internetEnemies.combatCritters.LogicUnitTests.battles;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.ITransactionHandler;
import com.internetEnemies.combatCritters.Logic.TransactionHandler;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.events.IVoidEventListener;
import com.internetEnemies.combatCritters.Logic.battles.registry.BattleRegistry;
import com.internetEnemies.combatCritters.Logic.battles.registry.IBattleRegistry;
import com.internetEnemies.combatCritters.data.Registry;
import com.internetEnemies.combatCritters.objects.battles.Opponent;

import java.util.ArrayList;

public class BattleRegistryTest {

    private IBattleRegistry registry;
    Registry<Opponent> opponentIRegistry;
    ITransactionHandler transactionHandler;
    @Before
    public void setup() {
        transactionHandler = mock(TransactionHandler.class);
        opponentIRegistry = mock(Registry.class);
        registry = new BattleRegistry(opponentIRegistry,transactionHandler);
    }
    @Test
    public void testGetBattle(){
        assertNotNull(registry.getBattle(mock(IBattleStateObserver.class),1, new ArrayList<>(),mock(IVoidEventListener.class),mock(IVoidEventListener.class)));
    }

    @Test
    public void test_getBattles(){
        when(opponentIRegistry.getAll()).thenReturn(new ArrayList<>());
        assertNotNull(registry.getBattles());
        verify(opponentIRegistry).getAll();
    }
}

