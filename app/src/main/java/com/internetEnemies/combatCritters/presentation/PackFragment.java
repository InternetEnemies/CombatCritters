package com.internetEnemies.combatCritters.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.FragmentCardBinding;
import com.internetEnemies.combatCritters.databinding.FragmentPackBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;

import java.util.ArrayList;

public class PackFragment extends Fragment {
    private FragmentPackBinding binding;
    private ItemGridFragment<Card> gridFrag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPackBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (gridFrag == null) {
            gridFrag = new ItemGridFragment<>(new ArrayList<>());
            getChildFragmentManager().beginTransaction().replace(R.id.fragContainer, gridFrag).commit();
        }

        if (getArguments() != null && getArguments().containsKey("pack")) {
            Pack pack = (Pack) getArguments().getSerializable("pack");

            if (pack != null) {
                gridFrag.updateItems(CardRenderer.getRenderers(pack.getSetList(), this.getContext()));
            }
        }
    }
}
