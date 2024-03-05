package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckValidity;

import java.util.List;

/**
 * IDeckValidator.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-04
 *
 * @PURPOSE:    validate a deck according to a ruleset
 */
public interface IDeckValidator {
    /**
     * validate a deck of cards
     * @param deck list of cards representing the deck
     * @return deck validity object
     */
    DeckValidity validate(List<Card> deck);
}
