package com.internetEnemies.combatCritters.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.internetEnemies.combatCritters.databinding.FragmentCardBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.presentation.renderable.ItemViewVisitor;

/**
 * BundleFragment.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      06-March-2024
 *
 * @PURPOSE:     Fragment used for displaying a Card.
 */
public class CardFragment extends Fragment {
    private FragmentCardBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null && getArguments().containsKey("card")) {
            Card card = (Card) getArguments().getSerializable("card");

            if (card != null) {
                binding.cardText.setText(card.getName());

                ItemViewVisitor viewVisitor = new ItemViewVisitor(getContext(), (ViewGroup) binding.cardContainer);
                card.accept(viewVisitor);
                View cardView = viewVisitor.getView();
                binding.cardContainer.addView(cardView);
            }
        }
    }


}
