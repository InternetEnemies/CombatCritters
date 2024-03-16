package com.internetEnemies.combatCritters.SystemTests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.presentation.MainMenuActivity;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 * Instrumented test, which will execute on an Android device.
 *
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
            Thread.sleep(2000); // 1000 milliseconds = 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Type in name of deck
        onView(ViewMatchers.withId(R.id.deckIdForSys))
                .inRoot(RootMatchers.isDialog())
                .perform(ViewActions.typeText("Deck1"), ViewActions.closeSoftKeyboard());

        // Click OK button to confirm
        onView(ViewMatchers.withText("OK"))
                .inRoot(RootMatchers.isDialog())
                .perform(ViewActions.click());

        // Select waffle warrior
        onView(withId(R.id.cardGridView)).perform(click());

        // Click add to deck 20 times

        // Assert that the not enough cards prompt goes away
    }
}

