package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardSlot;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Random;

public class PackOpener {
    public PackOpener() {
    }

    //Get the rarity of a card in the pack.
    public Card.Rarity randomByRarity(CardSlot slot){
        Card.Rarity rarity = null; //fix
        NavigableMap<Double,Card.Rarity> slotChances = slot.getCardPullChances();

        double maxRarityValue = Objects.requireNonNull(slotChances.lastEntry()).getKey();

        Random rnd = new Random();
        double randomValue = 1 + (maxRarityValue - 1) * rnd.nextDouble();

        rarity = slotChances.get(slotChances.higherKey(randomValue));

        return rarity;

    }

    //return the set of all cards of a single rarity in a pack
    public List<Card> findCardsOfRarity(Card.Rarity rarity, Pack pack){
        List<Card> setList = pack.getSetList();
        List<Card> resultSet = new ArrayList<>();

        for (Card c:setList) {
            if (c.getRarity().equals(rarity)){
                resultSet.add(c);
            }
        }
        return resultSet;
    }

    //get a random card from list
    private Card cardFromList(List<Card> cardList){
        int index = (int)(Math.random() * cardList.size());

        return cardList.get(index);
    }

    //Return the set of pulled cards from the given pack.
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
