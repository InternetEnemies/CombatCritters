package com.internetEnemies.combatCritters.Logic.transaction.transactionItem;

import com.internetEnemies.combatCritters.Logic.transaction.participant.IParticipant;

/**
 * ITransactionItem.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/31/24
 * 
 * @PURPOSE:    an item in a transaction
 */
public interface ITransactionItem {
    boolean verifyWith(IParticipant inventory);
    void removeFrom(IParticipant inventory);
    void addTo(IParticipant inventory);
    
}
