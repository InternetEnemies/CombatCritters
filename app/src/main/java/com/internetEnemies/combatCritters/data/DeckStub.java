/**
 * DeckStub.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-01
 *
 * @PURPOSE:    implementation of IDeck
 */

package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Deck database wrapper
 */
public class DeckStub implements IDeck{
    private final List<Card> cards;
    private final DeckDetails deckDetails;

    public DeckStub(DeckDetails deckDetails){
        this.deckDetails = deckDetails;
        this.cards = new ArrayList<>();
    }

    @Override
    public Card getCard(int slot) throws IndexOutOfBoundsException {
        return cards.get(slot);
    }

    @Override
    public void addCard(int slot, Card card) {
        cards.add(slot,card);
    }

    @Override
    public void removeCard(int slot) {
        cards.remove(slot);
    }

    @Override
    public int countCard(Card card) {
        // as with much of the stub I don't like this that much but whatever
        Optional<ItemStack<Card>> count = countCards().stream().filter(e -> e.getItem().equals(card)).findFirst();//Itemstack for the card
        return count.map(ItemStack::getAmount).orElse(0); // get the amount or return default 0
    }

    @Override
    public List<ItemStack<Card>> countCards() {
        //with sql this is not how this will be done

        List<ItemStack<Card>> counts = new ArrayList<>();
        cards.stream().distinct().forEach(card -> {// get distinct cards
            int count = (int)cards.stream().filter(e->e.equals(card)).count();//count each card
            counts.add(new ItemStack<>(card,count));// create ItemStack of each card
        });//this is probably horribly inefficient but this is stub so ¯\_(ツ)_/¯

        return counts;
    }

    @Override
    public DeckDetails getInfo() {
        return deckDetails;
    }

    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(new ArrayList<>(cards));
    }

    @Override
    public int getTotalCards() {
        return cards.size();
    }

    @Override
    public void setCards(List<Card> cards) {
        this.cards.clear();
        this.cards.addAll(cards);
    }
}
