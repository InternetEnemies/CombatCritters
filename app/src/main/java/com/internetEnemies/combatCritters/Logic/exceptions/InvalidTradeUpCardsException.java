/**
 * InvalidTradeUpCardsException.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-25
 *
 * @PURPOSE:    custom exception to throws in TradeUpHandler.confirmTradeUp()
 */

package com.internetEnemies.combatCritters.Logic.exceptions;

public class InvalidTradeUpCardsException extends Exception{
    public InvalidTradeUpCardsException(int difference){
        super("Receive invalid Trade Up cards:\n Difference: "+difference);
    }

    public InvalidTradeUpCardsException(String message){
        super(message);
    }
}
