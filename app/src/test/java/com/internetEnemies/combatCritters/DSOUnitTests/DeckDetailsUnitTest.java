package com.internetEnemies.combatCritters.DSOUnitTests;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeckDetailsUnitTest {
    @Test
    public void testConstructorAndGetters() {
        DeckDetails deckDetails = new DeckDetails(1, "Test Deck");
        assertEquals(1, deckDetails.getId());
        assertEquals("Test Deck", deckDetails.getName());
    }

    @Test
    public void testToString() {
        DeckDetails deckDetails = new DeckDetails(2, "Another Deck");
        assertEquals("Another Deck", deckDetails.toString());
    }
}
