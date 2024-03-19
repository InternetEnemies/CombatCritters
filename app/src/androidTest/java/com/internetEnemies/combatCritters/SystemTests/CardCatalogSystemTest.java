package com.internetEnemies.combatCritters.SystemTests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.SystemTests.Assertions.RecyclerCountAssertion;
import com.internetEnemies.combatCritters.presentation.MainMenuActivity;

/**
 * CardCatalogSystemTest.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-18
 *
 * @PURPOSE:    Test the cardCatalog ui
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CardCatalogSystemTest {

    @Rule
    public ActivityScenarioRule<MainMenuActivity> mActivityRule = new ActivityScenarioRule<>(MainMenuActivity.class);

    @Test
    public void testCardCatalog() {
        // Click deck builder button
        onView(withId(R.id.buttonToDeckBuilder)).perform(click());

        // Flip the showall slider
        onView(withId(R.id.showall_toggle)).perform(click());

        // Click filter drop down
        onView(withId(R.id.filterSpinner)).perform(click());

        // Select legendary
        onView(withText("Legendary")).perform(click());

        onView(withId(R.id.inventoryRecyclerView)).check(new RecyclerCountAssertion(3));
        //this test kinda sucks, but I couldnt find a way of identifying the legendary cards (with the test)
    }
}
