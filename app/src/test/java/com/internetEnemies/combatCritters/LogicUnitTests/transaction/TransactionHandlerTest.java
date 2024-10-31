package com.internetEnemies.combatCritters.LogicUnitTests.transaction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.transaction.ITransactionHandler;
import com.internetEnemies.combatCritters.Logic.transaction.TransactionHandler;
import com.internetEnemies.combatCritters.Logic.transaction.participant.IParticipant;
import com.internetEnemies.combatCritters.Logic.transaction.transactionItem.ITransactionItem;
import com.internetEnemies.combatCritters.objects.Transaction;
import org.junit.Before;
import org.junit.Test;

public class TransactionHandlerTest {
    ITransactionHandler transactionHandler;
    IParticipant p0;
    IParticipant p1;
    Transaction<ITransactionItem,ITransactionItem> transaction;
    ITransactionItem txItem;
    ITransactionItem rxItem;
    
    @Before
    public void setup() {
        this.p0 = mock(IParticipant.class);
        this.p1 = mock(IParticipant.class);
        
        this.transactionHandler = new TransactionHandler(p0,p1);
        this.transaction = (Transaction<ITransactionItem,ITransactionItem>) mock(Transaction.class);
        this.txItem = mock(ITransactionItem.class);
        this.rxItem = mock(ITransactionItem.class);
        when(transaction.getReceive()).thenReturn(rxItem);
        when(transaction.getTransmit()).thenReturn(txItem);
    }
    
    @Test
    public void test_verify(){
        when(txItem.verifyWith(any())).thenReturn(true);
        when(rxItem.verifyWith(any())).thenReturn(true);
        assertTrue(transactionHandler.verify(transaction));
    }
    
    @Test
    public void test_perform(){
        transactionHandler.perform(transaction);
        verify(txItem).removeFrom(any());
        verify(rxItem).removeFrom(p1);
        verify(txItem).addTo(p1);
        verify(rxItem).addTo(p0);
    }
}
