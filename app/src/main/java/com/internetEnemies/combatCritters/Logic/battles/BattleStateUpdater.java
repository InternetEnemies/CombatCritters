package com.internetEnemies.combatCritters.Logic.battles;

import com.internetEnemies.combatCritters.Logic.battles.events.CardEvent;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoard;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.List;

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
        eventSystem.getOnCardKilled().subscribe(this::handleCardKilled);
        this.battle.getEnergy().getEvent().subscribe(uiProvider::setEnergy);
    }

    private void handlePlayCard(CardEvent event) {
        refreshBoard();
    }
    private void handleCardKilled(CardEvent event) {
        refreshBoard();
    }

    /**
     * refresh all rows on the board
     */
    private void refreshBoard() {
        IBoard board = this.battle.getBoard();
        this.uiProvider.setBufferCards(board.getBuffer().getCardStateList());
        this.uiProvider.setEnemyCards(board.getEnemy().getCardStateList());
        this.uiProvider.setPlayerCards(board.getPlayer().getCardStateList());
    }

    /**
     * send an updated hand to the ui
     */
    public void updateHand(List<Card> hand) {
        this.uiProvider.setHand(hand);
    }

    /**
     * set the player turn state
     * @param isPlayer is it the players turn?
     */
    public void updateTurn(boolean isPlayer) {
        this.uiProvider.setPlayerTurn(isPlayer);
    }

    /**
     * set the size of the drawstack
     * @param size size of the stack
     */
    public void updatePullStack(int size) {
        this.uiProvider.setDrawPileSize(size);
    }
}
