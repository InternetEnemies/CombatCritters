package com.internetEnemies.combatCritters.LogicUnitTests.battles.events;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.battles.events.integerEvents.IIntegerEventHandler;
import com.internetEnemies.combatCritters.Logic.battles.events.integerEvents.IntegerEventHandler;

import java.util.ArrayList;
import java.util.List;

public class IntegerEventTest {
    IIntegerEventHandler eventHandler;
    @Before
    public void setup() {
        eventHandler = new IntegerEventHandler();
    }

    @Test
    public void testSubscribe() {
        List<Integer> list = new ArrayList<>();
        eventHandler.subscribe(list::add);
        assertEquals(0,list.size());
        eventHandler.fireEvent(1);
        assertEquals(1,list.size());
    }
}
