package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardFilter;
import com.internetEnemies.combatCritters.objects.ItemStack;

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
public class CardFilterer {

    private final CardFilter filterParams;
    private final ICardInventory ownedInventory;


    public CardFilterer(ICardInventory owned, CardFilter filter){
        this.ownedInventory = owned;
        this.filterParams = filter;
    }

    /**
     * filter the map of cards
     */
    void filter(List<ItemStack<Card>> cards){
        filterRarity(cards);
        filterOwned(cards);
        filterCost(cards);
    }

    // # Helpers

    /**
     *  filter cards by play cost
     */
    private void filterCost(List<ItemStack<Card>> cards) {
        if (this.filterParams.getCost() == null ) return; // exit early if we arent filtering by cost

        if (this.filterParams.isLessThan()) {
            cards.removeIf(e -> e.getItem().getPlayCost() <= this.filterParams.getCost());
        } else {
            cards.removeIf(e -> e.getItem().getPlayCost() >= this.filterParams.getCost());
        }
    }

    /**
     * filter cards by owned
     */
    private void filterOwned(List<ItemStack<Card>> cards) {
        if(!this.filterParams.isOwned()) return; // exit early if we arent filtering owned

        List<Card> ownedCards = ownedInventory.getCards().stream()
                .map(ItemStack::getItem)// convert the stream of ItemStacks to a Stream of Cards
                .collect(Collectors.toList()); // output to a List of Card
        cards.removeIf(e -> !ownedCards.contains(e.getItem())); // filter to cards in the owned cards list
    }

    /**
     *  filter cards by rarity
     */
    private void filterRarity(List<ItemStack<Card>> cards) {
        if (this.filterParams.isRarityWhitelist()){
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
        return this.filterParams.getRarities().contains(card.getRarity());
    }

}
