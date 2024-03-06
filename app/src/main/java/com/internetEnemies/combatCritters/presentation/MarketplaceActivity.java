package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.internetEnemies.combatCritters.Logic.Bank;
import com.internetEnemies.combatCritters.Logic.TransactionHandler;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.data.CardInventoryStub;
import com.internetEnemies.combatCritters.data.CurrencyInventoryStub;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.data.PackInventoryStub;
import com.internetEnemies.combatCritters.databinding.ActivityMarketplaceBinding;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.presentation.renderable.CurrencyRenderer;

public class MarketplaceActivity extends AppCompatActivity implements IBuyButtonClickListener {
    private ActivityMarketplaceBinding binding;
    private Bank bank;
    private int i = 10;     //TODO temp


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMarketplaceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //TODO channge this
        ICurrencyInventory currencyInventory = new CurrencyInventoryStub();
        bank = new Bank(currencyInventory);


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

    @Override
    public void onBuyButtonClicked() {
        Log.d("here1", "here");
        LinearLayout balanceContainer = findViewById(R.id.balanceContainer);

        i=i-1;
        CurrencyRenderer currencyRenderer = new CurrencyRenderer(new Currency(i)/*bank.getCurrentBalance(0)*/, this);
        currencyRenderer.setWidth(50);
        currencyRenderer.setHeight(50);
        View currencyView = currencyRenderer.getView(null, balanceContainer);

        balanceContainer.removeAllViews();
        balanceContainer.addView(currencyView);
    }
}
