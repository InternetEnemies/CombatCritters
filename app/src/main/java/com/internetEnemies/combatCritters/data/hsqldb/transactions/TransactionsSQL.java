package com.internetEnemies.combatCritters.data.hsqldb.transactions;

import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.IStatementFactory;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * TransactionsSQL.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/16/24
 * 
 * @PURPOSE:    provide sql for transactions
 */
public class TransactionsSQL {
    private static final String TXN_TYPE = "x";// this should be removed at some point, but it requires a breaking db schema change

    /**
     * sql for creating a new transaction
     */
    public static IStatementFactory createTransaction() {
        return connection -> {
            PreparedStatement preparedStatement = 
                    connection.prepareStatement("INSERT INTO Transactions (type) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, TXN_TYPE);
            return preparedStatement;
        };
    }
}
