package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.Logic.IPackInventoryManager;
import com.internetEnemies.combatCritters.Logic.PackInventoryManager;
import com.internetEnemies.combatCritters.databinding.ActivityPackOpeningBinding;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.PackRenderer;

import java.util.List;

public class PackOpeningActivity extends AppCompatActivity {
    private final int ITEM_SPACING = 15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPackOpeningBinding binding = ActivityPackOpeningBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PackOpeningActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });

        IPackInventoryManager packInventoryManager = new PackInventoryManager();
        List<Pack> packs = packInventoryManager.getPacks();

        RecyclerView recyclerView = binding.recyclerView;

        if(packs.size() != 0) {
            if (packs.size() < 4) {
                recyclerView.setLayoutManager(new GridLayoutManager(this, packs.size()));
            }
            else {
                recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
            }
        }
        else {
            binding.noPacks.setVisibility(View.VISIBLE);
        }
        recyclerView.addItemDecoration(new SpacingItemDecoration(ITEM_SPACING));

        ItemAdapter<Pack> adapter = new ItemAdapter<>(PackRenderer.getRenderers(packs, this), this::showPackOpeningPopup, false);

        recyclerView.setAdapter(adapter);
    }

    private void showPackOpeningPopup(Pack pack) {
        PackOpeningPopupFragment fragment = PackOpeningPopupFragment.newInstance(pack);
        fragment.show(getSupportFragmentManager(), "pack_opening_popup");
    }
}
