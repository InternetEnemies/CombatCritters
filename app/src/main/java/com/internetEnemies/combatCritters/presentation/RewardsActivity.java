package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.Logic.IPackInventoryManager;
import com.internetEnemies.combatCritters.Logic.ITradesHandler;
import com.internetEnemies.combatCritters.Logic.TradesHandler;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.ActivityCardsOpenedBinding;
import com.internetEnemies.combatCritters.databinding.ActivityRewardsBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;

import java.io.Serializable;
import java.util.List;

public class RewardsActivity extends AppCompatActivity {
    public static final String ARG_KEY = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.internetEnemies.combatCritters.databinding.ActivityRewardsBinding binding = ActivityRewardsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ITradesHandler tradesHandler = LogicProvider.getInstance().getTradesHandler(); //TODO remove this
        List<ItemStack<?>> itemStackList = tradesHandler.getOffers().get(0).getGiven();


        RecyclerView recyclerView = findViewById(R.id.cardsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new SpacingItemDecoration());

        TradeItemAdapter adapter = new TradeItemAdapter(itemStackList);
        recyclerView.setAdapter(adapter);

        binding.buttonBackToDeckBuilder.setOnClickListener(view -> {
            Intent intent = new Intent(RewardsActivity.this, DeckBuilderActivity.class);
            startActivity(intent);
        });
    }
}
