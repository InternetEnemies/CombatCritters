package com.internetEnemies.combatCritters.objects.battles;

import com.internetEnemies.combatCritters.objects.Card;

import java.util.Collections;
import java.util.List;

/**
 * BattleState.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/19/24
 *
 * @PURPOSE:    store the state of a battle for rendering
 */
public class BattleState {
    private final boolean isPlayerTurn;
    private final int playerHealth;
    private final int enemyHealth;
    private final int energy;
    private final List<Card> hand;
    private final int drawPileSize;
    private final List<GameCard> buffer;
    private final List<GameCard> enemy;
    private final List<GameCard> player;

    public BattleState(
            boolean isPlayerTurn,
            int playerHealth,
            int enemyHealth,
            int energy,
            List<Card> hand,
            int drawPileSize,
            List<GameCard> buffer,
            List<GameCard> enemy,
            List<GameCard> player
    ) {
        this.isPlayerTurn = isPlayerTurn;
        this.playerHealth = playerHealth;
        this.enemyHealth = enemyHealth;
        this.energy = energy;
        this.hand = hand;
        this.drawPileSize = drawPileSize;
        this.buffer = buffer;
        this.enemy = enemy;
        this.player = player;
    }

    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }

    public int getEnergy() {
        return energy;
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getDrawPileSize() {
        return drawPileSize;
    }

    public List<GameCard> getBuffer() {
        return Collections.unmodifiableList(buffer);
    }

    public List<GameCard> getEnemy() {
        return Collections.unmodifiableList(enemy);
    }

    public List<GameCard> getPlayer() {
        return Collections.unmodifiableList(player);
    }
}
