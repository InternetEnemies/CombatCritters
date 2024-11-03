package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.Logic.transaction.transactionItem.MultiItem;
import com.internetEnemies.combatCritters.Logic.transaction.transactionItem.SingleItem;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.IConnectionFactory;
import com.internetEnemies.combatCritters.data.hsqldb.transactions.MultiItemHandler;
import com.internetEnemies.combatCritters.data.hsqldb.transactions.SingleItemHandler;
import com.internetEnemies.combatCritters.data.hsqldb.transactions.TransactionItemHelper;
import com.internetEnemies.combatCritters.data.hsqldb.transactions.TransactionItems;
import com.internetEnemies.combatCritters.objects.VendorTransaction;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * VendorOfferHelper.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/2/24
 * 
 * @PURPOSE:    get objects from result sets for vendor offers
 */
public class VendorOfferHelper {
    private final IConnectionFactory connectionFactory;
    public VendorOfferHelper(IConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * get a Vendor Transaction from a result set
     */
    public VendorTransaction fromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("tid");

        TransactionItemHelper<MultiItem, SingleItem> helper = new TransactionItemHelper<>(new MultiItemHandler(), new SingleItemHandler(), connectionFactory);
        TransactionItems<MultiItem,SingleItem> transactionItems = helper.get(id);
        return new VendorTransaction(id, transactionItems.transmitItem(),transactionItems.receiveItem());
    }
}
