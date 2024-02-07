package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.internetEnemies.combatCritters.Logic.PackOpener;
import com.internetEnemies.combatCritters.databinding.ActivityCardsOpenedBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardSlot;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CardsOpenedActivity extends AppCompatActivity {

    private ActivityCardsOpenedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCardsOpenedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.buttonBackToDeckBuilder.setOnClickListener(view -> {
            Intent intent = new Intent(CardsOpenedActivity.this, DeckBuilderActivity.class);
            startActivity(intent);
        });

        List<Card> pulledCards = getPack();
        CardWithoutQuantityAdapter adapter = new CardWithoutQuantityAdapter(this, pulledCards);
        binding.cardsGridView.setAdapter(adapter);
    }

    private List<Card> getPack() {
        List<CardSlot> cardSlots = new ArrayList<>();

        NavigableMap<Double, Card.Rarity> rarityProbabilities = new TreeMap<>();
        rarityProbabilities.put(100.0, Card.Rarity.RARE);
        rarityProbabilities.put(900.0, Card.Rarity.RARE);
        rarityProbabilities.put(200.0, Card.Rarity.RARE);
        rarityProbabilities.put(300.0, Card.Rarity.COMMON);
        rarityProbabilities.put(400.0, Card.Rarity.COMMON);

        CardSlot cardSlot1 = new CardSlot(rarityProbabilities);
        CardSlot cardSlot2 = new CardSlot(rarityProbabilities);
        CardSlot cardSlot3 = new CardSlot(rarityProbabilities);
        CardSlot cardSlot4 = new CardSlot(rarityProbabilities);
        CardSlot cardSlot5 = new CardSlot(rarityProbabilities);
        cardSlots.add(cardSlot1);
        cardSlots.add(cardSlot2);
        cardSlots.add(cardSlot3);
        cardSlots.add(cardSlot4);
        cardSlots.add(cardSlot5);

        List<Card> cards = new ArrayList<>();
        CritterCard card1 = new CritterCard(1, "Card 1", "Image", 3, Card.Rarity.RARE, 22, 100, Collections.singletonList(1));
        CritterCard card2 = new CritterCard(1, "Card 1", "Image", 3, Card.Rarity.RARE, 22, 100, Collections.singletonList(1));
        ItemCard card3 = new ItemCard(1, "Card 1", "Image", 3, Card.Rarity.COMMON,  2);
        ItemCard card4 = new ItemCard(1, "Card 1", "Image", 3, Card.Rarity.COMMON,  2);
        CritterCard card5 = new CritterCard(1, "Card 1", "Image", 3, Card.Rarity.COMMON, 22, 100, Collections.singletonList(1));
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);

        Pack pack = new Pack(1, "Standard Pack", "image", cardSlots, cards);

        PackOpener packOpener = new PackOpener();
        return packOpener.pullCards(pack);
    }
}
