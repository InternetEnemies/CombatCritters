package com.internetEnemies.combatCritters.Logic.battles.registry.battleProviders;

import com.internetEnemies.combatCritters.Logic.battles.IBattleOrchestrator;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IVoidEventListener;
import com.internetEnemies.combatCritters.Logic.battles.opponents.IBattleOpponent;
import com.internetEnemies.combatCritters.Logic.battles.opponents.SingleSlotOpponent;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoard;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardFactory;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRowFactory;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;

import java.util.List;

/**
 * SingleSlotProvider.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/28/24
 *
 * @PURPOSE:    provide single slot opponents
 */
public class SingleSlotProvider extends BasicBattleProvider {
    private final int pos;

    public SingleSlotProvider(IRegistry<Card> cardRegistry, IBattleCardFactory battleCardFactory, int pos, Integer[] cards, int health, int startEnergy, int maxEnergy, int size){
        super(cardRegistry, battleCardFactory, cards, health, startEnergy, maxEnergy, size);
        this.pos = pos;
    }

    @Override
    public IBattleOrchestrator get(IEventSystem eventSystem, IBoardRowFactory rowFactory, IBoardFactory boardFactory, IBattleStateObserver uiProvider, List<Card> deck, IVoidEventListener onWin, IVoidEventListener onLoss) {
        List<CritterCard> oppDeck = getOppDeck();

        IBattleCard[] bufferInit = new IBattleCard[size];
        bufferInit[this.pos] = this.battleCardFactory.getCard(oppDeck.get(0));
        IBattleCard[] enemyInit = new IBattleCard[size];
        IBattleCard[] playerInit = new IBattleCard[size];

        IBoard board = getBoard(eventSystem, rowFactory, boardFactory, bufferInit, enemyInit, playerInit);

        IBattleOpponent opponent = new SingleSlotOpponent(this.battleCardFactory, this.pos, oppDeck);
        return makeBattle(eventSystem,uiProvider,opponent, deck, board, onWin, onLoss);
    }
}
