package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardSlot;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Random;

public class PackOpener {
    private final Pack pack;

    public PackOpener(Pack pack, int numCards) {
        this.pack = pack;
    }

    public Card.Rarity randomByRarity(CardSlot slot){
        Card.Rarity rarity = null; //fix
        NavigableMap<Double,Card.Rarity> slotChances = slot.getCardPullChances();

        double maxRarityValue = Objects.requireNonNull(slotChances.lastEntry()).getKey();

        Random rnd = new Random();
        double randomValue = 1 + (maxRarityValue - 1) * rnd.nextDouble(); //generate a random double from 1-total inclusive

        rarity = slotChances.get(slotChances.higherKey(randomValue));

        return rarity;

    }
    public List<Card> findCardsOfRarity(Card.Rarity rarity, Pack pack){
        //return the set of all cards of a single rarity in a pack
        List<Card> setList = pack.getSetList();
        setList.removeIf(i -> !(i.getRarity().equals(rarity)));
        return setList;
    }
    private Card cardFromList(List<Card> cardList){
        //get a random card from list
        Random rnd = new Random();
        int index = rnd.nextInt(cardList.size());

        return cardList.get(index);
    }
    public List<Card> pullCards(Pack pack){
        List<Card> cardsPulled = new ArrayList<>();

        for (CardSlot slot : pack.getProbabilityList()){
            Card.Rarity rarity = randomByRarity(slot);
            List<Card> cardsOfRarity = findCardsOfRarity(rarity, pack);
            cardsPulled.add(cardFromList(cardsOfRarity));

        }

        return cardsPulled;
    }

}
