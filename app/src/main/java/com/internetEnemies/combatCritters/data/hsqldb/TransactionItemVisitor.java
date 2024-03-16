package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.Logic.IItemVisitor;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.objects.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    private void visitCard(Card card) {
        insert(card.getId(), "cardId", TransactionRegistryHSQLDB.ITEM_CARD);

    }

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

    private PreparedStatement getStatement(String param) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(String.format(SQL_FORMAT,param));
        statement.setInt(1,tid);
        statement.setBoolean(3,recv);
        statement.setInt(5, amount);
        return statement;
    }
}
