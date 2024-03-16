package com.internetEnemies.combatCritters.SystemTests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import static androidx.test.espresso.contrib.RecyclerViewActions.*;


import android.content.Context;
import android.view.View;
import android.widget.GridView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;

import static org.junit.Assert.*;

import static java.util.EnumSet.allOf;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.presentation.BuilderFragment;
import com.internetEnemies.combatCritters.presentation.InventoryFragment;
import com.internetEnemies.combatCritters.presentation.InventoryViewModel;
import com.internetEnemies.combatCritters.presentation.ItemGridFragment;
import com.internetEnemies.combatCritters.presentation.MainMenuActivity;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

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
        ItemGridFragment itemGridFragment;
        
        // Click deck builder button
        onView(withId(R.id.buttonToDeckBuilder)).perform(click());

        mActivityRule.getScenario().onActivity(activity -> {
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            InventoryFragment inventoryFragment = new InventoryFragment();
            BuilderFragment builderFragment = new BuilderFragment();

            // Add both fragments to the activity
            fragmentTransaction.add(inventoryFragment, null);
            fragmentTransaction.add(builderFragment, null);
            //fragmentTransaction.add(itemGridFragment, null);

            // Commit the transaction
            fragmentTransaction.commit();
        });

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
        onView(withId(itemGridFragment.getView().findViewById(R.id.cardGridView))).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Click add to deck 20 times

        // Assert that the not enough cards prompt goes away
    }
}

