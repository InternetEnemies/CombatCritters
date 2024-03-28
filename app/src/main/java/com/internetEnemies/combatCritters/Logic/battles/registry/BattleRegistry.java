package com.internetEnemies.combatCritters.Logic.battles.registry;

import com.internetEnemies.combatCritters.Logic.ITransactionHandler;
import com.internetEnemies.combatCritters.Logic.battles.Battle;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCard;
import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.events.EventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IVoidEventListener;
import com.internetEnemies.combatCritters.Logic.battles.opponents.SingleSlotOpponent;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Board;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Energy;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Health;
import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.battles.Opponent;
import com.internetEnemies.combatCritters.objects.battles.RewardTransaction;

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
    private final IRegistry<Opponent> opponentDB;
    private final ITransactionHandler transactionHandler;

    public BattleRegistry(ITransactionHandler transactionHandler){
        this(Database.getInstance().getOpponentDB(),transactionHandler);
        
    }

    public BattleRegistry(IRegistry<Opponent> opponentDB, ITransactionHandler transactionHandler){
        this.opponentDB = opponentDB;
        this.transactionHandler = transactionHandler;
    }
    @Override
    public Battle getBattle(IBattleStateObserver uiProvider, int id, List<Card> deck, IVoidEventListener onWin, IVoidEventListener onLoss) {
        //todo actually implement this

        IEventSystem eventSystem = new EventSystem();
        CritterCard card = new CritterCard(
                0,
                "TestName",
                "",
                1,
                Card.Rarity.COMMON,
                2,
                3,
                new ArrayList<>()
        );
        CritterCard card1 = new CritterCard(
                0,
                "TestName1",
                "",
                1,
                Card.Rarity.COMMON,
                1,
                5,
                new ArrayList<>()
        );
        CritterCard card2 = new CritterCard(
                0,
                "TestName2",
                "",
                1,
                Card.Rarity.COMMON,
                2,
                2,
                new ArrayList<>()
        );
        BattleCard[] cards = new BattleCard[5];
        BattleCard[] cards1 = new BattleCard[]{
                null,
                new BattleCard(eventSystem, card1),
                null,
                null,
                null
        };
        BattleCard[] cards2 = new BattleCard[5];
        Board board = new Board(
                eventSystem,
                new Health(25,25),
                new Health(25,25),
                5,
                cards,
                cards1,
                cards2
        );
        List<CritterCard> opponentDeck = new ArrayList<>();
        opponentDeck.add(card1);
        opponentDeck.add(card2);
        opponentDeck.add(card1);
        IBattleCardFactory factory = new BattleCardFactory(eventSystem);
        return new Battle(eventSystem,uiProvider, factory, new SingleSlotOpponent(factory, 0, opponentDeck), deck,new Energy(5,3), board, onWin, onLoss);
    }

    @Override
    public List<Opponent> getBattles() {
        return opponentDB.getAll();
    }

    @Override
    public List<ItemStack<?>> win(int battleId) {
        Opponent defeated = opponentDB.getSingle(battleId);
        RewardTransaction reward = defeated.getReward();
        transactionHandler.addItems(reward);
        return reward.getReceived();
    }
}
