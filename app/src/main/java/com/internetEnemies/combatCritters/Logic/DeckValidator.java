package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckValidity;
import com.internetEnemies.combatCritters.objects.ItemCard;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains static methods for validating decks (lists of cards) to the rules of the game
 */
public class DeckValidator {
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

    /**
     * get an object describing whether the deck is valid and some string related to why it is invalid
     * @param deck list of cards to check validity
     * @return DeckValidity object describing validity
     */
    public static DeckValidity validateDeck(List<Card> deck) {
        List<String> issues = new ArrayList<>();
        // check total cards
        checkTotalCards(deck, issues);
        // check rarity limits
        checkRarity(deck, issues);
        // check items
        checkItemCount(deck,issues);

        return new DeckValidity(issues.isEmpty(), issues);
    }

    private static void checkItemCount(List<Card> deck, List<String> issues) {
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

    private static void checkRarity(List<Card> deck, List<String> issues) {
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

    private static void checkTotalCards(List<Card> deck, List<String> issues) {
        if (deck.size() > MAX_CARDS) {
            issues.add(STR_MAX_CARDS);
        }
        if (deck.size() < MIN_CARDS) {
            issues.add(STR_MIN_CARDS);
        }
    }
}
