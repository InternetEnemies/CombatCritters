package com.internetEnemies.combatCritters.data.hsqldb.transactions;

import com.internetEnemies.combatCritters.data.hsqldb.TransactionItemVisitor;
import com.internetEnemies.combatCritters.data.hsqldb.dataHandlers.GenericSQLOperations;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.GeneratedKeysExtractor;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.IConnectionFactory;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.SQLExecutor;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SingleResultExtractor;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.sql.SQLException;
import java.util.List;

/**
 * TransactionsDB.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/16/24
 * 
 * @PURPOSE:    provides functions for creating transactions
 */
public class TransactionsDB {
    private final IConnectionFactory connectionFactory;

    public TransactionsDB(IConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * create a new transaction
     * @param tx transmit items
     * @param rx receive items
     * @return id of the new transaction
     */
    public int createTransaction(List<ItemStack<?>> tx, List<ItemStack<?>> rx) {
        // create transaction entry
        int tid = SQLExecutor.execute(
                GenericSQLOperations.updateWithKeys(SingleResultExtractor.getSingleResultExtractor(GeneratedKeysExtractor::extractKey)),
                connectionFactory,
                TransactionsSQL.createTransaction(),
                "Failed to create transaction entry"
                );
        // create transaction items
        try {
            createItems(tx, tid, false);
            createItems(rx, tid, true);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create transaction items",e);
        }
        return tid;
    }
    
    private void createItems(List<ItemStack<?>> items,int tid, boolean recv)throws SQLException {
        for(ItemStack<?> item : items){ // this is pretty bad
            TransactionItemVisitor visitor = new TransactionItemVisitor(tid,item.getAmount(), recv, connectionFactory.getConnection());
            item.getItem().accept(visitor);
        }
    }
}
