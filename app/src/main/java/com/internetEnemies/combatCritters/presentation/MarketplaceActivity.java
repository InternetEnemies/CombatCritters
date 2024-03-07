package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.internetEnemies.combatCritters.Logic.Bank;
import com.internetEnemies.combatCritters.Logic.IBank;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.ActivityMarketplaceBinding;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.presentation.renderable.CurrencyRenderer;

/**
 * MarketplaceActivity.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      06-March-2024
 *
 * @PURPOSE:     Main activity for the marketplace. Hosts two fragments: MarketBuyFragment and
 *               MarketplaceFragment.
 */
public class MarketplaceActivity extends AppCompatActivity implements IBuyButtonClickListener {
    private ActivityMarketplaceBinding binding;
    private int i = 10;     //TODO temp for testing
    IBank bank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMarketplaceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bank = new Bank();

        MarketplaceFragment marketplaceFragment = new MarketplaceFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.market_fragment_container, marketplaceFragment).commit();

        MarketBuyFragment marketBuyFragment = new MarketBuyFragment();
        marketBuyFragment.setBuyButtonClickListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.buy_fragment_container, marketBuyFragment).commit();

        onCreateSetup();
    }

    private void onCreateSetup() {
        binding.buttonMainMenu.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(MarketplaceActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Updates currency balance when buy button in MarketBuyFragment is clicked.
     */
    @Override
    public void onBuyButtonClicked() {
        //TODO: chaange this
        Log.d("here1", String.valueOf(bank.getCurrentBalance(0).getAmount()));
        LinearLayout balanceContainer = findViewById(R.id.balanceContainer);

        i=i-1;
        CurrencyRenderer currencyRenderer = new CurrencyRenderer(bank.getCurrentBalance(0), this);
        currencyRenderer.setWidth(50);
        currencyRenderer.setHeight(50);
        currencyRenderer.setTextSize(20);
        View currencyView = currencyRenderer.getView(null, balanceContainer);

        balanceContainer.removeAllViews();
        balanceContainer.addView(currencyView);
    }
}
