package com.internetEnemies.combatCritters.Logic.inventory.decks;

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckValidity;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * DeckValidator.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/5/24
 *
 * @PURPOSE:    check whether a deck is valid and provide feedback for invalid decks
 */
public class DeckValidator implements IDeckValidator{
    //! These constraints should conform to what is outlined in documentation.md#Rules
    public static final int MIN_CARDS = 20;
    public static final int MAX_CARDS = 50;
    public static final int LIMIT_LEGEND = 5;
    public static final int LIMIT_EPIC = 10;
    public static final int LIMIT_RARE = 15;
    public static final int LIMIT_ITEM = MIN_CARDS; // keep the meme alive
    public static final String STR_MIN_CARDS = "Not enough cards in the deck";
    public static final String STR_MAX_CARDS = "Too many cards in the deck";
    public static final String STR_LIMIT_LEGEND = "Too many legendary cards in the deck";
    public static final String STR_LIMIT_EPIC = "Too many epic cards in the deck";
    public static final String STR_LIMIT_RARE = "Too many rare cards in the deck";
    public static final String STR_LIMIT_ITEM = "Too many items in the deck";

    private static final String STR_OWNED = "You own %d %s cards. (Deck uses %d)";


    private final ICardInventory inventory;
    private final List<String> issues;

    public DeckValidator(){
        this(Database.getInstance().getCardInventory());
    }

    public DeckValidator(ICardInventory inventory) {
        this.inventory = inventory;
        this.issues = new ArrayList<>();
    }



    /**
     * get an object describing whether the deck is valid and some string related to why it is invalid
     * @param deck list of cards to check validity
     * @return DeckValidity object describing validity
     */
    @Override
    public DeckValidity validate(IDeck deck) {
        issues.clear();
        List<Card> cards = deck.getCards();
        // check total cards
        checkTotalCards(cards);
        // check rarity limits
        checkRarity(cards);
        // check items
        checkItemCount(cards);
        // check cards owned
        checkOwnership(deck.countCards());

        return new DeckValidity(issues.isEmpty(), issues);
    }

    /**
     * check that the amount of items is within game rules
     * @param deck list of cards to check
     */
    private void checkItemCount(List<Card> deck) {
        int items = 0;
        for(Card card : deck) {
            if(card instanceof ItemCard) {
                items ++;
            }
        }
        if (items > LIMIT_ITEM) {
            issues.add(STR_LIMIT_ITEM);
        }
    }

    /**
     * check that the amount of cards of rarities are within the rules
     * @param deck deck to test
     */
    private void checkRarity(List<Card> deck) {
        int[] counts = new int[Card.Rarity.values().length];
        for(Card card : deck) {
            counts[card.getRarity().ordinal()]++;
        }
        if (counts[Card.Rarity.LEGENDARY.ordinal()] > LIMIT_LEGEND) {
            issues.add(STR_LIMIT_LEGEND);
        }
        if (counts[Card.Rarity.EPIC.ordinal()] > LIMIT_EPIC) {
            issues.add(STR_LIMIT_EPIC);
        }
        if (counts[Card.Rarity.RARE.ordinal()] > LIMIT_RARE) {
            issues.add(STR_LIMIT_RARE);
        }
    }

    /**
     * check that the total number of cards is within the rules
     * @param deck deck to ceck
     */
    private void checkTotalCards(List<Card> deck) {
        if (deck.size() > MAX_CARDS) {
            issues.add(STR_MAX_CARDS);
        }
        if (deck.size() < MIN_CARDS) {
            issues.add(STR_MIN_CARDS);
        }
    }

    /**
     * check if all the cards in the deck are owned
     * @param deck list of cardstacks from the deck
     */
    private void checkOwnership(List<ItemStack<Card>> deck) {
        for(ItemStack<Card> stack : deck) {
            int owned = this.inventory.getCardAmount(stack.getItem());
            int used = stack.getAmount();
            if (used > owned) {
                this.issues.add(String.format(Locale.CANADA,STR_OWNED, owned, stack.getItem().getName(),used));
            }
        }
    }
}
