package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DeckInventoryHSQLDB.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/7/24
 *
 * @PURPOSE:    sql implmentation of IDeckInventory
 */
public class DeckInventoryHSQLDB implements IDeckInventory {
    private final String dbPath;

    public DeckInventoryHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private DeckDetails fromResultSet(final ResultSet rs) throws SQLException {
        final int id = rs.getInt("id");
        final String name = rs.getString("name");
        return new DeckDetails(id, name);
    }

    @Override
    public IDeck getDeck(DeckDetails deckDetails) {
        try (final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM Decks WHERE id = ?");
            statement.setInt(1, deckDetails.getId());
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new DeckHSQLDB(dbPath, deckDetails);
            }
            else {
                return null; // Deck not found
            }
        }
        catch (final SQLException | DeckHSQLDB.NXDeckException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while processing the SQL operation", e);
        }
    }

    @Override
    public IDeck createDeck(String name) {
        try (final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO Decks (name) VALUES (?)",Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys(); // why doesnt this line throw an error if the above flag is unset wth
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt("id");
                DeckDetails newDeck = new DeckDetails(generatedId, name);
                return new DeckHSQLDB(dbPath, newDeck);
            }
            else {
                throw new SQLException("Creating deck failed, no ID obtained.");
            }
        }
        catch (final SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while processing the SQL operation", e);
        }
        catch (DeckHSQLDB.NXDeckException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteDeck(DeckDetails deckDetails) {
        try (final Connection connection = connection()) {
            //delete cards in deck
            final PreparedStatement deleteDeckCards = connection.prepareStatement("DELETE FROM DeckCards WHERE deckId = ?");
            deleteDeckCards.setInt(1, deckDetails.getId());
            deleteDeckCards.executeUpdate();

            //delete deck
            final PreparedStatement statement = connection.prepareStatement("DELETE FROM Decks WHERE id = ?");
            statement.setInt(1, deckDetails.getId());
            statement.executeUpdate();
        }
        catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);
        }
    }

    @Override
    public List<DeckDetails> getDeckDetails() {
        List<DeckDetails> deckDetailsList = new ArrayList<>();
        try (final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM Decks");
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.wasNull());
                deckDetailsList.add(fromResultSet(resultSet));
            }
        }
        catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);
        }
        return deckDetailsList;
    }
}