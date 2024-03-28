package com.internetEnemies.combatCritters.Logic.battles.registry.battleProviders;

import com.internetEnemies.combatCritters.Logic.battles.Battle;
import com.internetEnemies.combatCritters.Logic.battles.IBattleOrchestrator;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;
import com.internetEnemies.combatCritters.Logic.battles.events.IVoidEventListener;
import com.internetEnemies.combatCritters.Logic.battles.opponents.IBattleOpponent;
import com.internetEnemies.combatCritters.Logic.battles.opponents.SingleSlotOpponent;
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

public class SingleSlotProvider implements IBattleProvider{
    private final IRegistry<Card> cardIRegistry;
    private final IBattleCardFactory battleCardFactory;
    private final int pos;
    private final Integer[] cardIds;
    private final int health;
    private final int startEnergy;
    private final int maxEnergy;
    private final int size;

    public SingleSlotProvider(IRegistry<Card> cardRegistry, IBattleCardFactory battleCardFactory, int pos, Integer[] cards, int health, int startEnergy, int maxEnergy, int size){
        this.cardIRegistry = cardRegistry;
        this.battleCardFactory = battleCardFactory;
        this.pos = pos;
        this.cardIds = cards;
        this.health = health;
        this.startEnergy = startEnergy;
        this.maxEnergy = maxEnergy;
        this.size = size;
    }
    private Battle makeBattle(IEventSystem eventSystem, IBattleStateObserver uiProvider, IBattleOpponent opponent, List<Card> playerDeck, IEnergy energy, IBoard board, IVoidEventListener onWin, IVoidEventListener onLoss){
        return new Battle(eventSystem, uiProvider, this.battleCardFactory,opponent, playerDeck, energy, board, onWin, onLoss);
    }
    @Override
    public IBattleOrchestrator get(IEventSystem eventSystem, IBoardRowFactory rowFactory, IBoardFactory boardFactory, IBattleStateObserver uiProvider, List<Card> deck, IVoidEventListener onWin, IVoidEventListener onLoss) {
        List<Card> tempDeck = this.cardIRegistry.getListOf(Arrays.asList(this.cardIds));
        List<CritterCard> oppDeck = new ArrayList<>();
        for(Card card : tempDeck) {
            assert card instanceof CritterCard;
            oppDeck.add((CritterCard) card);
        }

        IBattleCard[] bufferInit = new IBattleCard[size];
        bufferInit[this.pos] = this.battleCardFactory.getCard(oppDeck.get(0));
        IBattleCard[] enemyInit = new IBattleCard[size];
        IBattleCard[] playerInit = new IBattleCard[size];

        IBoardRow buffer = rowFactory.getRow(eventSystem, null, size, bufferInit);
        IBoardRow enemy = rowFactory.getRow(eventSystem, new Health(health, health),size, enemyInit);
        IBoardRow player = rowFactory.getRow(eventSystem, new Health(health, health),size, playerInit);
        IBoard board = boardFactory.getBoard(eventSystem, this.size,buffer, enemy, player);

        IBattleOpponent opponent = new SingleSlotOpponent(this.battleCardFactory, this.pos, oppDeck);
        IEnergy energy = new Energy(this.maxEnergy, this.startEnergy);
        return makeBattle(eventSystem,uiProvider,opponent, deck, energy, board, onWin, onLoss);
    }
}
