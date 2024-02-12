package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * class that builds filters for CardSearchStub
 * </p>
 * <p>
 * Note that the implementations here are not the greatest in terms of efficiency, but I feel it is
 * fine since we expect that this will only be used in testing and the real implementation will use
 * sql and thus not have to filter lists/maps directly
 * </p>
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
    void filter(Map<Card,Integer> cards){
        filterRarity(cards);
        if(this.onlyOwned) filterOwned(cards);
        if(this.filterCost) filterCost(cards);
    }

    // # Helpers

    private void filterCost(Map<Card, Integer> cards) {
        if (this.isLess) {
            cards.entrySet().removeIf(e -> e.getKey().getPlayCost() <= this.criticalCost);
        } else {
            cards.entrySet().removeIf(e -> e.getKey().getPlayCost() >= this.criticalCost);
        }
    }

    private void filterOwned(Map<Card, Integer> cards) {
        Set<Card> ownedCards = ownedInventory.getCards().keySet();
        cards.entrySet().removeIf(e -> !ownedCards.contains(e.getKey()));
    }

    private void filterRarity(Map<Card, Integer> cards) {
        if (this.whitelistRarity){
            cards.entrySet().removeIf(e -> !rarityContains(e));
        } else {
            cards.entrySet().removeIf(this::rarityContains);
        }
    }

    /**
     * helper function for filterRarity
     * @param entry card entry to check
     * @return true iff the rarity associated with entry is in the rarities list
     */
    private boolean rarityContains(Map.Entry<Card,Integer> entry){
        return this.rarities.contains(entry.getKey().getRarity());
    }

}
