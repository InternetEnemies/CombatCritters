package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.Logic.ITradeUpHandler;
import com.internetEnemies.combatCritters.Logic.TradeUpHandler;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.ActivityTradeUpBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.CardStackRenderer;

import java.util.ArrayList;

/**
 * TradeUpActivity.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     24-March-2024
 *
 * @PURPOSE:
 */
public class TradeUpActivity extends AppCompatActivity {
    private ItemAdapter<ItemStack<Card>> inventoryAdapter;
    private ItemAdapter<Card> tradeUpAdapter;
    private ITradeUpHandler tradeUpHandler;
    private ViewGroup mysteryCardContainer;
    private static final float SCALE_FACTOR = .65f; //Scale factor for trade up cards


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.internetEnemies.combatCritters.databinding.ActivityTradeUpBinding binding = ActivityTradeUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.mysteryCardContainer = findViewById(R.id.mysteryCardContainer);
        this.tradeUpHandler = new TradeUpHandler();//LogicProvider.getInstance().getTradeUpHandler();
        this.inventoryAdapter = new ItemAdapter<>(CardStackRenderer.getRenderers(tradeUpHandler.getCards(), this), this::inventoryCardClicked, false);
        this.tradeUpAdapter = new ItemAdapter<>(new ArrayList<>(), this::tradeUpCardClicked, false);

        binding.inventoryRecyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        binding.inventoryRecyclerView.addItemDecoration(new SpacingItemDecoration());
        binding.inventoryRecyclerView.setAdapter(inventoryAdapter);

        binding.tradeUpRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.tradeUpRecyclerView.addItemDecoration(new SpacingItemDecoration(-10, 10));
        binding.tradeUpRecyclerView.setAdapter(tradeUpAdapter);

        binding.mainMenuButton.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(TradeUpActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }

    private void inventoryCardClicked(ItemStack<Card> cardStack) {
        tradeUpHandler.addCard(cardStack.getItem());
        tradeUpAdapter.updateItems(CardRenderer.getRenderers(tradeUpHandler.getSelectedCards(), this, SCALE_FACTOR));
        inventoryAdapter.updateItems(CardStackRenderer.getRenderers(tradeUpHandler.getCards(), this));
//        mysteryCardContainer.addView((new MysterCardRenderer(tradeUpHandler.get)))
    }

    private void tradeUpCardClicked(Card card) {
        tradeUpHandler.removeCard(card);
        tradeUpAdapter.updateItems(CardRenderer.getRenderers(tradeUpHandler.getSelectedCards(), this, SCALE_FACTOR));
        inventoryAdapter.updateItems(CardStackRenderer.getRenderers(tradeUpHandler.getCards(), this));
    }
}
