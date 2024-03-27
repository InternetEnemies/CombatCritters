package com.internetEnemies.combatCritters.Logic.battles;

import com.internetEnemies.combatCritters.Logic.battles.cards.PlayCardVisitor;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleInputException;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoard;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRow;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IEnergy;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IHealth;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public static final String BATTLE_LOG = "BattleLog"; // string for logging


    private final IEventSystem eventSystem;
    private final List<Card> hand;
    private final Queue<Card> pullStack;
    private final IHealth healthEnemy;
    private final IHealth healthPlayer;
    private final IEnergy energy;
    private final IBoard board;

    private final IBattleStateObserver uiProvider;
    private final BattleStateUpdater uiUpdater;

    private boolean isPlayerTurn;

    public Battle(IEventSystem eventSystem,IBattleStateObserver uiProvider, List<Card> deck, IHealth enemy, IHealth player, IEnergy energy, IBoard board) {
        this.eventSystem = eventSystem;

        this.hand = new ArrayList<>();
        this.healthEnemy = enemy;
        this.healthPlayer = player;
        this.energy = energy;
        this.board = board;

        this.uiProvider = uiProvider;
        this.uiUpdater = new BattleStateUpdater(this.eventSystem,this, this.uiProvider);

        this.pullStack = initPullStack(deck);

        this.isPlayerTurn = true;

        this.uiUpdater.init();
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
            uiUpdater.updateHand(this.hand);
            uiUpdater.updatePullStack(pullStack.size());
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

        uiProvider.setBufferCards(board.getBuffer().getCardStateList());
        uiProvider.setEnemyCards(board.getEnemy().getCardStateList());
        uiProvider.setPlayerCards(board.getPlayer().getCardStateList());
        uiProvider.setDrawPileSize(pullStack.size());
    }

    @Override
    public void endTurn() throws BattleInputException {
        Logger.getLogger(BATTLE_LOG).log(Level.INFO, "Ending Turn");
        if (!this.isPlayerTurn){
            throw new BattleInputException("You can't end the opponents turn");
        }
        //todo
        this.isPlayerTurn = false;
        uiUpdater.updateTurn(this.isPlayerTurn);
    }

    @Override
    public void playCard(int pos, Card card) throws BattleInputException {
        Logger.getLogger(BATTLE_LOG).log(Level.INFO, String.format("playing card: \n\t%d\n\t%s\n",pos,card.toString()));
        if (!this.isPlayerTurn){
            throw new BattleInputException("You can't play a card during the opponents turn");
        }
        if (!this.hand.contains(card)) {
            throw new BattleInputException("Card not in hand");
        }
        if (!this.hasEnergy(card)) {
            throw new BattleInputException("Not enough energy to play the card");
        }

        PlayCardVisitor visitor = new PlayCardVisitor(this.eventSystem, pos, this);
        card.accept(visitor);
        try {
            visitor.execute();
            this.hand.remove(card);
            this.useEnergy(card);
            uiUpdater.updateHand(this.hand);
        } catch (BattleException e) {
            //todo catch more specific exceptions here
            throw new BattleInputException("Error When Playing Card");
        }
    }

    @Override
    public void sacrifice(int pos) throws BattleInputException {
        Logger.getLogger(BATTLE_LOG).log(Level.INFO,String.format("sacrificing position: %d\n", pos));
        if (!this.isPlayerTurn){
            throw new BattleInputException("You can't sacrifice a card during the opponents turn");
        }

        IBoardRow player = this.getBoard().getPlayer();
        try {
            player.killCard(pos);
            this.getEnergy().addEnergy(1);
        } catch (BattleException e) {
            throw new BattleInputException("Cannot sacrifice that card");
        }
    }

    // * IBattle Methods

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

    @Override
    public IBoard getBoard(){
        return this.board;
    }

    // * helpers

    /**
     * check if player has enough energy to play the card
     * @param card card we want to play
     * @return true iff the player has enough energy to play the card
     */
    private boolean hasEnergy(Card card) {
        return card.getPlayCost() <= this.getEnergy().getEnergy();
    }

    /**
     * remove play cost energy from the pool
     * @param card card to get the playcost from
     */
    private void useEnergy(Card card) {
        this.getEnergy().removeEnergy(card.getPlayCost());
    }
}
