package com.internetEnemies.combatCritters.Logic.battles;

import com.internetEnemies.combatCritters.Logic.battles.registry.BattleRegistry;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.List;

public class BattleFactory implements IBattleFactory {

    private final BattleRegistry registry;
    public BattleFactory() {
        this.registry = new BattleRegistry();
    }
    @Override
    public BattleOrchestrator getBattle(IBattleStateObserver uiProvider, int battleId, List<Card> deck) {
        //todo create the battle object, this means fudging pulling from an array for the id and
        return new BattleOrchestrator(uiProvider, registry.getBattle(battleId,deck));
    }
}
