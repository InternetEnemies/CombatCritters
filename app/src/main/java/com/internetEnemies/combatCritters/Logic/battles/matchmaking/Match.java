package com.internetEnemies.combatCritters.Logic.battles.matchmaking;


import com.internetEnemies.combatCritters.Logic.battles.BattlePvP;
import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.events.EventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.player.BattlePlayer;
import com.internetEnemies.combatCritters.Logic.battles.player.IBattlePlayer;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.*;

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
    private static final int ROW_SIZE = 5;
    private static final int HEALTH = 25;
    private static final int ENERGY_MAX = 5;
    private static final int ENERGY_INIT = 3;

    private final IPlayer player1;
    private final IPlayer player2;
    private BattlePvP battle;
    public Match(IPlayer player1, IPlayer player2) {
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
        
        battle = new BattlePvP(battlePlayer1, battlePlayer2);
    }
    
    private IBattlePlayer getBattlePlayer(IPlayer player, IEventSystem eventSystem) {
        IHealth health = new Health(HEALTH,HEALTH);
        IBoardRow play = new BoardRow(eventSystem,health, ROW_SIZE,new IBattleCard[ROW_SIZE]);
        IBoardRow buffer = new BoardRow(eventSystem,health, ROW_SIZE,new IBattleCard[ROW_SIZE]);
        return new BattlePlayer(health,new Energy(ENERGY_MAX,ENERGY_INIT),new Turn(),new Hand(player1.getDeck()),play,buffer,ROW_SIZE,player, new BattleCardFactory(eventSystem),eventSystem);
    }

    @Override
    public List<IPlayer> getPlayers() {
        return List.of(player1,player2);
    }

    @Override
    public void endMatch(IPlayer winner) {
        //todo rewards
        if (winner != null) {
            boolean p1Win = winner.getUser().getId() == player1.getUser().getId();
            player1.getMatchStateObserver().matchEnded(p1Win ? MatchEndType.WIN: MatchEndType.LOSS);
            player2.getMatchStateObserver().matchEnded(!p1Win ? MatchEndType.WIN: MatchEndType.LOSS);
        } else {
            player1.getMatchStateObserver().matchEnded(MatchEndType.PLAYER_LEFT);
            player2.getMatchStateObserver().matchEnded(MatchEndType.PLAYER_LEFT);
        }
    }
}
