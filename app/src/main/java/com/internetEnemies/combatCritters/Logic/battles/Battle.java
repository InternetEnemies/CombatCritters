package com.internetEnemies.combatCritters.Logic.battles;

import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.cards.PlayCardVisitor;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IVoidEventListener;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleInputException;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleRuntimeException;
import com.internetEnemies.combatCritters.Logic.battles.opponents.IBattleOpponent;
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
    private static final int ENERGY_PER_TURN = 1;
    public static final String BATTLE_LOG = "BattleLog"; // string for logging


    private final IEventSystem eventSystem;
    private final IBattleCardFactory cardFactory;
    private final IBattleOpponent opponent;
    private final List<Card> hand;
    private final Queue<Card> pullStack;
    private final IHealth healthEnemy;
    private final IHealth healthPlayer;
    private final IEnergy energy;
    private final IBoard board;

    private final IBattleStateObserver uiProvider;
    private final BattleStateUpdater uiUpdater;

    private final IVoidEventListener onWin;
    private final IVoidEventListener onLoss;

    private boolean isPlayerTurn;

    public Battle(IEventSystem eventSystem, IBattleStateObserver uiProvider, IBattleCardFactory cardFactory, IBattleOpponent opponent, List<Card> deck, IEnergy energy, IBoard board, IVoidEventListener onWin, IVoidEventListener onLoss) {
        this.eventSystem = eventSystem;
        this.cardFactory = cardFactory;
        this.opponent = opponent;

        this.hand = new ArrayList<>();
        this.energy = energy;
        this.board = board;
        this.healthEnemy = board.getEnemy().getHealth();
        this.healthPlayer = board.getPlayer().getHealth();

        this.uiProvider = uiProvider;
        this.uiUpdater = new BattleStateUpdater(this.eventSystem,this, this.uiProvider);

        this.pullStack = initPullStack(deck);

        this.onLoss = onLoss;
        this.onWin = onWin;

        this.isPlayerTurn = true;

        // setup game end listeners
        this.healthPlayer.getChangeEvent().subscribe(this::handlePlayerHealth);
        this.healthEnemy.getChangeEvent().subscribe(this::handleEnemyHealth);

        // setup
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
     * handler for when the enemy is changed
     * @param health new health of the enemy
     */
    private void handleEnemyHealth(int health) {
        if (health <= 0) {
            setTurn(false);
            this.onWin.execute();
        }
    }

    /**
     * handle when the player's health changes
     * @param health new health of the player
     */
    private void handlePlayerHealth(int health){
        if (health <= 0) {
            setTurn(false);
            this.onLoss.execute();
        }
    }

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

    /**
     * run one turn of the game
     */
    private void runTurn() {
        //Player
        this.board.getPlayer().runAttackPhase();

        //enemy
        board.advanceBuffer();
        try {
            this.opponent.play(this.board);
        } catch (BattleException e) {
            throw new BattleRuntimeException("The opponent failed to run their turn");
            //oh no
        }
        board.getEnemy().runAttackPhase();

        //end enemy turn
        this.setTurn(true);
        this.pullCard();
        this.energy.addEnergy(ENERGY_PER_TURN);
    }



    // * IBattleOrchestrator (UI related Methods)

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
        this.setTurn(false);
        runTurn();
    }

    @Override
    public void playCard(int pos, Card card) throws BattleInputException {
        if (!this.isPlayerTurn){
            throw new BattleInputException("You can't play a card during the opponents turn");
        }
        if (!this.hand.contains(card)) {
            throw new BattleInputException("Card not in hand");
        }
        if (!this.hasEnergy(card)) {
            throw new BattleInputException("Not enough energy to play the card");
        }
        Logger.getLogger(BATTLE_LOG).log(Level.INFO, String.format("playing card: \n\t%d\n\t%s\n",pos,card.toString()));

        PlayCardVisitor visitor = new PlayCardVisitor(this.cardFactory, pos, this);
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

    /**
     * set whether it is the players turn
     */
    private void setTurn(boolean isPlayerTurn) {
        this.isPlayerTurn = isPlayerTurn;
        uiUpdater.updateTurn(this.isPlayerTurn);
    }
}
