package com.internetEnemies.combatCritters.SystemTests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.presentation.InventoryFragment;
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
    public void testButtonClick() {
        // Click deck builder button
        onView(withId(R.id.buttonToDeckBuilder)).perform(click());

        // Flip the showall slider
        mActivityRule.getScenario().onActivity(activity -> {
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            InventoryFragment fragment = new InventoryFragment(); // Initialize your InventoryFragment here
            fragmentTransaction.add(fragment, null);
            fragmentTransaction.commit();
        });
        onView(withId(R.id.showall_toggle)).perform(click());

        // Click create new deck button

        // Type in name of deck

        // Click ok button

        // Select waffle warrior

        // Click add to deck 20 times

        // Assert that the not enough cards prompt goes away
    }
}

