package com.internetEnemies.combatCritters.SystemTests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.SystemTests.Assertions.RecyclerCountMinimumAssertion;
import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
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

        // Click deck builder button
        onView(withId(R.id.buttonToDeckBuilder)).perform(click());

        // Check if your inventory has at least 1 card now, this means it was inserted correctly
        onView(withId(R.id.inventoryRecyclerView)).check(new RecyclerCountMinimumAssertion(1));

        // Click the main menu button
        onView(withId(R.id.button_mainMenu)).perform(click());

        // Click trading button
        onView(withId(R.id.buttonToTrading)).perform(click());

        // Click the deal button
//        onView(withId(R.id.tradeConstraintLayout))
//                .perform(RecyclerViewActions
//                        .actionOnItemAtPosition(0, actionOnItem(withId(R.id.dealButton), click())));

//        onView(allOf(withId(R.id.dealButton), isDescendantOfA(withId(R.id.tradeConstraintLayout))))
//                .perform(click());

//        onView(allOf(withId(R.id.tradeConstraintLayout), isDescendantOfA(withId(R.id.recyclerView))))
//                .perform(RecyclerViewActions
//                        .actionOnItemAtPosition(0,
//                                actionOnItem(allOf(withId(R.id.dealButton), isDescendantOfA(withId(R.id.tradeConstraintLayout))),
//                                        click())));
//
//        onView(allOf(withId(R.id.recyclerView), isDisplayed()))
//                .perform(RecyclerViewActions
//                        .actionOnItemAtPosition(0,
//                                actionOnItem(allOf(withId(R.id.dealButton),
//                                                isDescendantOfA(allOf(withId(R.id.tradeConstraintLayout), isDisplayed()))),
//                                        click())));

//
//        onView(allOf(withId(R.id.recyclerView), isDisplayed()))
//                .perform(RecyclerViewActions
//                        .actionOnItemAtPosition(0,
//                                actionOnItem(hasDescendant(withId(R.id.dealButton)), click())));

//        onView(allOf(withId(R.id.recyclerView), isDisplayed()))
//                .perform(RecyclerViewActions
//                        .actionOnItemAtPosition(0,
//                                actionOnItem(allOf(withId(R.id.tradeConstraintLayout), isDisplayed()),
//                                        actionOnItem(withId(R.id.dealButton), click()))));


    }
}


