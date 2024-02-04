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

        binding.invGridView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                List<Card> cardsInInv = new ArrayList<>();
                cardsInInv = getInvCards();
                Card longClickedCard = cardsInInv.get(position);
                return false;
            }
        });
    }

    private List<Card> getInvCards() {
        List<CardSlot> invSlots = new ArrayList<>();

        NavigableMap<Double, Card.Rarity> rarityProbabilities = new TreeMap<>();
        rarityProbabilities.put(100.0, Card.Rarity.RARE);
        rarityProbabilities.put(900.0, Card.Rarity.RARE);
        rarityProbabilities.put(200.0, Card.Rarity.RARE);
        rarityProbabilities.put(300.0, Card.Rarity.COMMON);
        rarityProbabilities.put(400.0, Card.Rarity.COMMON);
        rarityProbabilities.put(500.0, Card.Rarity.RARE);
        rarityProbabilities.put(600.0, Card.Rarity.COMMON);

        CardSlot cardSlot1 = new CardSlot(rarityProbabilities);
        CardSlot cardSlot2 = new CardSlot(rarityProbabilities);
        CardSlot cardSlot3 = new CardSlot(rarityProbabilities);
        CardSlot cardSlot4 = new CardSlot(rarityProbabilities);
        CardSlot cardSlot5 = new CardSlot(rarityProbabilities);
        CardSlot cardSlot6 = new CardSlot(rarityProbabilities);
        CardSlot cardSlot7 = new CardSlot(rarityProbabilities);
        invSlots.add(cardSlot1);
        invSlots.add(cardSlot2);
        invSlots.add(cardSlot3);
        invSlots.add(cardSlot4);
        invSlots.add(cardSlot5);
        invSlots.add(cardSlot6);
        invSlots.add(cardSlot7);

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

        List<Card> yourCards = getInvCards();

        CardAdapter adapter = new CardAdapter(getContext(), yourCards);
        binding.invGridView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}