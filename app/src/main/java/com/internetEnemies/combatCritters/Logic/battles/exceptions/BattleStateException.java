package com.internetEnemies.combatCritters.Logic.battles.exceptions;

/**
 * BattleStateException.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/29/24
 *
 * @PURPOSE:    exception thrown when an operation trying to be performed in an invalid state
 */
public class BattleStateException extends BattleRuntimeException {
    public BattleStateException(String message) {
        super(message);
    }
    public BattleStateException(String message, Exception cause) {
      super(message, cause);
    }
}
