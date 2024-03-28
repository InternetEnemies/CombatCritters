package com.internetEnemies.combatCritters.presentation.battles;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.Logic.ITradesHandler;
import com.internetEnemies.combatCritters.Logic.ITransactionHandler;
import com.internetEnemies.combatCritters.Logic.battles.registry.IBattleRegistry;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.ActivityRewardsBinding;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.battles.RewardTransaction;
import com.internetEnemies.combatCritters.presentation.DeckBuilderActivity;
import com.internetEnemies.combatCritters.presentation.LogicProvider;
import com.internetEnemies.combatCritters.presentation.SpacingItemDecoration;
import com.internetEnemies.combatCritters.presentation.TradeItemAdapter;

import java.util.List;

public class RewardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.internetEnemies.combatCritters.databinding.ActivityRewardsBinding binding = ActivityRewardsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int battleId = getIntent().getIntExtra("battleId",-1);

        IBattleRegistry battleRegistry = LogicProvider.getInstance().getBattleRegistry();
        RewardTransaction rewards = battleRegistry.getOpponent(battleId).getReward();

        List<ItemStack<?>> itemStackList = rewards.getReceived();


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
