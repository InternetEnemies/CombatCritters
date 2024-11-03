package com.internetEnemies.combatCritters.Logic.transaction;

import com.internetEnemies.combatCritters.Logic.transaction.participant.IParticipant;
import com.internetEnemies.combatCritters.Logic.transaction.transactionItem.ITransactionItem;
import com.internetEnemies.combatCritters.objects.Transaction;

/**
 * TransactionHandler.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/31/24
 * 
 * @PURPOSE:    perform transactions between 2 participants
 */
public class TransactionHandler implements ITransactionHandler {
    /*
    Brief explanation of naming for both transaction and this class
    transmitter transmits the transmit items in the transaction to the receiver,
    receiver transmits the receive items in the transaction to the transmitter
    */

    private final IParticipant transmitter;
    private final IParticipant receiver;

    public TransactionHandler(IParticipant transmitter, IParticipant receiver) {
        this.transmitter = transmitter;
        this.receiver = receiver;
    }

    @Override
    public boolean verify(Transaction<?, ?> transaction) {
        return transaction.getTransmit().verifyWith(transmitter) && 
                transaction.getReceive().verifyWith(receiver); //true if the transmitter has the items to transmit and the receiver has the items to give
    }

    @Override
    public void perform(Transaction<?, ?> transaction) {
        ITransactionItem transmit = transaction.getTransmit();
        ITransactionItem receive = transaction.getReceive();
        
        // exchange items between participants
        transmit.removeFrom(transmitter);
        receive.removeFrom(receiver);
        transmit.addTo(receiver);
        receive.addTo(transmitter);
    }

    @Override
    public void verifiedPerform(Transaction<?, ?> transaction) {
        if(!verify(transaction)) throw new UnverifiedTransactionException("Transaction could not be verified");
        perform(transaction);
    }

}