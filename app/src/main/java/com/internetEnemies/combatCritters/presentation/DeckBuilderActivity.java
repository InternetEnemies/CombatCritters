package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;


import androidx.appcompat.app.AppCompatActivity;

import com.internetEnemies.combatCritters.Logic.IBank;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.ActivityDeckBuilderBinding;
import com.internetEnemies.combatCritters.presentation.renderable.CurrencyRenderer;

public class DeckBuilderActivity extends AppCompatActivity {
    private ActivityDeckBuilderBinding binding;
    private IBank bank;
    private static final float CURRENCY_SCALE_FACTOR = 1.5F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeckBuilderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.bank = LogicProvider.getInstance().getBank();

        onCreateSetup();
    }

    private void onCreateSetup() {
        setBalance();
        BuilderFragment deckBuilderFragment = new BuilderFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.builderFragmentContainer, deckBuilderFragment).commit();

        InventoryFragment inventoryFragment = InventoryFragment.newInstance();
        inventoryFragment.setCardSoldListener(this::setBalance);
        getSupportFragmentManager().beginTransaction().replace(R.id.inventoryFragmentContainer, inventoryFragment).commit();

        binding.buttonMainMenu.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(DeckBuilderActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
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
