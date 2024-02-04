package com.internetEnemies.combatCritters.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.DeckBuilderBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardSlot;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class DeckBuilderFragment extends Fragment {

    private DeckBuilderBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = DeckBuilderBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonOpenPack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DeckBuilderFragment.this)
                        .navigate(R.id.action_DeckBuilder_to_PackOpening);
            }
        });

//        binding.invGridView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                List<Card> cardsInInv = new ArrayList<>();
//                cardsInInv = getInvCards();
//                Card longClickedCard = cardsInInv.get(0);
//                return false;
//            }
//        });
    }

    private List<Card> getInvCards() {
        List<Card> cards = new ArrayList<>();
        CritterCard card1 = new CritterCard(1, "Card 1", "Image", 3, Card.Rarity.RARE, 22, 100, Collections.singletonList(1));
        CritterCard card2 = new CritterCard(1, "Card 1", "Image", 3, Card.Rarity.RARE, 22, 100, Collections.singletonList(1));
        ItemCard card3 = new ItemCard(1, "Card 1", "Image", 3, Card.Rarity.COMMON,  2);
        ItemCard card4 = new ItemCard(1, "Card 1", "Image", 3, Card.Rarity.COMMON,  2);
        CritterCard card5 = new CritterCard(1, "Card 1", "Image", 3, Card.Rarity.COMMON, 22, 100, Collections.singletonList(1));
        CritterCard card6 = new CritterCard(1, "Card 1", "Image", 3, Card.Rarity.RARE, 22, 100, Collections.singletonList(1));
        CritterCard card7 = new CritterCard(1, "Card 1", "Image", 3, Card.Rarity.COMMON, 22, 100, Collections.singletonList(1));
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);

        return cards;
    }

    @Override
    public void onResume() {
        super.onResume();

        CardAdapter adapter1 = new CardAdapter(getContext(), getInvCards());
        CardAdapter adapter2 = new CardAdapter(getContext(), getInvCards());
        binding.deckBuilderGridView.setAdapter(adapter1);
        binding.inventoryGridView.setAdapter(adapter2);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}