package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.internetEnemies.combatCritters.Logic.ITradeUpHandler;
import com.internetEnemies.combatCritters.Logic.TradeUpValidator;
import com.internetEnemies.combatCritters.Logic.exceptions.InvalidTradeUpCardsException;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.ActivityTradeUpBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.TradeUpValidity;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.CardStackRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.MysteryCardRenderer;

import java.util.ArrayList;

/**
 * TradeUpActivity.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     24-March-2024
 *
 * @PURPOSE:    Screen for trade ups.
 */
public class TradeUpActivity extends AppCompatActivity {
    ActivityTradeUpBinding binding;
    private ItemAdapter<ItemStack<Card>> inventoryAdapter;
    private ItemAdapter<Card> tradeUpAdapter;
    private ITradeUpHandler tradeUpHandler;
    private static final float SCALE_FACTOR = .65f; //Scale factor for trade up cards


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityTradeUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.tradeUpHandler = LogicProvider.getInstance().getTradeUpHandler();
        tradeUpHandler.reset();

        this.inventoryAdapter = new ItemAdapter<>(CardStackRenderer.getRenderers(tradeUpHandler.getCards(), this), this::inventoryCardClicked, false);
        this.tradeUpAdapter = new ItemAdapter<>(new ArrayList<>(), this::tradeUpCardClicked, false);

        setAmountRequired(false);
        setupInventoryRecyclerView();
        setupTradeUpRecyclerView();

        binding.tradeUpButton.setOnClickListener(v -> tradeUpButtonClicked());

        binding.mainMenuButton.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(TradeUpActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Called when a cardStack in the inventory recyclerview is clicked.
     *
     * @param cardStack cardStack that was clicked
     */
    private void inventoryCardClicked(ItemStack<Card> cardStack) {
        if(!tradeUpHandler.isValid().isValid()) {
            tradeUpHandler.addCard(cardStack.getItem());
            refreshTradeUp();
        } else {
            Toast.makeText(this, "Can't add more cards to trade up", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Called when a card in the trade up recyclerview is clicked.
     *
     * @param card card that was clicked
     */
    private void tradeUpCardClicked(Card card) {
        tradeUpHandler.removeCard(card);
        refreshTradeUp();
    }

    /**
     * Called when trade up button is clicked. Will attempt to perform the trade up.
     */
    private void tradeUpButtonClicked() {
        try {
            showTradeUpResultPopup(tradeUpHandler.confirmTradeUp());
            refreshTradeUp();
        }
        catch(InvalidTradeUpCardsException e) { //This should never happen
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Sets the text that displays how many cards currently in trade up
     *
     * @param isValid change the colour of the text based on the isValid
     */
    private void setAmountRequired(boolean isValid) {
        if(isValid) {
            binding.amountRequired.setTextColor(Color.GREEN);
        } else {
            binding.amountRequired.setTextColor(ContextCompat.getColor(this, R.color.redAmount));
        }
        String amountRequiredText = tradeUpHandler.getSelectedCards().size() + "/" + TradeUpValidator.TRADE_UP_REQUIREMENT;
        binding.amountRequired.setText(amountRequiredText);
    }

    /**
     * Displays/hides the mystery card
     *
     * @param show whether or not to show the mystery card
     */
    private void showTradeUpMysteryCard(boolean show) {
        if(show) {
            binding.mysteryCardContainer.removeAllViews();
            binding.mysteryCardContainer.addView((new MysteryCardRenderer(tradeUpHandler.getCurrentTradeUpRarity(), this, SCALE_FACTOR)).getView(null, binding.mysteryCardContainer));
            binding.tradeUpButton.setVisibility(View.VISIBLE);
        }
        else {
            binding.mysteryCardContainer.removeAllViews();
            binding.tradeUpButton.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Show up CardPopupFragment
     *
     * @param card card to display in CardPopupFragment
     */
    private void showTradeUpResultPopup(Card card) {
        CardPopupFragment fragment = CardPopupFragment.newInstance(card);
        fragment.show(getSupportFragmentManager(), "card_popup");
    }

    /**
     * Update both adapters. Set the required text and set the mystery card.
     */
    private void refreshTradeUp() {
        tradeUpAdapter.updateItems(CardRenderer.getRenderers(tradeUpHandler.getSelectedCards(), this, SCALE_FACTOR));
        inventoryAdapter.updateItems(CardStackRenderer.getRenderers(tradeUpHandler.getCards(), this));
        setAmountRequired(tradeUpHandler.isValid().isValid());
        showTradeUpMysteryCard(tradeUpHandler.isValid().isValid());
    }

    private void setupInventoryRecyclerView() {
        binding.inventoryRecyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        binding.inventoryRecyclerView.addItemDecoration(new SpacingItemDecoration());
        binding.inventoryRecyclerView.setAdapter(inventoryAdapter);
    }

    private void setupTradeUpRecyclerView() {
        binding.tradeUpRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.tradeUpRecyclerView.addItemDecoration(new SpacingItemDecoration(0, 0));
        binding.tradeUpRecyclerView.setAdapter(tradeUpAdapter);
    }
}
