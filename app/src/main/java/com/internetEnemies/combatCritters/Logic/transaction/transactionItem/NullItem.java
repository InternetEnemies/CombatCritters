package com.internetEnemies.combatCritters.Logic.transaction.transactionItem;

import com.internetEnemies.combatCritters.Logic.transaction.participant.IParticipant;

/**
 * NullItem.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/31/24
 * 
 * @PURPOSE:    represents null in a transaction. used to allow for one-sided transactions such as battle rewards or battle buy in
 */
public class NullItem implements ITransactionItem{
    @Override
    public boolean verifyWith(IParticipant inventory) {
        return true; // true since we always have at least nothing
    }

    @Override
    public void removeFrom(IParticipant inventory) {} // we cant remove nothing

    @Override
    public void addTo(IParticipant inventory) {} // we cant add nothing
}
