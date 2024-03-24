package com.internetEnemies.combatCritters.Logic.battles;

import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCard;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoard;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IEnergy;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IHealth;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Battle.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-23
 *
 * @PURPOSE:    Handle battle state machine and trigger events on changes to battle state
 */
public class Battle implements IBattleOrchestrator, IBattle{
    private static final int INIT_HAND_SIZE = 3;


    private final List<Card> hand;
    private final Queue<Card> pullStack;
    private final IHealth healthEnemy;
    private final IHealth healthPlayer;
    private final IEnergy energy;
    private final IBoard board;

    private final IBattleStateObserver uiProvider;

    public Battle(IBattleStateObserver uiProvider, List<Card> deck, IHealth enemy, IHealth player, IEnergy energy, IBoard board) {
        //todo
        this.hand = new ArrayList<>();
        this.healthEnemy = enemy;
        this.healthPlayer = player;
        this.energy = energy;
        this.board = board;

        this.uiProvider = uiProvider;

        this.pullStack = initPullStack(deck);
        initGame();
        initializeUI();
    }

    /**
     * initialize the game
     */
    private void initGame() {
        pullCard(INIT_HAND_SIZE);
    }

    //* Game Methods

    /**
     * pull cards from the deck into the hand
     * @param amount number of cards to pull
     */
    private void pullCard(int amount) {
        for (int i = 0; i < amount; i++) {
            pullCard();
        }
    }

    /**
     * pull a card from the deck and add to the hand
     */
    private void pullCard() {
        if (!this.pullStack.isEmpty()) {
            Card card = pullStack.remove();
            this.hand.add(card);
        }
    }

    /**
     * initialize the pull stack
     */
    private Queue<Card> initPullStack(List<Card> deck) {
        LinkedList<Card> pullList = new LinkedList<>(deck);
        Collections.shuffle(pullList);
        return pullList;
    }



    // * IBattleOrchestrator (UI related Methods)
    //todo should these be moved into a child class?

    /**
     * initializes the state of battle into the observer
     */
    private void initializeUI() {
        uiProvider.setHand(hand);
        uiProvider.setPlayerHealth(healthPlayer.getHealth());
        uiProvider.setEnemyHealth(healthEnemy.getHealth());
        uiProvider.setEnergy(energy.getEnergy());

        uiProvider.setBufferCards(board.getBuffer());
        uiProvider.setEnemyCards(board.getEnemy());
        uiProvider.setPlayerCards(board.getPlayer());

    }

    @Override
    public void endTurn() {
        //todo
        System.out.println("endTurn called");
    }

    @Override
    public void playCard(int pos, Card card) {
        //todo
        System.out.printf("playCard called with: \n\t%d\n\t%s\n",pos,card.toString());
    }

    @Override
    public void sacrifice(int pos) {
        //todo
        System.out.printf("sacrifice called with: %d\n", pos);
    }

    // * IBattle Methods
    @Override
    public boolean playCardRaw(int pos, int row, BattleCard card, boolean force) {
        return false;
    }

    @Override
    public void damagePosition(int pos, int row) {

    }

    @Override
    public void healPosition(int pos, int row) {

    }

    @Override
    public IEnergy getEnergy() {
        return this.energy;
    }

    @Override
    public IHealth getPlayerHealth() {
        return this.healthPlayer;
    }

    @Override
    public IHealth getEnemyHealth() {
        return this.healthEnemy;
    }
}
