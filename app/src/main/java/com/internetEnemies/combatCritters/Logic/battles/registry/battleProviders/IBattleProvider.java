package com.internetEnemies.combatCritters.Logic.battles.registry.battleProviders;

import com.internetEnemies.combatCritters.Logic.battles.IBattleOrchestrator;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IVoidEventListener;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardFactory;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRowFactory;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.List;

/**
 * IBattleProvider.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/28/24
 *
 * @PURPOSE:    provide battles
 */
public interface IBattleProvider {
    /**
     * get the battle
     * @param uiProvider ui callback set
     * @param deck deck that the player will use
     * @param onWin what to do if player wins
     * @param onLoss what to do if player loses
     * @return orchestrator interface for the battle
     */
    IBattleOrchestrator get(IEventSystem eventSystem, IBoardRowFactory rowFactory, IBoardFactory boardFactory, IBattleStateObserver uiProvider, List<Card> deck, IVoidEventListener onWin, IVoidEventListener onLoss);
}
