package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.Logic.visitor.IItemVisitor;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * TransactionItemVisitor.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-17
 *
 * @PURPOSE:    visit an IItem and add a related TransactionItem to the sql database
 */
public class TransactionItemVisitor implements IItemVisitor {
    private static final String SQL_FORMAT = "INSERT INTO TransactionItem (tid, type, recv, %s, amount) VALUES (?,?,?,?,?)";

    private final Connection connection;
    private final int tid;
    private final int amount;
    private final boolean recv;

    public TransactionItemVisitor (int tid, int amount, boolean recv, Connection connection) {
        this.tid = tid;
        this.connection= connection;
        this.amount = amount;
        this.recv = recv;
    }
    @Override
    public void visitCritterCard(CritterCard card) {
        visitCard(card);
    }

    @Override
    public void visitItemCard(ItemCard card) {
        visitCard(card);
    }

    /**
     * helper for visiting any card
     * @param card card to visit
     */
    private void visitCard(Card card) {
        insert(card.getId(), "cardId", TransactionRegistryHSQLDB.ITEM_CARD);

    }

    /**
     * insert the item into the TransactionItem table
     * @param val value to insert
     * @param param parameter to insert at
     * @param type type of transaction item
     */
    private void insert(int val, String param, String type) {
        try {
            PreparedStatement statement = getStatement(param);
            statement.setString(2, type);
            statement.setInt(4, val);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("error inserting transaction item",e);
        }
    }

    @Override
    public void visitPack(Pack pack) {
        insert(pack.getId(), "packId", TransactionRegistryHSQLDB.ITEM_PACK);
    }

    @Override
    public void visitCurrency(Currency currency) {
        insert(currency.getAmount(), "currency", TransactionRegistryHSQLDB.ITEM_CURRENCY);
    }

    /**
     * get an initial statement for the insert
     * @param param parameter for the insert
     * @return PreparedStatement ready to have set called on it
     */
    private PreparedStatement getStatement(String param) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(String.format(SQL_FORMAT,param));
        statement.setInt(1,tid);
        statement.setBoolean(3,recv);
        statement.setInt(5, amount);
        return statement;
    }
}
