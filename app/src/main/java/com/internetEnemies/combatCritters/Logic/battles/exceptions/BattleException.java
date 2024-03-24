package com.internetEnemies.combatCritters.Logic.battles.exceptions;

/**
 * BattleException.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-23
 *
 * @PURPOSE:    custom exception thrown by battles
 */
public class BattleException extends Exception{
    public BattleException(String message) {
        super(message);
    }
}
