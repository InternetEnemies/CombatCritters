package com.internetEnemies.combatCritters.Logic.transaction;

import com.internetEnemies.combatCritters.Logic.transaction.participant.IParticipant;

public interface ITransactionHandlerFactory {
    ITransactionHandler getTransactionHandler(IParticipant p0, IParticipant p1);
}
