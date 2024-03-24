package com.internetEnemies.combatCritters.Logic.battles;

import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCard;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IEnergy;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IHealth;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.battles.CardState;

import java.util.ArrayList;
import java.util.List;

/**
 * Battle.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-23
 *
 * @PURPOSE:    Handle battle state machine and trigger events on changes to battle state
 */
public class Battle implements IBattleOrchestrator, IBattle{
    private final List<Card> hand;
    private final IHealth healthEnemy;
    private final IHealth healthPlayer;
    private final IEnergy energy;

    private final IBattleStateObserver uiProvider;

    public Battle(IBattleStateObserver uiProvider, List<Card> deck, IHealth enemy, IHealth player, IEnergy energy) {
        //todo
        this.hand = deck;
        this.healthEnemy = enemy;
        this.healthPlayer = player;
        this.energy = energy;

        this.uiProvider = uiProvider;

        initializeUI();
    }


    // * IBattleOrchestrator (UI related Methods)
    //todo should these be moved into a child class?

    /**
     * initializes the state of battle into the observer
     */
    private void initializeUI() {
        uiProvider.setHand(hand);
        uiProvider.setPlayerHealth(healthPlayer.getHealth());
        uiProvider.setEnemyHealth(healthEnemy.getHealth());
        uiProvider.setEnergy(energy.getEnergy());
        //todo
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
        CardState testCard = new CardState(15,card);

        List<CardState> testList = new ArrayList<>();
        testList.add(testCard);
        testList.add(testCard);
        testList.add(testCard);
        testList.add(null);
        testList.add(testCard);
        uiProvider.setBufferCards(testList);
        uiProvider.setEnemyCards(testList);
        uiProvider.setPlayerCards(testList);

    }

    @Override
    public void endTurn() {
        //todo
        System.out.println("endTurn called");
    }

    @Override
    public void playCard(int pos, Card card) {
        //todo
        System.out.printf("playCard called with: \n\t%d\n\t%s\n",pos,card.toString());
    }

    @Override
    public void sacrifice(int pos) {
        //todo
        System.out.printf("sacrifice called with: %d\n", pos);
    }

    // * IBattle Methods
    @Override
    public boolean playCardRaw(int pos, int row, BattleCard card, boolean force) {
        return false;
    }

    @Override
    public void damagePosition(int pos, int row) {

    }

    @Override
    public void healPosition(int pos, int row) {

    }

    @Override
    public IEnergy getEnergy() {
        return this.energy;
    }

    @Override
    public IHealth getPlayerHealth() {
        return this.healthPlayer;
    }

    @Override
    public IHealth getEnemyHealth() {
        return this.healthEnemy;
    }
}
