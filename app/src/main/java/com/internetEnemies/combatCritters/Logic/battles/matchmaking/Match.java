package com.internetEnemies.combatCritters.Logic.battles.matchmaking;


import com.internetEnemies.combatCritters.Logic.battles.IBattleOrchestrator;
import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.events.EventSystem;
import com.internetEnemies.combatCritters.Logic.battles.registry.battleProviders.SingleSlotProvider;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.BoardFactory;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.BoardRowFactory;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;

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
    public Match(IPlayer player1, IPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1.getMatchStateObserver().matchFound(player2);
        player2.getMatchStateObserver().matchFound(player1);
        startMatch();
    }

    private void startMatch(){//todo do this correctly
        EventSystem eventSystem = new EventSystem();
        BattleCardFactory cardFactory = new BattleCardFactory(eventSystem);
        Card card = new CritterCard(1, "Card", "card_1.jpeg",1, Card.Rarity.COMMON,1,1,List.of());
        IBattleOrchestrator battle = new SingleSlotProvider(new IRegistry<Card>() {//totaly fine dont worry about it
            @Override
            public Card getSingle(int id) {
                return new CritterCard(1, "Card", "card_1.jpeg",1, Card.Rarity.COMMON,1,1,List.of());
            }

            @Override
            public List<Card> getListOf(List<Integer> ids) {
                return List.of(getSingle(1));
            }

            @Override
            public List<Card> getAll() {
                return List.of(getSingle(1));
            }

            @Override
            public Card add(Card obj) {
                return getSingle(1);
            }
        }, cardFactory, 1, new Integer[]{1, 2, 3}, HEALTH, ENERGY_INIT, ENERGY_MAX, ROW_SIZE).get(eventSystem,new BoardRowFactory(),new BoardFactory(), player1.getStateObserver(),player1.getDeck(),
                ()-> System.out.println("ENDED"), ()-> System.out.println("ENDED"));
        player1.setOrchestrator(battle);
        player2.setOrchestrator(battle);
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
