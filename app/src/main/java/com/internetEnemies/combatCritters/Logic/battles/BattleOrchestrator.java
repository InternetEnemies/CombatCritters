package com.internetEnemies.combatCritters.Logic.battles;

import com.internetEnemies.combatCritters.objects.Card;

/**
 * BattleOrchestrator.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-23
 *
 * @PURPOSE:    Orchestrate the communication of battle state to the ui and player input to a battle
 */
public class BattleOrchestrator implements IBattleOrchestrator {
    private IBattleStateObserver observer;
    public BattleOrchestrator(IBattleStateObserver observer, Battle battle) {
        this.observer = observer;
        battle.initialize(this.observer);
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
