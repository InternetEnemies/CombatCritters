package com.internetEnemies.combatCritters.SystemTests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.SystemTests.Assertions.RecyclerCountAssertion;
import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.presentation.MainMenuActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * MarketplaceSystemTest.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-19
 *
 * @PURPOSE:    Test the marketplace
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MarketplaceSystemTest {

    @Rule
    public ActivityScenarioRule<MainMenuActivity> mActivityRule = new ActivityScenarioRule<>(MainMenuActivity.class);

    @Test
    public void testMarketplace() {
        // Add currency to the test because we have 0 on start
        int value = 100;
        Currency startValue = new Currency(value);
        Database.getInstance().getCurrencyInventory().addToBalance(startValue);

        // Click marketplace button
        onView(withId(R.id.buttonToMarketplace)).perform(click());

        // Click the card you want to purchase
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Click the purchase button
        onView(ViewMatchers.withText("Purchase")).inRoot(RootMatchers.isDialog()).perform(click());

        // Check if the currency is not the same as when we started (i.e. we made a purchase)
        Currency endValue = Database.getInstance().getCurrencyInventory().getCurrentBalance();
        assert(!startValue.equals(endValue));

        // Click the main menu button
        onView(withId(R.id.mainMenuButton)).perform(click());

        // Click the deck builder button
        onView(withId(R.id.buttonToDeckBuilder)).perform(click());

        // Check if we have a card
        onView(withId(R.id.inventoryRecyclerView)).check(new RecyclerCountAssertion(1));
    }
}
