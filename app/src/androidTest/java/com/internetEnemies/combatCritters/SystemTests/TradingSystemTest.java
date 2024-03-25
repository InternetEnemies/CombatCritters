package com.internetEnemies.combatCritters.SystemTests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.SystemTests.Assertions.RecyclerCountAssertion;
import com.internetEnemies.combatCritters.SystemTests.Assertions.RecyclerCountMinimumAssertion;
import com.internetEnemies.combatCritters.SystemTests.MyView.MyViewAction;
import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.presentation.MainMenuActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

/**
 * TradingSystemTest.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-19
 *
 * @PURPOSE:    Test the trading
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TradingSystemTest {

    @Rule
    public ActivityScenarioRule<MainMenuActivity> mActivityRule = new ActivityScenarioRule<>(MainMenuActivity.class);

    @Test
    public void testTrading() {
        // Create a test card object
        CritterCard testCard = new CritterCard(45,"Wrath of the Ocean","card_id_59",3, Card.Rarity.LEGENDARY, 5, 5, Arrays.asList(null, null, null));

        // Get an instance of CardInventoryHSQLDB
        ICardInventory cardInventory = Database.getInstance().getCardInventory();

        // Add the test card to the inventory twice because thats whats required for trade
        cardInventory.addCard(testCard);
        cardInventory.addCard(testCard);

        // Add 10 currency as its part of the trade
        int value = 10;
        Currency startValue = new Currency(value);
        Database.getInstance().getCurrencyInventory().addToBalance(startValue);

        // Click deck builder button
        onView(withId(R.id.buttonToDeckBuilder)).perform(click());

        // Checking if cards are there (2 of 1 cards so assert 1)
        onView(withId(R.id.inventoryRecyclerView)).check(new RecyclerCountAssertion(1));

        // Click the main menu button
        onView(withId(R.id.button_mainMenu)).perform(click());

        // THIS CAN BE REMOVED WHEN CURRENCY IN TRADING----------------------------------------------
        // Click marketplace button
        onView(withId(R.id.buttonToMarketplace)).perform(click());

        // Check if we have currency of 10
        onView(allOf(withId(R.id.currencyTextView), isDescendantOfA(withId(R.id.balanceContainer))))
                .check(matches(withText(equalToIgnoringCase("10"))));

        // Click the main menu button
        onView(withId(R.id.mainMenuButton)).perform(click());
        //--------BUT STILL CHECK CURRENCY------------------------------------------------------------------

        // Click trading button
        onView(withId(R.id.buttonToTrading)).perform(click());

        // Click the deal button
        onView(withId(R.id.recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.dealButton)));

        // Click main menu
        onView(withId(R.id.mainMenuButton)).perform(click());

        // THIS CAN BE REMOVED WHEN CURRENCY IN TRADING----------------------------------------------
        // Click marketplace button
        onView(withId(R.id.buttonToMarketplace)).perform(click());

        // Check if we have currency of 10
        onView(allOf(withId(R.id.currencyTextView), isDescendantOfA(withId(R.id.balanceContainer))))
                .check(matches(withText(equalToIgnoringCase("0"))));

        // Click the main menu button
        onView(withId(R.id.mainMenuButton)).perform(click());
        //--------BUT STILL CHECK CURRENCY------------------------------------------------------------------

        // Click deck builder button
        onView(withId(R.id.buttonToDeckBuilder)).perform(click());

        // Check if your inventory has at least 2 cards now because you will have 2 different cards (5 of 1 and 2 of the other) after the trade
        onView(withId(R.id.inventoryRecyclerView)).check(new RecyclerCountMinimumAssertion(2));

        try {
            Thread.sleep(500); // 1000 milliseconds = 1 second
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



