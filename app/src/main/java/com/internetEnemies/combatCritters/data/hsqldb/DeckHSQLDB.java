package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.data.CardBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

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
            if (!resultSet.next()) {
                throw new NXDeckException("Deck with ID " + deckDetails.getId() + " doesn't exists.");
            }
        } catch(SQLException e){
            System.err.println("An error occurred while setting deckDetails: " + e.getMessage());
            throw new NXDeckException("Error while setting deckDetails");
        }
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Card fromResultSet(final ResultSet rs) throws SQLException {
        final int id = rs.getInt("id");
        final String name = rs.getString("name");
        final String image = rs.getString("image");
        final int playCost = rs.getInt("playCost");
        final int rarity = rs.getInt("rarity");
        final String type = rs.getString("type");

        Card card = null;

        List<Integer> abilities = new ArrayList<>();
        Card.Rarity rare = Card.Rarity.values()[rarity];

        switch(type){
            case "critter":
                final int damage = rs.getInt("damage");
                final int health = rs.getInt("health");
                final Integer ability = rs.getInt("abilities");
                abilities.add(ability);
                card = new CritterCard(id, name, image, playCost, rare, damage, health, abilities);
                break;
            case "item":
                final int effectId = rs.getInt("effectId");
                card = new ItemCard(id, name, image, playCost, rare, effectId);
                break;
        }
        return card;
    }

    @Override
    public Card getCard(int slot) {
        try(final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM DeckCards INNER JOIN Cards ON Cards.id = DeckCards.cardId WHERE DeckCards.position = ?");
            statement.setInt(1, slot);

            final ResultSet resultSet = statement.executeQuery();

            Card card = null;
            while(resultSet.next()) {
                System.out.println(resultSet.getInt("DeckCards.position"));
                card = fromResultSet(resultSet);
            }
            resultSet.close();
            statement.close();
            if(card == null) {
                throw new IndexOutOfBoundsException();
            }

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
            statement.setInt(1, this.deckDetails.getId());
            statement.setInt(2, card.getId());
            statement.setInt(3, slot);

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
    public List<ItemStack<Card>> countCards() {
        List<ItemStack<Card>> cardStacks = new ArrayList<>();
        try (final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT *, COUNT(*) as count FROM Cards INNER JOIN DeckCards ON DeckCards.cardId == Card.cardId GROUP BY DeckCards.cardId");
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int cardId = resultSet.getInt("cardId");
                int count = resultSet.getInt(2);
                Card card = new CardBuilder().build();
                ItemStack<Card> itemStack = new ItemStack<>(card, count);
                cardStacks.add(itemStack);
            }
        }
        catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);
        } catch (CardBuilder.InvalidCardException e) {
            throw new RuntimeException(e);
        }
        return cardStacks;
    }

    @Override
    public DeckDetails getInfo() {
        return this.deckDetails;
    }

    @Override
    public List<Card> getCards() {
        List<Card> cards = new ArrayList<>();
        try (final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM DeckCards");
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int cardId = resultSet.getInt("cardId");
                Card card = new CardBuilder().build();
                if (card != null) {
                    cards.add(card);
                }
            }
        }
        catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);
        } catch (CardBuilder.InvalidCardException e) {
            throw new RuntimeException(e);
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
    }
}
