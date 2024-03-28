package com.internetEnemies.combatCritters.Logic.battles.exceptions;

/**
 * BattleInputException.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/25/24
 * 
 * @PURPOSE:    exception for Input related issues, general thrown out of this layer to a controlling interface
 */
public class BattleInputException extends BattleException{
    public BattleInputException(String message) {
        super(message);
    }
}
