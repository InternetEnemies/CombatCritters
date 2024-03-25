package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.internetEnemies.combatCritters.Logic.ITradeUpHandler;
import com.internetEnemies.combatCritters.Logic.TradeUpHandler;
import com.internetEnemies.combatCritters.databinding.ActivityTradeUpBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;
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
    private ActivityTradeUpBinding binding;
    private ItemAdapter<ItemStack<Card>> cardStackAdapter;
    private ITradeUpHandler tradeUpHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTradeUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.tradeUpHandler = LogicProvider.getInstance().getTradeUpHandler();
        this.cardStackAdapter = new ItemAdapter<>(CardStackRenderer.getRenderers(tradeUpHandler.getCards(), this), this::cardClicked, false);

        Log.d("here", String.valueOf(tradeUpHandler.getCards().size()));
        binding.inventoryRecyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        binding.inventoryRecyclerView.addItemDecoration(new SpacingItemDecoration());
        binding.inventoryRecyclerView.setAdapter(cardStackAdapter);



        binding.mainMenuButton.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(TradeUpActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }

    private void cardClicked(ItemStack<Card> card) {

    }
}
