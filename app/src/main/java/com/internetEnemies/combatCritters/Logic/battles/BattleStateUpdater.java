package com.internetEnemies.combatCritters.Logic.battles;

import com.internetEnemies.combatCritters.Logic.battles.events.CardEvent;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoard;

/**
 * BattleUIUpdater.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/26/24
 *
 * @PURPOSE:    setup events for the ui (this might qualify as a ui bridge)
 */
public class BattleStateUpdater {
    private final IEventSystem eventSystem;
    private final IBattleStateObserver uiProvider;
    private final IBattle battle;
    public BattleStateUpdater(IEventSystem eventSystem, IBattle battle, IBattleStateObserver uiProvider) {
        this.eventSystem = eventSystem;
        this.uiProvider = uiProvider;
        this.battle = battle;
    }

    /**
     * initialize the BattleStateUpdater (connect the IBattleStateObserver to events provided by the event system)
     */
    public void init() {
        eventSystem.getOnPlayCard().subscribe(this::handlePlayCard);
    }

    private void handlePlayCard(CardEvent event) {
        IBoard board = this.battle.getBoard();
        this.uiProvider.setBufferCards(board.getBuffer().getCardStateList());
        this.uiProvider.setEnemyCards(board.getEnemy().getCardStateList());
        this.uiProvider.setPlayerCards(board.getPlayer().getCardStateList());
    }
}
