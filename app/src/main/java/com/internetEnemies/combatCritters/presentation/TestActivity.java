package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.Logic.IPackCatalog;
import com.internetEnemies.combatCritters.Logic.IPackInventoryManager;
import com.internetEnemies.combatCritters.Logic.PackCatalog;
import com.internetEnemies.combatCritters.Logic.PackInventoryManager;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.PackCardDatabase;
import com.internetEnemies.combatCritters.databinding.ActivityPackOpeningBinding;
import com.internetEnemies.combatCritters.databinding.ActivityTestBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.ItemRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.PackRenderer;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTestBinding binding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });

        IPackInventoryManager packInventoryManager = new PackInventoryManager();

        RecyclerView recyclerView = binding.recyclerView;

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));


        ItemAdapter<Pack> adapter = new ItemAdapter<>(PackRenderer.getRenderers(packInventoryManager.getPacks(), this), pack -> {
            showPackOpeningPopup(pack);
        }, false);


        recyclerView.setAdapter(adapter);
    }

    private void showPackOpeningPopup(Pack pack) {
        PackOpeningPopupFragment fragment = PackOpeningPopupFragment.newInstance(pack);
        fragment.show(getSupportFragmentManager(), "pack_opening_popup");
    }
}
