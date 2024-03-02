package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CardFilterBuilderStub.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-02
 *
 * @PURPOSE:    build filters for CardSearchStub
 *
 * @NOTE the implementations here are not the greatest in terms of efficiency, but I feel it is
 * fine since we expect that this will only be used in testing and the real implementation will use
 * sql and thus not have to filter lists/maps directly
 */
public class CardFilterBuilderStub implements ICardFilterBuilder {
    private boolean whitelistRarity;
    private final List<Card.Rarity> rarities;

    private boolean onlyOwned;

    private boolean filterCost; // we don't currently have negative card cost but this allows support for that
    private int criticalCost;
    private boolean isLess;

    private final ICardInventory ownedInventory;


    public CardFilterBuilderStub(ICardInventory owned){
        this.whitelistRarity = false;
        this.rarities = new ArrayList<>();

        this.onlyOwned = false;

        this.filterCost = false;

        this.ownedInventory = owned;
    }

    @Override
    public void setRarityWhitelist() {
        this.whitelistRarity = true;
    }

    @Override
    public void addRarity(Card.Rarity rarity) {
        assert rarity != null;
        this.rarities.add(rarity);
    }

    @Override
    public void setOwned() {
        this.onlyOwned = true;
    }

    @Override
    public void setCost(int cost, boolean less) {
        this.filterCost = true;
        this.criticalCost = cost;
        this.isLess = less;
    }

    /**
     * filter the map of cards
     */
    void filter(List<ItemStack<Card>> cards){
        filterRarity(cards);
        if(this.onlyOwned) filterOwned(cards);
        if(this.filterCost) filterCost(cards);
    }

    // # Helpers

    /**
     *  filter cards by play cost
     */
    private void filterCost(List<ItemStack<Card>> cards) {
        if (this.isLess) {
            cards.removeIf(e -> e.getItem().getPlayCost() <= this.criticalCost);
        } else {
            cards.removeIf(e -> e.getItem().getPlayCost() >= this.criticalCost);
        }
    }

    /**
     * filter cards by owned
     */
    private void filterOwned(List<ItemStack<Card>> cards) {
        List<Card> ownedCards = ownedInventory.getCards().stream()
                .map(ItemStack::getItem)// convert the stream of ItemStacks to a Stream of Cards
                .collect(Collectors.toList()); // output to a List of Card
        cards.removeIf(e -> !ownedCards.contains(e.getItem())); // filter to cards in the owned cards list
    }

    /**
     *  filter cards by rarity
     */
    private void filterRarity(List<ItemStack<Card>> cards) {
        if (this.whitelistRarity){
            cards.removeIf(e -> !rarityContains(e.getItem()));
        } else {
            cards.removeIf(e -> rarityContains(e.getItem()));
        }
    }

    /**
     * helper function for filterRarity
     * @param  card card to check
     * @return true iff the rarity associated with card is in the rarities list
     */
    private boolean rarityContains(Card card){
        return this.rarities.contains(card.getRarity());
    }

}
