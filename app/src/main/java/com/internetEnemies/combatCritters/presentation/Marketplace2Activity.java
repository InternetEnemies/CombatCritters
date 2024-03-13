package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.internetEnemies.combatCritters.Logic.Bank;
import com.internetEnemies.combatCritters.Logic.IBank;
import com.internetEnemies.combatCritters.Logic.IMarketHandler;
import com.internetEnemies.combatCritters.Logic.MarketHandler;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.ActivityMarketplace2Binding;
import com.internetEnemies.combatCritters.databinding.ActivityMarketplaceBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.BundleRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.CurrencyRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.ItemRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.MarketTransactionRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.PackRenderer;

import java.util.ArrayList;

public class Marketplace2Activity extends AppCompatActivity {
    private ActivityMarketplace2Binding binding;
    private IBank bank;
    private IMarketHandler marketHandler;
    private ItemAdapter<MarketTransaction> cardTransactionsAdapter;
    private ItemAdapter<MarketTransaction> packTransactionsAdapter;
    private ItemAdapter<MarketTransaction> bundleTransactionsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMarketplace2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bank = new Bank();
        marketHandler = new MarketHandler();

        setupRecyclerView();
        setBalance();
        setupTabLayout();

        binding.mainMenuButton.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(Marketplace2Activity.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }

    private void setupTabLayout() {
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0: // Cards tab selected
                        binding.recyclerView.setAdapter(cardTransactionsAdapter);
                        break;
                    case 1: // Packs tab selected
                        binding.recyclerView.setAdapter(packTransactionsAdapter);
                        break;
                    case 2: // Bundles tab selected
                        binding.recyclerView.setAdapter(bundleTransactionsAdapter);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void setupRecyclerView() {
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        binding.recyclerView.addItemDecoration(new SpacingItemDecoration(15));

        cardTransactionsAdapter = new ItemAdapter<>(MarketTransactionRenderer.getRenderers(marketHandler.getCardOffers(), this), null, true);
        packTransactionsAdapter = new ItemAdapter<>(MarketTransactionRenderer.getRenderers(marketHandler.getPackOffers(), this), null, true);
        bundleTransactionsAdapter = new ItemAdapter<>(MarketTransactionRenderer.getRenderers(marketHandler.getBundleOffers(), this), null, true);

        binding.recyclerView.setAdapter(cardTransactionsAdapter);
    }


    private void setBalance() {
        FrameLayout balanceContainer = findViewById(R.id.balanceContainer);

        CurrencyRenderer currencyRenderer = new CurrencyRenderer(bank.getCurrentBalance(), this);
        View currencyView = currencyRenderer.getView(null, balanceContainer);

        TextView currencyTextView = currencyView.findViewById(R.id.currencyTextView);
        currencyTextView.setTextColor(Color.BLACK);

        balanceContainer.removeAllViews();
        balanceContainer.addView(currencyView);
    }
}
