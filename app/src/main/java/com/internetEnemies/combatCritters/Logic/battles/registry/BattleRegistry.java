package com.internetEnemies.combatCritters.Logic.battles.registry;

import com.internetEnemies.combatCritters.Logic.battles.Battle;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCard;
import com.internetEnemies.combatCritters.Logic.battles.events.EventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Board;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Energy;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Health;
import com.internetEnemies.combatCritters.data.BattleInfoDatabase;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.hsqldb.BattleInfoRegistryHSQLDB;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.battles.Opponent;

import java.util.ArrayList;
import java.util.List;

/**
 * BattleRegistry.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-23
 *
 * @PURPOSE: provide battles from the set of battles
 */
public class BattleRegistry implements IBattleRegistry {
    private IRegistry<Opponent> opponentDB;

    public BattleRegistry(){
        opponentDB = BattleInfoDatabase.getInstance().getOpponentDB();
    }

    public BattleRegistry(IRegistry<Opponent> opponentDB){
        this.opponentDB = opponentDB;
    }
    @Override
    public Battle getBattle(IBattleStateObserver uiProvider, int id, List<Card> deck) {
        //todo actually implement this

        IEventSystem eventSystem = new EventSystem();
        CritterCard card = new CritterCard(
                0,
                "TestName",
                "",
                1,
                Card.Rarity.COMMON,
                10,
                10,
                new ArrayList<>()
        );
        BattleCard battleCard = new BattleCard(eventSystem,card);
        BattleCard[] cards = new BattleCard[]{
                null,
                battleCard,
                battleCard,
                null,
                battleCard
        };
        Board board = new Board(
                eventSystem,
                5,
                cards,
                cards,
                cards
        );
        return new Battle(eventSystem,uiProvider, deck, new Health(25, 25), new Health(25,25), new Energy(5,1), board);
    }

    @Override
    public List<Opponent> getBattles() {
        return opponentDB.getAll();
    }
}
