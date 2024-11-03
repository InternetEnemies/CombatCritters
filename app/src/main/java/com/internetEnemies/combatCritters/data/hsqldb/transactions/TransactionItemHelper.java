package com.internetEnemies.combatCritters.data.hsqldb.transactions;

import com.internetEnemies.combatCritters.Logic.transaction.transactionItem.ITransactionItem;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.CardHelper;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.PackHelper;
import com.internetEnemies.combatCritters.data.hsqldb.TransactionRegistryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.dataHandlers.GenericSQLOperations;
import com.internetEnemies.combatCritters.data.hsqldb.queryProviders.CardSQL;
import com.internetEnemies.combatCritters.data.hsqldb.queryProviders.PackSQL;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.ResultListExtractor;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.IConnectionFactory;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.IStatementFactory;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.SQLExecutor;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SingleResultExtractor;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * TransactionItemHelper.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/1/24
 * 
 * @PURPOSE:    get Items for a transaction
 */
public class TransactionItemHelper<Tx extends ITransactionItem, Rx extends ITransactionItem> {
    private final ITransactionItemHandler<Tx> txHandler;
    private final ITransactionItemHandler<Rx> rxHandler;
    private final IConnectionFactory connectionFactory;
    
    public TransactionItemHelper(ITransactionItemHandler<Tx> txHandler, ITransactionItemHandler<Rx> rxHandler, IConnectionFactory connectionFactory) {
        this.txHandler = txHandler;
        this.rxHandler = rxHandler;
        this.connectionFactory = connectionFactory;
    }

    /**
     * get the transaction items for a transaction
     * @param id transaction id to get items for
     */
    public TransactionItems<Tx, Rx> get(int id) {
        List<ItemStack<?>> tx = new ArrayList<>();
        List<ItemStack<?>> rx = new ArrayList<>();
        //Get transaction items
        List<TransactionItem> items = SQLExecutor.execute(
                GenericSQLOperations.query(ResultListExtractor.getListExtractor(TransactionItemHelper::from)),
                this.connectionFactory,
                getTransactionItems(id),
                "failed to get transaction items"
                );
        //categorize and get ItemStacks
        for (TransactionItem item : items) {
            ItemStack<?> stack = stackFromTransaction(item);
            if (item.recv()){
                rx.add(stack);
            } else {
                tx.add(stack);
            }
        }
        
        return new TransactionItems<>(txHandler.process(tx), rxHandler.process(rx));
    }

    /**
     * provides statement for getting transaction items
     */
    private static IStatementFactory getTransactionItems(int tid) {
        return connection -> {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM TransactionItem WHERE tid=?");
            ps.setInt(1, tid);
            return ps;
        };
    }

    /**
     * get an itemStack from a transaction item
     */
    private ItemStack<?> stackFromTransaction(TransactionItem item) {
        IItem iitem =switch (item.type()){
            case TransactionRegistryHSQLDB.ITEM_CARD -> getCard(item);
            case TransactionRegistryHSQLDB.ITEM_PACK -> getPack(item);
            case TransactionRegistryHSQLDB.ITEM_CURRENCY -> getCurrency(item);
            default -> throw new RuntimeException("INVALID TRANSACTION TYPE");
        };
        return new ItemStack<>(iitem, item.amount());
    }

    /**
     * get Card from transaction item
     */
    private IItem getCard(TransactionItem item) {
        return SQLExecutor.execute(
                GenericSQLOperations.query(SingleResultExtractor.getSingleResultExtractor(CardHelper::cardFromResultSet)),
                connectionFactory,
                CardSQL.getCard(item.cardId()), 
                "Error getting Card from TransactionItem"
        );
    }

    /**
     * get pack from Transaction item
     */
    private IItem getPack(TransactionItem item) {
        PackHelper helper = new PackHelper(this.connectionFactory);
        return SQLExecutor.execute(
                GenericSQLOperations.query(SingleResultExtractor.getSingleResultExtractor(helper::packFromResult)),
                connectionFactory,
                PackSQL.getPack(item.packId()),
                "Error getting Pack from TransactionItem"
        );
    }

    /**
     * get currency from transaction item
     */
    private IItem getCurrency(TransactionItem item) {
        return new Currency(item.currency());
    }

    /**
     * get a TransactionItem record from a resultset
     */
    public static TransactionItem from(ResultSet rs) throws SQLException {
        return new TransactionItem(
                rs.getInt("tid"),
                rs.getString("type"),
                rs.getBoolean("recv"),
                rs.getInt("packId"),
                rs.getInt("cardId"),
                rs.getInt("currency"),
                rs.getInt("amount")
        );
    }
}