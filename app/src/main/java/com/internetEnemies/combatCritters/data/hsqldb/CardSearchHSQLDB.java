package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.ICardSearch;
import com.internetEnemies.combatCritters.objects.Card;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

// ATTENTION: THis cannot be done until item stacks so disregard this

public class CardSearchHSQLDB implements ICardSearch {

    private final String dbPath;

    public CardSearchHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }






    @Override
    public Map<Card, Integer> getOwned() {
        return null;
    }

    @Override
    public Map<Card, Integer> getAll() {
        return null;
    }
}
