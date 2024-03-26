package com.internetEnemies.combatCritters.SystemTests.Tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.SystemTests.Assertions.RecyclerCountAssertion;
import com.internetEnemies.combatCritters.SystemTests.Assertions.RecyclerCountMinimumAssertion;
import com.internetEnemies.combatCritters.SystemTests.MyView.MyViewAction;
import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.presentation.MainMenuActivity;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

/**
 * TradingSystemTest.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-23
 *
 * @PURPOSE:    Test the trading
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TradingSystemTest {

    private CritterCard testCard;
    private int value;

    @Rule
    public ActivityScenarioRule<MainMenuActivity> mActivityRule = new ActivityScenarioRule<>(MainMenuActivity.class);

    @Test
    public void testTrading() {
        // Create a test card object
        testCard = new CritterCard(45,"Wrath of the Ocean","card_id_59",3, Card.Rarity.LEGENDARY, 5, 5, Arrays.asList(null, null, null));

        // Get an instance of CardInventoryHSQLDB
        ICardInventory cardInventory = Database.getInstance().getCardInventory();

        // Add the test card to the inventory twice because thats whats required for trade
        cardInventory.addCard(testCard);
        cardInventory.addCard(testCard);

        // Add 10 currency as its part of the trade
        value = 100;
        Currency startValue = new Currency(value);
        Database.getInstance().getCurrencyInventory().addToBalance(startValue);

        // Click deck builder button
        onView(withId(R.id.buttonToDeckBuilder)).perform(click());

        // Checking if cards are there (2 of 1 cards so assert 1)
        onView(withId(R.id.inventoryRecyclerView)).check(new RecyclerCountAssertion(1));

        // Click the main menu button
        onView(withId(R.id.button_mainMenu)).perform(click());

        // Click trading button
        onView(withId(R.id.buttonToTrading)).perform(click());

        // Click the deal button
        onView(withId(R.id.recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.dealButton)));

        // Check if the currency is not the same as when we started (i.e. we made a purchase)
        Currency endValue = Database.getInstance().getCurrencyInventory().getCurrentBalance();
        assert(!startValue.equals(endValue));
        value = endValue.getAmount();

        // Click main menu
        onView(withId(R.id.mainMenuButton)).perform(click());

        // Click deck builder button
        onView(withId(R.id.buttonToDeckBuilder)).perform(click());

        // Check if your inventory has at least 2 cards now because you will have 2 different cards (5 of 1 and 2 of the other) after the trade
        onView(withId(R.id.inventoryRecyclerView)).check(new RecyclerCountMinimumAssertion(2));
    }

    @After
    public void cleanup() {
        // Get an instance of CardInventoryHSQLDB
        ICardInventory cardInventory = Database.getInstance().getCardInventory();
        List<ItemStack<Card>> listToRemove = Database.getInstance().getCardInventory().getCards();

        // Remove the test card from the inventory
        for (ItemStack<Card> card : listToRemove) {
            cardInventory.removeCard(card.getItem(), card.getAmount());
        }

        // Deduct the added currency to reset the currency inventory
        Currency addedCurrency = new Currency(value);
        Database.getInstance().getCurrencyInventory().removeFromBalance(addedCurrency);
    }
}



