package com.internetEnemies.combatCritters.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.CardsOpenedBinding;
import com.internetEnemies.combatCritters.objects.CritterCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardsOpened extends Fragment {

    private CardsOpenedBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = CardsOpenedBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<CritterCard> cards = new ArrayList<>();
        cards.add(new CritterCard(1, "Charzard", "xx", 19, CritterCard.Rarity.COMMON, 12, 12, Collections.singletonList(1)));
        cards.add(new CritterCard(1, "Charzard", "xx", 19, CritterCard.Rarity.COMMON, 12, 12, Collections.singletonList(1)));


        CardAdapter adapter = new CardAdapter(getContext(), cards);
        binding.cardsGridView.setAdapter(adapter);

        binding.buttonBackToDeckBuilder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CardsOpened.this)
                        .navigate(R.id.action_CardsOpened_to_DeckBuilder);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}