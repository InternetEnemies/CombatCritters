package com.internetEnemies.combatCritters.Logic.battles;

import com.internetEnemies.combatCritters.Logic.battles.events.IBattleEndedHandler;
import com.internetEnemies.combatCritters.Logic.battles.matchmaking.IPlayer;
import com.internetEnemies.combatCritters.Logic.battles.player.IBattlePlayer;

/**
 * BattlePvP.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     12/4/24
 *
 * @PURPOSE:    pvp battle handler
 */
public class BattlePvP implements IBattlePvP {
    private static final int START_HAND = 3;
    private static final int ENERGY_PER_TURN = 1;
    private final IBattlePlayer player1;
    private final IBattlePlayer player2;
    private final IBattleEndedHandler battleEndedHandler;
    
    private boolean battleEnded;

    public BattlePvP(IBattlePlayer player1, IBattlePlayer player2, IBattleEndedHandler battleEndedHandler) {
        this.player1 = player1;
        this.player2 = player2;
        this.battleEndedHandler = battleEndedHandler;

        this.player1.setOpponent(player2);
        this.player2.setOpponent(player1);

        battleEnded = false;
        initObserver();
        
        this.player1.getHand().pullCards(START_HAND);
        this.player2.getHand().pullCards(START_HAND);
        
        this.player2.startTurn();
        this.player1.startTurn();
    }

    private void initObserver() {
        this.player1.getTurn().getEventHost().subscribe(e -> {
            if(!e.isTurn()) {
                handleTurnEnd();
            }
        });
        this.player2.getTurn().getEventHost().subscribe(e -> {
            if(!e.isTurn()) {
                handleTurnEnd();
            }
        });
    }


    @Override
    public IBattlePlayer getPlayer1() {
        return player1;
    }

    @Override
    public IBattlePlayer getPlayer2() {
        return player2;
    }
    
    private synchronized void handleTurnEnd() {
        if(!this.player1.getTurn().isTurn() && !this.player2.getTurn().isTurn()) {// both players have ended their turn
            runTurn();
        }
    }
    
    private synchronized void runTurn(){
        // get damage
        int[] p1Damage = this.player1.getDamage();
        int[] p2Damage = this.player2.getDamage();
        // forward damage
        this.player2.dealDamage(p1Damage);
        this.player1.dealDamage(p2Damage);
        // check win condition
        if(isGameOver()) {
            processGameEnd();
        } else {
            // advance buffers
            this.player1.advanceBuffer();
            this.player2.advanceBuffer();
            // update buffers
            //? this will update the buffers to show any cards that are queued up to be played
            this.player1.updateOpponentBuffer();
            this.player2.updateOpponentBuffer();
            
            // reset turn state
            //! possible race condition, fix if it's a problem
            player1.startTurn();
            player2.startTurn();
            //do per turn changes
            doTurnUpdates();
        }
    }
    
    void doTurnUpdates(){
        // pul cards
        player1.getHand().pullCard();
        player2.getHand().pullCard();
        // update energy
        player1.getEnergy().addEnergy(ENERGY_PER_TURN);
        player2.getEnergy().addEnergy(ENERGY_PER_TURN);
    }
    
    private boolean isGameOver() {
        int p1 = player1.getHealth().getHealth();
        int p2 = player2.getHealth().getHealth();
        return p1 <= 0 || p2 <= 0;
    }
    
    private void processGameEnd() {
        int p1 = player1.getHealth().getHealth();
        int p2 = player2.getHealth().getHealth();
        IPlayer winner;
        if (p1 == p2) {// tie
            winner = null;
        } else {
            winner = p1>p2 ? player1.getPlayer() : player2.getPlayer(); // player with lower health loses
        }
        battleEnded = true;
        this.battleEndedHandler.gameEnded(winner);
    }

    public boolean isBattleEnded() {
        return battleEnded;
    }
}
