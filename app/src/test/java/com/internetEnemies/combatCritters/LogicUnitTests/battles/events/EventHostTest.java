package com.internetEnemies.combatCritters.LogicUnitTests.battles.events;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.internetEnemies.combatCritters.Logic.battles.events.EventHost;

import java.util.ArrayList;
import java.util.List;

/**
 * EventHostTest.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/26/24
 *
 * @PURPOSE:    test the EventHost class
 */
public class EventHostTest {
    EventHost<Integer> eventHost;
    @Before
    public void setup() {
        eventHost = new EventHost<>();
    }

    @Test
    public void testSubscribe() {
        List<Integer> list = new ArrayList<>();
        eventHost.subscribe(list::add);
        assertEquals(0, list.size());
        eventHost.fireEvent(1);
        assertEquals(1, list.size());
    }
}
