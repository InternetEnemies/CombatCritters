package com.internetEnemies.combatCritters.SystemTests.Tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.Assert.assertEquals;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.SystemTests.Assertions.RecyclerCountAssertion;
import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.presentation.MainMenuActivity;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

/**
 * TradeUpSystemTest.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-19
 *
 * @PURPOSE:    Test the trade ups
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TradeUpSystemTest {

    private CritterCard testCard;

    @Rule
    public ActivityScenarioRule<MainMenuActivity> mActivityRule = new ActivityScenarioRule<>(MainMenuActivity.class);

    @Test
    public void testTradeUp() {

        // Create a test card object
        testCard = new CritterCard(24,"Fish Fist Fighter","card_id_25",3, Card.Rarity.UNCOMMON, 3, 4, Arrays.asList(null, null, null));

        // Get an instance of CardInventoryHSQLDB
        ICardInventory cardInventory = Database.getInstance().getCardInventory();

        // Add the test card to the inventory 5 times because thats whats required for trade up
        cardInventory.addCard(testCard);
        cardInventory.addCard(testCard);
        cardInventory.addCard(testCard);
        cardInventory.addCard(testCard);
        cardInventory.addCard(testCard);

        // Click Deck builder button
        onView(withId(R.id.buttonToDeckBuilder)).perform(click());

        // Check that we have cards
        onView(withId(R.id.inventoryRecyclerView)).check(new RecyclerCountAssertion(1));

        // Check that there is 5 of them
        onView(withId(R.id.item_count)).check(matches(withText("5")));

        // Click main menu button
        onView(withId(R.id.button_mainMenu)).perform(click());

        // Click button to trade ups
        onView(withId(R.id.buttonToTradeUp)).perform(click());

        // Click the card 5 times to add it to trade up
        for (int i = 0; i < 5; i++) {
            onView(allOf(withId(R.id.inventoryRecyclerView))).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        }

        // Click trade up button
        onView(withId(R.id.tradeUpButton)).perform(click());

        // Click the close button
        onView(ViewMatchers.withText("Close")).inRoot(RootMatchers.isDialog()).perform(click());

        // Click the main menu button
        onView(withId(R.id.mainMenuButton)).perform(click());

        // Click Deck builder button
        onView(withId(R.id.buttonToDeckBuilder)).perform(click());

        // Check that we have the new card
        onView(withId(R.id.inventoryRecyclerView)).check(new RecyclerCountAssertion(1));

        // Check that there is 1
        onView(withId(R.id.item_count)).check(matches(withText("1")));

        // Delay for users visual input to process otherwise it closes quickly
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void cleanup() {
        // Get an instance of CardInventoryHSQLDB
        ICardInventory cardInventory = Database.getInstance().getCardInventory();
        List<ItemStack<Card>> listToRemove = Database.getInstance().getCardInventory().getCards();

        // Remove the test card from the inventory
        for (ItemStack<Card> card : listToRemove) {
            cardInventory.removeCard(card.getItem(), card.getAmount());
        }

        // Verify that the test card has been removed from the inventory
        assertEquals("Test card removal failed", 0, cardInventory.getCardAmount(testCard));
    }
}
