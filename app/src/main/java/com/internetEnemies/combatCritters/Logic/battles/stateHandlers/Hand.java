package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.events.EventHost;
import com.internetEnemies.combatCritters.Logic.battles.events.HandEvent;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.*;

/**
 * Hand.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     12/4/24
 *
 * @PURPOSE:    Hand state holder
 */
public class Hand implements IHand{

    private final List<Card> hand;
    private final Queue<Card> pullStack;
    private final EventHost<HandEvent> eventHost;
    public Hand(List<Card> deck) {
        this(deck, new EventHost<>());
    }
    public Hand(List<Card> deck, EventHost<HandEvent> eventHost) {
        this.eventHost = eventHost;
        this.hand = new ArrayList<>();

        //init pull stack
        LinkedList<Card> temp = new LinkedList<>(deck);
        Collections.shuffle(temp);
        this.pullStack = temp;
    }

    @Override
    public void pullCards(int amount) {
        for (int i = 0; i < amount; i++) {
            pullCard();
        }
    }

    @Override
    public void pullCard() {
        if (!this.pullStack.isEmpty()) {
            Card card = pullStack.remove();
            this.hand.add(card);
            this.eventHost.fireEvent(new HandEvent(pullStack.size(), hand));
        }
    }

    @Override
    public List<Card> getCards() {
        return hand;
    }

    @Override
    public EventHost<HandEvent> getEventHost() {
        return eventHost;
    }

    @Override
    public void remove(Card card) {
        this.hand.remove(card);
        this.eventHost.fireEvent(new HandEvent(hand.size(), hand));
    }
}
