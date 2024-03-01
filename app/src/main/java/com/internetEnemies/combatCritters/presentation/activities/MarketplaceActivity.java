package com.internetEnemies.combatCritters.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.ActivityMarketplaceBinding;

public class MarketplaceActivity extends AppCompatActivity {
    private ActivityMarketplaceBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMarketplaceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onCreateSetup();
    }

    private void onCreateSetup() {
        binding.buttonMainMenu.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(MarketplaceActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }

    private void setupTabLayout() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Bundles"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Packs"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Cards"));

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment selectedFragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        //Cards tab selected
//                        selectedFragment = new MarketplaceCardsFragment();
                        break;
                    case 1:
                        //Packs tab selected
//                        selectedFragment = new MarketplacePacksFragment();
                        break;
                    case 2:
                        //Bundles tab selected
//                        selectedFragment = new MarketplaceBundlesFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.marketplace_fragment_container, selectedFragment)
                        .commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        binding.tabLayout.selectTab(binding.tabLayout.getTabAt(0));
    }
}
