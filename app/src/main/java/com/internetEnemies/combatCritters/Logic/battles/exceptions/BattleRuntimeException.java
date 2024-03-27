package com.internetEnemies.combatCritters.Logic.battles.exceptions;

/**
 * BattleRuntimeException.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-23
 *
 * @PURPOSE:    Custom runtime exception thrown by battles
 */
public class BattleRuntimeException extends RuntimeException{
    public BattleRuntimeException(String message) {
        super(message);
    }

    public BattleRuntimeException(String message, Exception e) {
        super(message,e);
    }
}
