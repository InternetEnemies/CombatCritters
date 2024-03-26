package com.internetEnemies.combatCritters.SystemTests.Tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.SystemTests.Assertions.RecyclerCountAssertion;
import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.presentation.MainMenuActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TradeUpSystemTest {

    @Rule
    public ActivityScenarioRule<MainMenuActivity> mActivityRule = new ActivityScenarioRule<>(MainMenuActivity.class);

    @Test
    public void testTradeUp() {

        // Create a test card object
        CritterCard testCard = new CritterCard(24,"Fish Fist Fighter","card_id_25",3, Card.Rarity.UNCOMMON, 3, 4, Arrays.asList(null, null, null));

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

        // Click main menu button
        onView(withId(R.id.button_mainMenu)).perform(click());

        // Click trade up button
        onView(withId(R.id.buttonToTradeUp)).perform(click());

        try {
            Thread.sleep(5000); // 1000 milliseconds = 1 second
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
