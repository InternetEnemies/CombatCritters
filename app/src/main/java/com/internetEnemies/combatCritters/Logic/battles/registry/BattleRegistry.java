package com.internetEnemies.combatCritters.Logic.battles.registry;

import com.internetEnemies.combatCritters.Logic.transaction.ITransactionHandlerDeprecated;
import com.internetEnemies.combatCritters.Logic.battles.IBattleOrchestrator;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.events.EventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IVoidEventListener;
import com.internetEnemies.combatCritters.Logic.battles.registry.battleProviders.IBattleProvider;
import com.internetEnemies.combatCritters.Logic.battles.registry.battleProviders.RandomProvider;
import com.internetEnemies.combatCritters.Logic.battles.registry.battleProviders.SingleSlotProvider;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.BoardFactory;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.BoardRowFactory;
import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.PackCardDatabase;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.battles.Opponent;
import com.internetEnemies.combatCritters.objects.battles.RewardTransaction;

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
    private static final int ROW_SIZE = 5;
    private static final int HEALTH = 25;
    private static final int ENERGY_MAX = 5;
    private static final int ENERGY_INIT = 3;
    private final IRegistry<Opponent> opponentDB;
    private final ITransactionHandlerDeprecated transactionHandler;
    private final IBattleProvider[] battles;
    private final IEventSystem eventSystem;

    public BattleRegistry(ITransactionHandlerDeprecated transactionHandler) {
        this(new EventSystem(), transactionHandler);
    }
    public BattleRegistry(IEventSystem eventSystem, ITransactionHandlerDeprecated transactionHandler){
        this(
                Database.getInstance().getOpponentDB(),
                transactionHandler,
                PackCardDatabase.getInstance().getCardDB(),
                new BattleCardFactory(eventSystem),
                eventSystem
                );
    }

    public BattleRegistry(IRegistry<Opponent> opponentDB, ITransactionHandlerDeprecated transactionHandler, IRegistry<Card> cardRegistry, IBattleCardFactory battleCardFactory, IEventSystem eventSystem){
        this.opponentDB = opponentDB;
        this.transactionHandler = transactionHandler;
        this.eventSystem = eventSystem;

        // battles init
        battles = new IBattleProvider[]{
                new SingleSlotProvider(cardRegistry,battleCardFactory,1,new Integer[]{1,2,3},HEALTH,ENERGY_INIT,ENERGY_MAX,ROW_SIZE),
                new RandomProvider(cardRegistry,battleCardFactory,new Integer[]{19,5,25,33,24,36},HEALTH,ENERGY_INIT,ENERGY_MAX,ROW_SIZE,ROW_SIZE)
        };
    }
    @Override
    public IBattleOrchestrator getBattle(IBattleStateObserver uiProvider, int id, List<Card> deck, IVoidEventListener onWin, IVoidEventListener onLoss) {
        return this.battles[id-1].get(this.eventSystem,new BoardRowFactory(),new BoardFactory(),uiProvider,deck,onWin,onLoss);
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
