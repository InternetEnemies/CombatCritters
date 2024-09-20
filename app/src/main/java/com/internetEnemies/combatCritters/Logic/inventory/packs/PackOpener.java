/**
 * PackOpener.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-01
 *
 * @PURPOSE:    implementation of IPackOpener
 */

package com.internetEnemies.combatCritters.Logic.inventory.packs;

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardSlot;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Random;

public class PackOpener implements IPackOpener {
    private final ICardInventory cardInventory;
    public PackOpener() {
        this(Database.getInstance().getCardInventory());
    }

    public PackOpener(ICardInventory cardInventory){
        this.cardInventory = cardInventory;
    }

    /**
     * Get the rarity of a card on the pack
     * @param slot the card
     * @return the rarity of the card
     */
    public Card.Rarity randomByRarity(CardSlot slot){
        Card.Rarity rarity;
        NavigableMap<Double,Card.Rarity> slotChances = slot.getCardPullChances();

        double maxRarityValue = Objects.requireNonNull(slotChances.lastEntry()).getKey();

        Random rnd = new Random();
        double randomValue = (maxRarityValue) * rnd.nextDouble();

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

    @Override
    public List<Card> openPack(Pack pack) {
        List<Card> cards = pullCards(pack);
        cardInventory.addCards(cards);
        return cards;
    }

}
