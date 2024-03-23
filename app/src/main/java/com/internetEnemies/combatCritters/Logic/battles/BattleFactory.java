package com.internetEnemies.combatCritters.Logic.battles;

import com.internetEnemies.combatCritters.Logic.battles.registry.BattleRegistry;
import com.internetEnemies.combatCritters.Logic.battles.registry.IBattleRegistry;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.List;

/**
 * BattleFactory.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-23
 *
 * @PURPOSE:    Start battles and provide the interface (orchestrator) for playing them
 */
public class BattleFactory implements IBattleFactory {

    private final IBattleRegistry registry;

    public BattleFactory(IBattleRegistry registry){
        this.registry = registry;
    }
    public BattleFactory() {
        this(new BattleRegistry());
    }
    @Override
    public BattleOrchestrator getBattle(IBattleStateObserver uiProvider, int battleId, List<Card> deck) {
        return new BattleOrchestrator(uiProvider, registry.getBattle(battleId,deck));
    }
}
