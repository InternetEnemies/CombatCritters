package com.internetEnemies.combatCritters.Logic.battles;

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
public class Battle implements IBattleOrchestrator{
    private final List<Card> hand;

    private final IBattleStateObserver uiProvider;

    public Battle(IBattleStateObserver uiProvider, List<Card> deck) {
        //todo
        this.hand = deck;

        this.uiProvider = uiProvider;
    }


    // * IBattleOrchestrator (UI related Methods)
    //todo should these be moved into a child class?

    /**
     * initializes the state of battle into the observer
     */
    private void initializeUI() {
        uiProvider.setHand(hand);
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

        uiProvider.setPlayerHealth(15);
        uiProvider.setEnemyHealth(25);
        uiProvider.setEnergy(3);
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
}
