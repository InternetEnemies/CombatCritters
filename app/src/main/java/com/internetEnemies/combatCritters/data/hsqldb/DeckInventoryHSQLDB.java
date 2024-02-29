package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DeckInventoryHSQLDB implements IDeckInventory {
    private final String dbPath;

    public DeckInventoryHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private DeckDetails fromResultSet(final ResultSet rs) throws SQLException {
        final Integer id = rs.getInt("id");
        final String name = rs.getString("name");
        return new DeckDetails(id, name);
    }

    @Override
    public IDeck getDeck(DeckDetails deckDetails) {
        return null;
    }

    @Override
    public IDeck createDeck(String name) {
        return null;
    }

    @Override
    public void deleteDeck(DeckDetails deckDetails) {

    }

    @Override
    public List<DeckDetails> getDeckDetails() {
        return null;
    }
}
