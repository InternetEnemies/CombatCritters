package com.internetEnemies.combatCritters.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.FragmentBundleBinding;
import com.internetEnemies.combatCritters.databinding.FragmentPackBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.PackRenderer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * BundleFragment.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      06-March-2024
 *
 * @PURPOSE:     Fragment used for displaying bundles. Fragment contains two GridViews, one for
 *               displaying the cards in the bundle, the other for displaying the packs in the bundle.
 */
public class BundleFragment extends Fragment {
    private ItemGridFragment<Card> cardsGridFrag;
    private ItemGridFragment<Pack> packsGridFrag;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        com.internetEnemies.combatCritters.databinding.FragmentBundleBinding binding = FragmentBundleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (cardsGridFrag == null) {
            cardsGridFrag = new ItemGridFragment<>(new ArrayList<>());
            getChildFragmentManager().beginTransaction().replace(R.id.cardsFragContainer, cardsGridFrag).commit();
        }

        if (packsGridFrag == null) {
            packsGridFrag = new ItemGridFragment<>(new ArrayList<>());
            getChildFragmentManager().beginTransaction().replace(R.id.packsFragContainer, packsGridFrag).commit();
        }

        if (getArguments() != null && getArguments().containsKey("packs") && getArguments().containsKey("cards")) {
            Serializable packsSerializable = getArguments().getSerializable(("packs"));
            if(packsSerializable instanceof List<?>) {
                List<Pack> packs = (List<Pack>) packsSerializable;
                packsGridFrag.updateItems(PackRenderer.getRenderers(packs, this.getContext()));
            }

            Serializable cardsSerializable = getArguments().getSerializable("cards");
            if (cardsSerializable instanceof List<?>) {
                List<Card> cards = (List<Card>) cardsSerializable;
                cardsGridFrag.updateItems(CardRenderer.getRenderers(cards, this.getContext()));
            }
        }
    }
}
