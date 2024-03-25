package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.Logic.ITradeUpHandler;
import com.internetEnemies.combatCritters.Logic.TradeUpHandler;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.internetEnemies.combatCritters.databinding.ActivityTradeUpBinding binding = ActivityTradeUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.tradeUpHandler = new TradeUpHandler();//LogicProvider.getInstance().getTradeUpHandler();
        this.inventoryAdapter = new ItemAdapter<>(CardStackRenderer.getRenderers(tradeUpHandler.getCards(), this), this::inventoryCardClicked, false);
        this.tradeUpAdapter = new ItemAdapter<>(new ArrayList<>(), this::tradeUpCardClicked, false);

        binding.inventoryRecyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        binding.inventoryRecyclerView.addItemDecoration(new SpacingItemDecoration());
        binding.inventoryRecyclerView.setAdapter(inventoryAdapter);

        binding.tradeUpRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        binding.tradeUpRecyclerView.addItemDecoration(new SpacingItemDecoration());
        binding.tradeUpRecyclerView.setAdapter(tradeUpAdapter);

        binding.mainMenuButton.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(TradeUpActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }

    private void inventoryCardClicked(ItemStack<Card> cardStack) {
        tradeUpHandler.addCard(cardStack.getItem());
        tradeUpAdapter.updateItems(CardRenderer.getRenderers(tradeUpHandler.getSelectedCards(), this));
        inventoryAdapter.updateItems(CardStackRenderer.getRenderers(tradeUpHandler.getCards(), this));
//        Log.d("here", String.valueOf(tradeUpHandler.getCards().get(0).getAmount()));
    }

    private void tradeUpCardClicked(Card card) {
        tradeUpHandler.removeCard(card);
        tradeUpAdapter.updateItems(CardRenderer.getRenderers(tradeUpHandler.getSelectedCards(), this));
        inventoryAdapter.updateItems(CardStackRenderer.getRenderers(tradeUpHandler.getCards(), this));
    }
}
