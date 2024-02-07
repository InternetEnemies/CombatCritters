package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckValidity;

import java.util.ArrayList;
import java.util.List;

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
    public static final String STR_LIMIT_ITEM = "Too many items in the deck"; // keep the meme alive
    public static DeckValidity validateDeck(List<Card> deck) {
        List<String> issues = new ArrayList<>();
        // check total cards
        testTotalCards(deck, issues);

        // check deck limits
        // check rarity limits
        // check items

        return new DeckValidity(issues.isEmpty(), issues);
    }

    private static void testTotalCards(List<Card> deck, List<String> issues) {
        if (deck.size() > MAX_CARDS) {
            issues.add(STR_MAX_CARDS);
        }
        if (deck.size() < MIN_CARDS) {
            issues.add(STR_MIN_CARDS);
        }
    }

    private static void testDeckLimits(List<Card> deck, List<String> issues) {
    }
}
