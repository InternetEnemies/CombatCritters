package com.internetEnemies.combatCritters.Logic.battles.registry.battleProviders;

import com.internetEnemies.combatCritters.Logic.battles.IBattleOrchestrator;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IVoidEventListener;
import com.internetEnemies.combatCritters.Logic.battles.opponents.IBattleOpponent;
import com.internetEnemies.combatCritters.Logic.battles.opponents.RandomOpponent;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoard;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardFactory;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRowFactory;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;

import java.util.List;
import java.util.Random;

/**
 * RandomProvider.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/28/24
 *
 * @PURPOSE:    provider for RandomOpponent battles
 */
public class RandomProvider extends BasicBattleProvider{

    private final int maxVal;

    public RandomProvider(IRegistry<Card> cardRegistry, IBattleCardFactory battleCardFactory, Integer[] cards, int health, int startEnergy, int maxEnergy, int size,int max) {
        super(cardRegistry, battleCardFactory, cards, health, startEnergy, maxEnergy, size);
        assert max <= size;
        this.maxVal = max;
    }

    @Override
    public IBattleOrchestrator get(IEventSystem eventSystem, IBoardRowFactory rowFactory, IBoardFactory boardFactory, IBattleStateObserver uiProvider, List<Card> deck, IVoidEventListener onWin, IVoidEventListener onLoss) {
        Random random = new Random();
        List<CritterCard> oppDeck = getOppDeck();

        IBattleOpponent opponent = new RandomOpponent(battleCardFactory,oppDeck,this.maxVal);
        IBattleCard[] buffer = new IBattleCard[size];
        IBattleCard[] enemy = new IBattleCard[size];
        enemy[random.nextInt(size)] = battleCardFactory.getCard(oppDeck.get(random.nextInt(oppDeck.size())));

        IBattleCard[] player = new IBattleCard[size];
        IBoard board = getBoard(eventSystem,rowFactory,boardFactory,enemy,buffer,player);
        return makeBattle(eventSystem, uiProvider,opponent,deck,board, onWin,onLoss);
    }
}
