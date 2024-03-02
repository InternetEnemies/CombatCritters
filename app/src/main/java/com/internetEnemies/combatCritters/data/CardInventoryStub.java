package com.internetEnemies.combatCritters.data;


import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * testing database for Card inventory
 */
public class CardInventoryStub implements ICardInventory{
    private final List<ItemStack<Card>> cardDB;

    public CardInventoryStub(){
        cardDB = new ArrayList<>();
    }

    @Override
    public int getCardAmount(Card card) {
        ItemStack<Card> stack = getItemStack(card);
        if (stack != null) {
            return stack.getAmount();
        } else {
            return 0;// card not found so quantity == 0
        }
    }

    @Override
    public void addCard(Card card) {
        ItemStack<Card> cardStack = getItemStack(card);
        if (cardStack != null) { // replace old item stack with new one with updated quantity
            ItemStack<Card> newStack = new ItemStack<>(card,cardStack.getAmount() + 1);
            cardDB.set(cardDB.indexOf(cardStack), newStack);
        } else { // add new itemstack
            ItemStack<Card> newStack = new ItemStack<>(card, 1);
            cardDB.add(newStack);
        }
    }

    @Override
    public void addCards(List<Card> cards) {
        for ( Card card : cards) {
            addCard(card);
        }
    }

    @Override
    public void removeCard(Card card, int amount) {
        ItemStack<Card> cardStack = getItemStack(card);
        assert cardStack != null;

        if (cardStack.getAmount() <= amount){
            // full remove card
            cardDB.remove(cardStack);
        } else {
            // partial remove
            ItemStack<Card> newCardStack = new ItemStack<>(card, cardStack.getAmount() - amount);
            cardDB.set(cardDB.indexOf(cardStack),newCardStack);
        }
    }

    @Override
    public void removeCard(Card card) {
        removeCard(card, 1);
    }

    @Override
    public List<ItemStack<Card>> getCards() {
        // instantiating a new list here prevents modifications to carddb from being passed
        // while it would be nice to pass those changes implementing that with SQL sounds rather difficult
        return Collections.unmodifiableList(new ArrayList<>(cardDB));
    }

    private ItemStack<Card> getItemStack(Card card){
        assert card != null;
        Optional<ItemStack<Card>> stack = cardDB.stream()
                .filter(e -> e.getItem().equals(card)) // we could assert that the count here is always 1 or 0
                .findFirst();

        return stack.orElse(null);
    }
}
