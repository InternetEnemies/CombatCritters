package com.internetEnemies.combatCritters.Logic.battles.matchmaking;

import com.internetEnemies.combatCritters.Logic.battles.IBattleOrchestrator;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleInputException;
import com.internetEnemies.combatCritters.objects.Card;

/**
 * ProofOfConceptBattle.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/27/24
 *
 * @PURPOSE:    provide a basic test battle (this should be deleted later)
 */
public class ProofOfConceptBattle { //todo REMOVE THIS
    private final IBattleOrchestrator orch1;
    private final IBattleOrchestrator orch2;
    private final IBattleStateObserver observer1;
    private final IBattleStateObserver observer2;

    public ProofOfConceptBattle(IPlayer p1, IPlayer p2) {
        this.observer1 = p1.getStateObserver();
        this.observer2 = p2.getStateObserver();

        this.orch1 = new IBattleOrchestrator() {
            @Override
            public void endTurn() throws BattleInputException {
                observer2.setPlayerTurn(true);
            }

            @Override
            public void playCard(int pos, Card card) throws BattleInputException {
            }

            @Override
            public void sacrifice(int pos) throws BattleInputException {
            }
        };
        this.orch2 = new IBattleOrchestrator() {
            @Override
            public void endTurn() throws BattleInputException {
                observer1.setPlayerTurn(true);
            }

            @Override
            public void playCard(int pos, Card card) throws BattleInputException {

            }

            @Override
            public void sacrifice(int pos) throws BattleInputException {

            }
        };

        // set orchestrators
        p1.setOrchestrator(orch1);
        p2.setOrchestrator(orch2);
    }
}
