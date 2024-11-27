package com.internetEnemies.combatCritters.Logic.battles.matchmaking;


/**
 * Match.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/27/24
 *
 * @PURPOSE:    handle high level battle state
 */
public class Match implements IMatch{
    ProofOfConceptBattle proofOfConceptBattle;
    private final IPlayer player1;
    private final IPlayer player2;
    public Match(IPlayer player1, IPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
        startMatch();
    }

    private void startMatch(){
        proofOfConceptBattle = new ProofOfConceptBattle(player1, player2);
    }
}
