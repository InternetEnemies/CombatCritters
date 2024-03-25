package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.internetEnemies.combatCritters.Logic.IBank;
import com.internetEnemies.combatCritters.Logic.ITradesHandler;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.ActivityTradingBinding;
import com.internetEnemies.combatCritters.objects.TradeTransaction;
import com.internetEnemies.combatCritters.presentation.renderable.CurrencyRenderer;

/**
 * TradingActivity.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     22-March-2024
 *
 * @PURPOSE:     Screen for browsing trade offers.
 */
public class TradingActivity extends AppCompatActivity {
    private ITradesHandler tradesHandler;
    private static final float CURRENCY_SCALE_FACTOR = 1.5F;
    private IBank bank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.internetEnemies.combatCritters.databinding.ActivityTradingBinding binding = ActivityTradingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tradesHandler = LogicProvider.getInstance().getTradesHandler();
        bank = LogicProvider.getInstance().getBank();

        setBalance();

        TradeTransactionAdapter adapter = new TradeTransactionAdapter(tradesHandler.getOffers(), this::onDealClicked);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerView.addItemDecoration(new SpacingItemDecoration());
        binding.recyclerView.setAdapter(adapter);

        binding.mainMenuButton.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(TradingActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }

    private void onDealClicked(TradeTransaction transaction) {
        if(tradesHandler.performTransaction(transaction)) {
            Toast.makeText(this, "Trade successful", Toast.LENGTH_SHORT).show();
            setBalance();
        } else {
            Toast.makeText(this, "Missing required items", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Displays the players current balance.
     */
    private void setBalance() {
        FrameLayout balanceContainer = findViewById(R.id.balanceContainer);
        balanceContainer.setScaleX(CURRENCY_SCALE_FACTOR);
        balanceContainer.setScaleY(CURRENCY_SCALE_FACTOR);

        CurrencyRenderer currencyRenderer = new CurrencyRenderer(bank.getCurrentBalance(), this);
        View currencyView = currencyRenderer.getView(null, balanceContainer);

        balanceContainer.removeAllViews();
        balanceContainer.addView(currencyView);
    }
}