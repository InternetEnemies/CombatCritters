package com.internetEnemies.combatCritters.SystemTests.Tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.matchesPattern;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.presentation.MainMenuActivity;

/**
 * CurrencySystemTest.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-19
 *
 * @PURPOSE:    Test the Currency System
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CurrencySystemTest {

    @Rule
    public ActivityScenarioRule<MainMenuActivity> mActivityRule = new ActivityScenarioRule<>(MainMenuActivity.class);

    @Test
    public void testCurrency() {
        // Click marketplace button
        onView(withId(R.id.buttonToMarketplace)).perform(click());

        // Check that there is currency
        onView(allOf(withId(R.id.currencyTextView), isDescendantOfA(withId(R.id.balanceContainer))))
                .check(matches(withText(matchesPattern("^[0-9]+$")))); // This ensures that there is a balance (whether its 0 or 100000 etc.)

        try {
            Thread.sleep(500); // 1000 milliseconds = 1 second
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
