package com.internetEnemies.combatCritters.Logic.battles.matchmaking;


import com.internetEnemies.combatCritters.Logic.battles.BattlePvP;
import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.events.EventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.player.BattlePlayer;
import com.internetEnemies.combatCritters.Logic.battles.player.IBattlePlayer;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.*;
import com.internetEnemies.combatCritters.Logic.transaction.participant.IUserParticipantFactory;
import com.internetEnemies.combatCritters.objects.Currency;

import java.util.List;

/**
 * Match.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/27/24
 *
 * @PURPOSE:    handle high level battle state
 */
public class Match implements IMatch{
    private static final int REWARDS_WIN = 100;
    private static final int REWARDS_LOSE = 10;
    private static final int REWARDS_DRAW = 30;
    private static final int REWARDS_LEAVE=  0;
    
    
    private static final int ROW_SIZE = 5;
    private static final int HEALTH = 25;
    private static final int ENERGY_MAX = 5;
    private static final int ENERGY_INIT = 3;

    private final IUserParticipantFactory userParticipantFactory;
    private final IPlayer player1;
    private final IPlayer player2;
    private BattlePvP battle;
    private IPlayer winner;
    private boolean ended = false;
    
    public Match(IUserParticipantFactory userParticipantFactory, IPlayer player1, IPlayer player2) {
        this.userParticipantFactory = userParticipantFactory;
        this.player1 = player1;
        this.player2 = player2;
        player1.getMatchStateObserver().matchFound(player2);
        player2.getMatchStateObserver().matchFound(player1);
        startMatch();
    }

    private void startMatch(){
        EventSystem eventSystem = new EventSystem();
        
        IBattlePlayer battlePlayer1 = getBattlePlayer(player1,eventSystem);
        IBattlePlayer battlePlayer2 = getBattlePlayer(player2,eventSystem);
        
        battle = new BattlePvP(battlePlayer1, battlePlayer2, this::handleGameEnded);
    }
    
    private void handleGameEnded(IPlayer winner){
        this.winner = winner;
        endMatch();
    }
    
    private IBattlePlayer getBattlePlayer(IPlayer player, IEventSystem eventSystem) {
        IHealth health = new Health(HEALTH,HEALTH);
        IBoardRow play = new BoardRow(eventSystem,health, ROW_SIZE,new IBattleCard[ROW_SIZE]);
        IBoardRow buffer = new BoardRow(eventSystem,health, ROW_SIZE,new IBattleCard[ROW_SIZE]);
        return new BattlePlayer(health,new Energy(ENERGY_MAX,ENERGY_INIT),new Turn(),new Hand(player.getDeck()),play,buffer,ROW_SIZE,player, new BattleCardFactory(eventSystem),eventSystem);
    }

    @Override
    public List<IPlayer> getPlayers() {
        return List.of(player1,player2);
    }

    @Override
    public void endMatch() {
        if(ended) return;
        ended = true;
        
        if(battle.isBattleEnded()) {
            if (winner != null) {
                handleWinLoss();
            } else { // draw
                handleSingleReward(REWARDS_DRAW, MatchEndType.DRAW);
            }
        } else { // player left
            handleSingleReward(REWARDS_LEAVE, MatchEndType.PLAYER_LEFT);
        }
        
    }

    private void handleSingleReward(int currency, MatchEndType end) {
        MatchEndBuilder builder = new MatchEndBuilder(userParticipantFactory);
        builder.addReward(new Currency(currency), 1);
        builder.setReason(end);
        builder.execute(player1);
        builder.execute(player2);
    }

    private void handleWinLoss() {
        MatchEndBuilder bWin = new MatchEndBuilder(userParticipantFactory);
        MatchEndBuilder bLoss = new MatchEndBuilder(userParticipantFactory);
        bWin.setReason(MatchEndType.WIN);
        bLoss.setReason(MatchEndType.LOSS);
        
        bWin.addReward(new Currency(REWARDS_WIN), 1);
        bLoss.addReward(new Currency(REWARDS_LOSE), 1);
        
        if(winner == player1) {
            bWin.execute(player1);
            bLoss.execute(player2);
        } else {
            bWin.execute(player2);
            bLoss.execute(player1);
        }
    }
}
