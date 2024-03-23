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
public class Battle {
    private final List<Card> hand;
    public Battle(List<Card> deck) {
        //todo
        this.hand = deck;
    }

    /**
     * initializes the state of battle into the observer
     *
     * @param observer interface provider to initialize
     */
    public void initialize(IBattleStateObserver observer) {
        //!!!THIS IS THE ONLY FUNCTION IN THIS CLASS THAT SHOULD INTERACT WITH THE OBSERVER DIRECTLY
        observer.setHand(hand);
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
        observer.setBufferCards(testList);
        observer.setEnemyCards(testList);
        observer.setPlayerCards(testList);

        observer.setPlayerHealth(15);
        observer.setEnemyHealth(25);
        observer.setEnergy(3);

    }
}
