package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.ItemCard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckHSQLDB implements IDeck {

    private final String dbPath;
    private final DeckDetails deckDetails;

    public DeckHSQLDB(final String dbPath, final DeckDetails deckDetails) throws NXDeckException {
        this.dbPath = dbPath;
        this.deckDetails = deckDetails;
        try (final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT id FROM Decks WHERE id = ?");
            statement.setInt(1, deckDetails.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                throw new NXDeckException("Deck with ID " + deckDetails.getId() + " already exists.");
            }
        } catch(SQLException e){
            System.err.println("An error occurred while setting deckDetails: " + e.getMessage());
            throw new NXDeckException("Error while setting deckDetails", e);
        }
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Card fromResultSet(final ResultSet rs) throws SQLException {
        final Integer id = rs.getInt("id");
        final String name = rs.getString("name");
        final String image = rs.getString("image");
        final Integer playCost = rs.getInt("playCost");
        final Integer rarity = rs.getInt("rarity");
        final String type = rs.getString("type");

        Card card = null;

        // Dummy list
        List<String> abilities = new ArrayList<>();

        switch(type){
            case "critter":
                final Integer damage = rs.getInt("damage");
                final Integer health = rs.getInt("health");
                //final Integer/List/whatever abilities = rs.getSomethingHere("abilities");
                card = new CritterCard(id, name, image, playCost, rarity/*Card.Rarity.rarity*/, damage, health /*ability*/);
                break;
            case "item":
                final Integer effectId = rs.getInt("effectId");
                card = new ItemCard(id, name, image, playCost, rarity/*Card.Rarity.rarity*/, effectId);
                break;
        }
        return card;
    }

    @Override
    public Card getCard(int slot) {
        try(final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM DeckCards NATURAL JOIN Cards WHERE Cards.id = ?");
            statement.setInt(1, slot);

            final ResultSet resultSet = statement.executeQuery();

            Card card = null;
            while(resultSet.next()) {
                card = fromResultSet(resultSet);
            }
            resultSet.close();
            statement.close();

            return card;
        }
        catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);  //temp exception
        }
    }

    @Override
    public void addCard(int slot, Card card) {
        try(final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO DeckCards (deckId, cardId, position) VALUES(?, ?, ?)");
            statement.setInt(1, slot);
            statement.setInt(2, card.getId());

            statement.executeUpdate();
        }
        catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);  //temp exception
        }
    }

    @Override
    public void removeCard(int slot) {
        try(final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("DELETE FROM DeckCards WHERE slot = ?");
            statement.setInt(1, slot);
            statement.executeUpdate();
        }
        catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);  //temp exception
        }

    }

    @Override
    public int countCard(Card card) {
        try (final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM DeckCards WHERE cardId = ?");
            statement.setInt(1, card.getId());
            final ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        }
        catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);
        }
    }

    @Override
    public Map<Card, Integer> countCards() {
        Map<Card, Integer> cardCounts = new HashMap<>();
        try (final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT cardId, COUNT(*) FROM DeckCards GROUP BY cardId");
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int cardId = resultSet.getInt("cardId");
                int count = resultSet.getInt(2);
                Card card = card.getId(cardId); // This concerns me
                cardCounts.put(card, count);
            }
        }
        catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);
        }
        return cardCounts;
    }

    @Override
    public DeckDetails getInfo() {
        try (final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM DeckDetails");
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DeckDetails details = null; // Placeholder bc i think this shit is wrong
                details.getId();
                details.getName();
                return details;
            }
            else {
                return null;
            }
        }
        catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);
        }
    }

    @Override
    public List<Card> getCards() {
        List<Card> cards = new ArrayList<>();
        try (final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM DeckCards");
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int cardId = resultSet.getInt("cardId");
                Card card = card.getId(cardId); // This concerns me yet again
                if (card != null) {
                    cards.add(card);
                }
            }
        }
        catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);
        }
        return cards;
    }

    @Override
    public int getTotalCards() {
        try (final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM DeckCards");
            final ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        }
        catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);
        }
    }

    public class NXDeckException extends Exception {
        public NXDeckException() {
            super();
        }

        public NXDeckException(String message) {
            super(message);
        }

        public NXDeckException(String message, Throwable cause) {
            super(message, cause);
        }

        public NXDeckException(Throwable cause) {
            super(cause);
        }
    }
}
