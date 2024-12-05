package com.internetEnemies.combatCritters.Logic.battles.player;

import com.internetEnemies.combatCritters.Logic.battles.IBattleOrchestrator;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleInputException;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleRuntimeException;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleStateException;
import com.internetEnemies.combatCritters.Logic.battles.matchmaking.IPlayer;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.*;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;

/**
 * BattlePlayer.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     12/4/24
 *
 * @PURPOSE:    Player for battles
 */
public class BattlePlayer implements IBattlePlayer, IBattleOrchestrator {

    private final IHealth health;
    private final IEnergy energy;
    private final ITurn turn;
    private final IHand hand;
    private final IBattleCardFactory battleCardFactory;
    private final IBoardRow play;
    private final IBoardRow buffer;
    private final int boardSize;
    private final IPlayer player;
    private final IEventSystem eventSystem;
    
    private IBattlePlayer opponent;

    public BattlePlayer(IHealth health,
                        IEnergy energy,
                        ITurn turn,
                        IHand hand,
                        IBoardRow play,
                        IBoardRow buffer,
                        int boardSize,
                        IPlayer player,
                        IBattleCardFactory battleCardFactory,
                        IEventSystem eventSystem) {
        this.health = health;
        this.energy = energy;
        this.turn = turn;
        this.hand = hand;
        this.play = play;
        this.buffer = buffer;
        this.boardSize = boardSize;
        this.player = player;
        this.eventSystem = eventSystem;

        player.setOrchestrator(this);

        this.battleCardFactory = battleCardFactory;

        initObserver();
    }

    private void initObserver() {
        IBattleStateObserver observer = player.getStateObserver();
        this.turn.getEventHost().subscribe(e -> observer.setPlayerTurn(e.isTurn()));
        this.health.getChangeEvent().subscribe(observer::setPlayerHealth);
        this.energy.getEvent().subscribe(observer::setPlayerEnergy);

        this.hand.getEventHost().subscribe(e -> {
            observer.setHand(e.hand());
            observer.setDrawPileSize(e.pullStack());
        });

        // this should probably be a single event but whatever
        this.eventSystem.getOnCardDamaged().subscribe(_ -> refreshBoard());
        this.eventSystem.getOnCardKilled().subscribe(_ -> refreshBoard());
        this.eventSystem.getOnCardHealed().subscribe(_ -> refreshBoard());
        this.eventSystem.getOnPlayCard().subscribe(_ -> refreshBoard());
        
        // * init values
        observer.setPlayerEnergy(energy.getEnergy());
        observer.setPlayerHealth(health.getHealth());
    }

    private void refreshBoard() {
        IBattleStateObserver observer = player.getStateObserver();
        observer.setPlayerCards(play.getCardStateList());
        observer.setPlayerBufferCards(buffer.getCardStateList());
        
        if (this.opponent == null) throw new BattleStateException("Player in bad state");
        //observer.setEnemyBufferCards(opponent.getBuffer().getCardStateList());//todo remove
        observer.setEnemyCards(opponent.getPlay().getCardStateList());
    }

    @Override
    public int[] getDamage() {
        return this.play.getDamage();
    }

    @Override
    public void dealDamage(int[] damage) {
        this.play.dealDamage(damage);
    }

    @Override
    public void advanceBuffer() {
        for (int i = 0; i < boardSize; i++) {
            IBattleCard enemyCard = play.getCard(i);
            IBattleCard bufferCard = buffer.getCard(i);
            if (enemyCard == null && bufferCard != null) {
                try {
                    buffer.transfer(play, i,i);
                } catch (BattleException e) {
                    throw new BattleRuntimeException("failed to advance buffer",e);
                    //this shouldnt happen, I dont even know how to force this to happen for a test lol
                }
            }
        }
    }

    @Override
    public void startTurn() {
        this.turn.setTurn(true);
    }

    @Override
    public IBoardRow getPlay() {
        return this.play;
    }

    @Override
    public IBoardRow getBuffer() {
        return this.buffer;
    }

    @Override
    public IHealth getHealth() {
        return this.health;
    }

    @Override
    public IEnergy getEnergy() {
        return this.energy;
    }

    @Override
    public ITurn getTurn() {
        return this.turn;
    }

    @Override
    public IHand getHand() {
        return this.hand;
    }

    @Override
    public IPlayer getPlayer() {
        return this.player;
    }

    @Override
    public void setOpponent(IBattlePlayer opponent) {
        this.opponent = opponent;
        
        //subscribe to the opponents events
        IBattleStateObserver observer = player.getStateObserver();
        opponent.getHealth().getChangeEvent().subscribe(observer::setEnemyHealth);
        opponent.getEnergy().getEvent().subscribe(observer::setEnemyEnergy);
        
        // * init values
        observer.setEnemyEnergy(opponent.getEnergy().getEnergy());
        observer.setEnemyHealth(opponent.getHealth().getHealth());
        
        refreshBoard();
        updateOpponentBuffer();
    }

    @Override
    public void updateOpponentBuffer() {
        if (this.opponent == null) throw new BattleStateException("Player in bad state");
        this.player.getStateObserver().setEnemyBufferCards(opponent.getBuffer().getCardStateList());
    }

    //* BattleOrchestrator
    @Override
    public void endTurn() throws BattleInputException {
        if (!this.turn.isTurn()){
            throw new BattleInputException("Cannot end turn that has already been ended.");
        }
        this.turn.setTurn(false);
    }

    @Override
    public void playCard(int pos, Card card) throws BattleInputException {
        if (!this.turn.isTurn()){
            throw new BattleInputException("You can't play a card when your turn is inactive.");
        }
        if (!this.hand.getCards().contains(card)) {
            throw new BattleInputException("Card not in hand");
        }
        if (this.energy.getEnergy() < card.getPlayCost()) {
            throw new BattleInputException("Not enough energy to play the card");
        }
        if(!(card instanceof CritterCard) ){
            throw new BattleInputException("Item Cards are not supported. How did you get here?");
        }

        try {
            this.buffer.playCard(pos, battleCardFactory.getCard((CritterCard) card));
            this.hand.remove(card);
            this.energy.removeEnergy(card.getPlayCost());
        } catch (BattleException e) {
            throw new BattleInputException("Invalid Play Card Action: " + e.getMessage());
        }
    }

    @Override
    public void sacrifice(int pos) throws BattleInputException {
        if (!this.turn.isTurn()){
            throw new BattleInputException("You can't sacrifice a card when your turn is inactive.");
        }

        IBoardRow row = pos/this.boardSize > 0 ? this.play : this.buffer;
        try {
            row.killCard(pos % boardSize);
            this.energy.addEnergy(1);
        } catch (BattleException e) {
            throw new BattleInputException("Cannot Sacrifice card: " + e.getMessage());
        }
    }
}
