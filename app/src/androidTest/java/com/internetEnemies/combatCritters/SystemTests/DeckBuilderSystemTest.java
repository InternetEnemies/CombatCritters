package com.internetEnemies.combatCritters.SystemTests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;


import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;

import com.internetEnemies.combatCritters.Logic.DeckValidator;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.presentation.MainMenuActivity;

/**
 * DeckBuilderSystemTest.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-18
 *
 * @PURPOSE:    Test the Deck Builder UI
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class DeckBuilderSystemTest {

    @Rule
    public ActivityScenarioRule<MainMenuActivity> mActivityRule = new ActivityScenarioRule<>(MainMenuActivity.class);

    @Test
    public void testDeckBuilder() {
        // Click deck builder button
        onView(withId(R.id.buttonToDeckBuilder)).perform(click());

        // Flip the showall slider
        onView(withId(R.id.showall_toggle)).perform(click());

        // Click create deck button
        onView(withId(R.id.startDeckCreationButton)).perform(click());

        // Add a delay to allow the dialog to appear
        try {
            Thread.sleep(1000); // 1000 milliseconds = 1 second
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Type in name of deck
        onView(ViewMatchers.withId(R.id.deckIdForSys))
                .inRoot(RootMatchers.isDialog())
                .perform(ViewActions.typeText("Deck1"), ViewActions.closeSoftKeyboard());

        // Click OK button to confirm
        onView(ViewMatchers.withText("OK")).inRoot(RootMatchers.isDialog()).perform(ViewActions.click());

        // Select waffle warrior
        onView(allOf(withId(R.id.inventoryRecyclerView))).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        // assert the not enough cards warning is there
        onView(allOf(withId(R.id.deck_issue),withText(DeckValidator.STR_MIN_CARDS))).check(matches(isDisplayed()));
        // Click add to deck 20 times
        for (int i = 0; i < DeckValidator.MIN_CARDS; i++) {
            onView(withId(R.id.addToDeckButton)).perform(click());
        }

        // Assert that the not enough cards prompt goes away
        // ? Note the not enough owned warning will remain
        onView(allOf(withId(R.id.deck_issue),withText(DeckValidator.STR_MIN_CARDS))).check(doesNotExist());

        try {
            Thread.sleep(500); // 1000 milliseconds = 1 second
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

