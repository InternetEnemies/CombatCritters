package com.internetEnemies.combatCritters.SystemTests.Tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.internetEnemies.combatCritters.Logic.DeckValidator;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.SystemTests.Assertions.RecyclerCountAssertion;
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
 * BattleSystemTest.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-28
 *
 * @PURPOSE:    Test the Battles
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class BattleSystemTest {

    private CritterCard testCard;

    @Rule
    public ActivityScenarioRule<MainMenuActivity> mActivityRule = new ActivityScenarioRule<>(MainMenuActivity.class);

    @Test
    public void testBattle() { //1,'Waffle Warrior','card_id_1',3,0,'critter',1,3,NULL
        // Create a test card object
        testCard = new CritterCard(1, "Waffle Warrior", "card_id_1", 3, Card.Rarity.COMMON, 1, 3, Arrays.asList(null, null, null));

        // Get an instance of CardInventoryHSQLDB
        ICardInventory cardInventory = Database.getInstance().getCardInventory();

        // Add the test card to the inventory 20 times because thats whats required for trade
        for (int i = 0; i < 20; i++) {
            cardInventory.addCard(testCard);
        }

        // Click deck builder button
        onView(withId(R.id.buttonToDeckBuilder)).perform(click());

        // Check if we have cards
        onView(withId(R.id.inventoryRecyclerView)).check(new RecyclerCountAssertion(1));

        // Click create deck button
        onView(withId(R.id.startDeckCreationButton)).perform(click());

        // Add a delay to allow the dialog to appear
        try {
            Thread.sleep(1000); // 1000 milliseconds = 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Type in name of deck
        onView(ViewMatchers.withId(R.id.deckIdForSys))
                .inRoot(RootMatchers.isDialog())
                .perform(ViewActions.typeText("Deck1"), ViewActions.closeSoftKeyboard());

        // Click OK button to confirm
        onView(ViewMatchers.withText("OK")).inRoot(RootMatchers.isDialog()).perform(ViewActions.click());

        // Select waffle warrior
        onView(allOf(withId(R.id.inventoryRecyclerView))).perform(actionOnItemAtPosition(0, click()));

        // assert the not enough cards warning is there
        onView(allOf(withId(R.id.deck_issue), withText(DeckValidator.STR_MIN_CARDS))).check(matches(isDisplayed()));

        // Click add to deck 20 times
        for (int i = 0; i < DeckValidator.MIN_CARDS; i++) {
            onView(withId(R.id.addToDeckButton)).perform(click());
        }

        // Assert that the not enough cards prompt goes away
        onView(allOf(withId(R.id.deck_issue), withText(DeckValidator.STR_MIN_CARDS))).check(doesNotExist());

        // Click main menu button
        onView(withId(R.id.button_mainMenu)).perform(click());

        // Click battle button
        onView(withId(R.id.buttonToBattleStartup)).perform(click());

        // Select the opponent
        onView(allOf(withId(R.id.opponentsRecycler))).perform(actionOnItemAtPosition(0, click()));

        // Click enter combat button
        onView(withId(R.id.enterCombatButton)).perform(click());

        // Just spam this to lose the battle
        for (int x = 0; x < 25; x++) {
            onView(withId(R.id.button_endTurn)).perform(click());
        }

        // Go back to deck builder
        onView(withId(R.id.button_backToDeckBuilder)).perform(click());
    }
}
