package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.tabs.TabLayout;
import com.internetEnemies.combatCritters.Logic.Bank;
import com.internetEnemies.combatCritters.Logic.IBank;
import com.internetEnemies.combatCritters.Logic.IMarketHandler;
import com.internetEnemies.combatCritters.Logic.MarketHandler;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.ActivityMarketplaceBinding;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.presentation.renderable.CurrencyRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.MarketTransactionRenderer;

/**
 * MarketplaceActivity.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     01-January-2024
 *
 * @PURPOSE:     Screen for browsing market offers. There are three tabs on this screen: one for
 *               displaying card offers, one for pack offers, and the last for bundle offers. Clicking
 *               on any of the offers will bring up the MarketplacePopupFragment.
 */
public class MarketplaceActivity extends AppCompatActivity {
    private ActivityMarketplaceBinding binding;
    private IBank bank;
    private IMarketHandler marketHandler;
    private ItemAdapter<MarketTransaction> cardTransactionsAdapter;
    private ItemAdapter<MarketTransaction> packTransactionsAdapter;
    private ItemAdapter<MarketTransaction> bundleTransactionsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMarketplaceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bank = new Bank();
        marketHandler = new MarketHandler();

        setupRecyclerView();
        setBalance();
        setupTabLayout();

        binding.mainMenuButton.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(MarketplaceActivity.this, MainMenuActivity.class);
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
        binding.recyclerView.addItemDecoration(new SpacingItemDecoration());

        cardTransactionsAdapter = new ItemAdapter<>(MarketTransactionRenderer.getRenderers(marketHandler.getCardOffers(), this), this::showMarketplacePopupFragment, false);
        packTransactionsAdapter = new ItemAdapter<>(MarketTransactionRenderer.getRenderers(marketHandler.getPackOffers(), this), this::showMarketplacePopupFragment, false);
        bundleTransactionsAdapter = new ItemAdapter<>(MarketTransactionRenderer.getRenderers(marketHandler.getBundleOffers(), this), this::showMarketplacePopupFragment, false);

        binding.recyclerView.setAdapter(cardTransactionsAdapter);
    }

    private void showMarketplacePopupFragment(MarketTransaction transaction) {
        MarketplacePopupFragment fragment = MarketplacePopupFragment.newInstance(transaction);
        fragment.setBuyButtonClickListener(this::setBalance);
        fragment.show(getSupportFragmentManager(), "marketplace_popup");
    }

    /**
     * Displays the players current balance.
     */
    private void setBalance() {
        FrameLayout balanceContainer = findViewById(R.id.balanceContainer);
        float scaleFactor = 1.5f;
        balanceContainer.setScaleX(scaleFactor);
        balanceContainer.setScaleY(scaleFactor);

        CurrencyRenderer currencyRenderer = new CurrencyRenderer(bank.getCurrentBalance(), this);
        View currencyView = currencyRenderer.getView(null, balanceContainer);

        balanceContainer.removeAllViews();
        balanceContainer.addView(currencyView);
    }
}
