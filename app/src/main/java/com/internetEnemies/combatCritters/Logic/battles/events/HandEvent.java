package com.internetEnemies.combatCritters.Logic.battles.events;

import com.internetEnemies.combatCritters.objects.Card;

import java.util.List;

/**
 * HandEvent.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     12/4/24
 *
 * @PURPOSE:    event data for hand changes
 */
public record HandEvent(int pullStack, List<Card> hand) {
}
