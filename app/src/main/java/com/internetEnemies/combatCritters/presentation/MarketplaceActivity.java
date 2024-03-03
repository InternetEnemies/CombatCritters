package com.internetEnemies.combatCritters.presentation;

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

    private MarketplaceFragment marketplaceFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMarketplaceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        marketplaceFragment = new MarketplaceFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.market_fragment_container, marketplaceFragment).commit();

        onCreateSetup();
    }

    private void onCreateSetup() {
        binding.buttonMainMenu.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(MarketplaceActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
//        setupTabLayout();
    }
//
//    private void setupTabLayout() {
//        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                Fragment selectedFragment = getFragmentForTab(tab.getPosition());
//                if (selectedFragment != null) {
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.market_fragment_container, selectedFragment)
//                            .commit();
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//            }
//        });
//
//        if (binding.tabLayout.getTabAt(0) != null) {
//            binding.tabLayout.getTabAt(0).select();
//        }
//    }
//
//    private Fragment getFragmentForTab(int position) {
//        switch (position) {
//            case 0:
//                return new MarketplaceCardsFragment();
//            case 1:
//                return new MarketplacePacksFragment();
//            case 2:
//                return new MarketplaceBundlesFragment();
//            default:
//                return null;
//        }
//    }
}
