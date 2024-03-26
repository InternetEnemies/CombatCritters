package com.internetEnemies.combatCritters.SystemTests.Tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.SystemTests.Assertions.RecyclerCountAssertion;
import com.internetEnemies.combatCritters.SystemTests.Assertions.RecyclerCountMinimumAssertion;
import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.presentation.MainMenuActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * PackOpeningSystemTest.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-19
 *
 * @PURPOSE:    Test the pack opening
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PackOpeningSystemTest {

    @Rule
    public ActivityScenarioRule<MainMenuActivity> mActivityRule = new ActivityScenarioRule<>(MainMenuActivity.class);

    @Test
    public void testPackOpening() {
        // Add currency to the test because we have 0 on start
        int value = 100;
        Currency startValue = new Currency(value);
        Database.getInstance().getCurrencyInventory().addToBalance(startValue);

        // Click marketplace button
        onView(withId(R.id.buttonToMarketplace)).perform(click());

        // Click the packs tab
        onView(ViewMatchers.withText("Packs")).perform(click());

        // Click the pack you want to purchase
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Click the purchase button
        onView(ViewMatchers.withText("Purchase")).inRoot(RootMatchers.isDialog()).perform(click());

        // Check if the currency is not the same as when we started (i.e. we made a purchase)
        Currency endValue = Database.getInstance().getCurrencyInventory().getCurrentBalance();
        assert(!startValue.equals(endValue));

        // Click the main menu button
        onView(withId(R.id.mainMenuButton)).perform(click());

        // Click the packs button
        onView(withId(R.id.buttonToPacks)).perform(click());

        // Click your pack
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Click the open pack button
        onView(ViewMatchers.withText("Open pack")).inRoot(RootMatchers.isDialog()).perform(click());

        // Click the collect cards button
        onView(withId(R.id.button_backToDeckBuilder)).perform(click());

        // Check if your inventory has at least 1 card now, this means you received yor card from pack opening
        onView(withId(R.id.inventoryRecyclerView)).check(new RecyclerCountMinimumAssertion(1));
    }
}
