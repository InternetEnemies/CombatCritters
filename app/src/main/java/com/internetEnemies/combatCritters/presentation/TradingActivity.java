package com.internetEnemies.combatCritters.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.Logic.IPackInventoryManager;
import com.internetEnemies.combatCritters.databinding.ActivityPackOpeningBinding;
import com.internetEnemies.combatCritters.databinding.ActivityTradingBinding;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.PackRenderer;

import java.util.List;

public class TradingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTradingBinding binding = ActivityTradingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
