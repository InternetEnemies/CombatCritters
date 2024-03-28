package com.internetEnemies.combatCritters.Logic.battles.registry.battleProviders;

import com.internetEnemies.combatCritters.Logic.battles.Battle;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IVoidEventListener;
import com.internetEnemies.combatCritters.Logic.battles.opponents.IBattleOpponent;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Energy;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Health;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoard;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardFactory;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRow;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoardRowFactory;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IEnergy;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * BasicBattleProvider.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/28/24
 *
 * @PURPOSE:    common functionality for Basic Battles
 */
public abstract class BasicBattleProvider implements IBattleProvider {
    protected final IRegistry<Card> cardIRegistry;
    protected final IBattleCardFactory battleCardFactory;
    protected final Integer[] cardIds;
    protected final int health;
    protected final int startEnergy;
    protected final int maxEnergy;
    protected final int size;

    public BasicBattleProvider(IRegistry<Card> cardRegistry, IBattleCardFactory battleCardFactory, Integer[] cards, int health, int startEnergy, int maxEnergy, int size) {
        this.cardIRegistry = cardRegistry;
        this.battleCardFactory = battleCardFactory;
        this.cardIds = cards;
        this.health = health;
        this.startEnergy = startEnergy;
        this.maxEnergy = maxEnergy;
        this.size = size;
    }

    /**
     * get the battle object
     */
    protected Battle makeBattle(IEventSystem eventSystem, IBattleStateObserver uiProvider, IBattleOpponent opponent, List<Card> playerDeck, IBoard board, IVoidEventListener onWin, IVoidEventListener onLoss) {
        IEnergy energy = new Energy(this.maxEnergy, this.startEnergy);
        return new Battle(eventSystem, uiProvider, this.battleCardFactory, opponent, playerDeck, energy, board, onWin, onLoss);
    }

    /**
     * @return opponents deck from card id list
     */
    protected List<CritterCard> getOppDeck() {
        List<Card> tempDeck = this.cardIRegistry.getListOf(Arrays.asList(this.cardIds));
        List<CritterCard> oppDeck = new ArrayList<>();
        for (Card card : tempDeck) {
            assert card instanceof CritterCard;
            oppDeck.add((CritterCard) card);
        }
        return oppDeck;
    }

    /**
     * get the board
     */
    protected IBoard getBoard(IEventSystem eventSystem, IBoardRowFactory rowFactory, IBoardFactory boardFactory, IBattleCard[] bufferInit, IBattleCard[] enemyInit, IBattleCard[] playerInit) {
        IBoardRow buffer = rowFactory.getRow(eventSystem, null, size, bufferInit);
        IBoardRow enemy = rowFactory.getRow(eventSystem, new Health(health, health), size, enemyInit);
        IBoardRow player = rowFactory.getRow(eventSystem, new Health(health, health), size, playerInit);
        return boardFactory.getBoard(eventSystem, this.size, buffer, enemy, player);
    }
}
