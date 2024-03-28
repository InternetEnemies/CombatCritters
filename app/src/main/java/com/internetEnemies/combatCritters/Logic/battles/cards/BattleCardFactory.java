package com.internetEnemies.combatCritters.Logic.battles.cards;

import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.objects.CritterCard;

/**
 * BattleCardFactory.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-27
 *
 * @PURPOSE:    make BattleCard instances
 */
public class BattleCardFactory implements IBattleCardFactory{
    private final IEventSystem eventSystem;
    public BattleCardFactory(IEventSystem eventSystem){
        this.eventSystem = eventSystem;
    }
    @Override
    public IBattleCard getCard(CritterCard card) {
        return new BattleCard(eventSystem, card);
    }
}
