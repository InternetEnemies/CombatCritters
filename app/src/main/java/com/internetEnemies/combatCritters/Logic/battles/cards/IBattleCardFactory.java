package com.internetEnemies.combatCritters.Logic.battles.cards;

import com.internetEnemies.combatCritters.objects.CritterCard;

/**
 * IBattleCardProvider.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-27
 *
 * @PURPOSE:    provide IBattleCards from cards
 */
public interface IBattleCardFactory {

    /**
     * get a battle card from a critter card
     * @param card critter card to convert
     * @return new BattleCard instance for this card
     */
    IBattleCard getCard(CritterCard card);
}
