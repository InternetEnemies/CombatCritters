package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.internetEnemies.combatCritters.Logic.ITradeUpHandler;
import com.internetEnemies.combatCritters.Logic.exceptions.InvalidTradeUpCardsException;
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
        TradeUpValidity tradeUpValidity = tradeUpHandler.addCard(cardStack.getItem());
        tradeUpAdapter.updateItems(CardRenderer.getRenderers(tradeUpHandler.getSelectedCards(), this, SCALE_FACTOR));
        inventoryAdapter.updateItems(CardStackRenderer.getRenderers(tradeUpHandler.getCards(), this));
        refreshAmountRequired();

        showTradeUpMysteryCard(tradeUpValidity.isValid());
    }

    /**
     * Called when a card in the trade up recyclerview is clicked.
     *
     * @param card card that was clicked
     */
    private void tradeUpCardClicked(Card card) {
        TradeUpValidity tradeUpValidity = tradeUpHandler.removeCard(card);
        tradeUpAdapter.updateItems(CardRenderer.getRenderers(tradeUpHandler.getSelectedCards(), this, SCALE_FACTOR));
        inventoryAdapter.updateItems(CardStackRenderer.getRenderers(tradeUpHandler.getCards(), this));

        showTradeUpMysteryCard(tradeUpValidity.isValid());
    }

    /**
     * Called when trade up button is clicked. Will attempt to perform the trade up.
     */
    private void tradeUpButtonClicked() {
        try {
            showTradeUpResultPopup(tradeUpHandler.confirmTradeUp());
        }
        catch(InvalidTradeUpCardsException e) {
            Toast.makeText(this, "Not enough cards to trade up.", Toast.LENGTH_SHORT).show();
        }
        catch(IllegalArgumentException e) {
            Toast.makeText(this, "Error. These cards were not found in your inventory.", Toast.LENGTH_SHORT).show();
        }

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

    private void refreshAmountRequired() {
        binding.amountRequired.setText(String.valueOf(tradeUpHandler.getCards().size()));
    }
}
