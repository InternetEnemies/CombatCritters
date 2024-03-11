package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.CardHelper;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DeckHSQLDB.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/7/24
 *
 * @PURPOSE:    sql implmentation of IDeck
 */
public class DeckHSQLDB implements IDeck {

    private final String dbPath;
    private final DeckDetails deckDetails;
    private final List<Card> deck;

    public DeckHSQLDB(final String dbPath, final DeckDetails deckDetails) throws NXDeckException {
        this.dbPath = dbPath;
        this.deckDetails = deckDetails;
        this.deck = new ArrayList<>();

        //Check that this deck exists
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
        //load deck
        loadDeck();
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }


    @Override
    public Card getCard(int slot) {
        return this.deck.get(slot);
    }

    @Override
    public void addCard(int slot, Card card) {
        this.deck.add(slot,card);
        storeDeck();
    }

    @Override
    public void removeCard(int slot) {
        this.deck.remove(slot);
        storeDeck();
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
            throw new RuntimeException("error while counting a card in the deck", e);
        }
    }

    @Override
    public List<ItemStack<Card>> countCards() {
        List<ItemStack<Card>> cardStacks = new ArrayList<>();
        try (final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT id, name, image, playCost, rarity, type, damage, health, effectId, COUNT(*) as count FROM Cards INNER JOIN DeckCards ON DeckCards.cardId = Cards.id GROUP BY Cards.id");//certainly better ways of doing this
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int count = resultSet.getInt("count");
                ItemStack<Card> itemStack = new ItemStack<>(CardHelper.cardFromResultSet(resultSet), count);
                cardStacks.add(itemStack);
            }
        }
        catch (final SQLException e) {
            throw new RuntimeException("error occurred while counting cards in the deck", e);
        }
        return cardStacks;
    }

    @Override
    public DeckDetails getInfo() {
        return this.deckDetails;
    }

    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(this.deck);
    }

    @Override
    public int getTotalCards() {
        return this.deck.size();
    }

    private void loadDeck(){
        this.deck.clear();
        try (final Connection connection = connection()) {
            String sql = "SELECT * FROM DeckCards INNER JOIN Cards ON Cards.id = DeckCards.cardId WHERE DeckCards.deckId = ? ORDER BY DeckCards.position";
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, this.deckDetails.getId());
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                this.deck.add(CardHelper.cardFromResultSet(resultSet));
            }
        }
        catch (final SQLException e) {
            throw new RuntimeException("error while loading a deck from the database", e);
        }
    }

    private void storeDeck(){
        String deleteSql = "DELETE FROM DeckCards WHERE deckId = ?";
        String createSql = "INSERT INTO DeckCards (cardID, deckID, position) VALUES (?,?,?)";
        try(final Connection connection = connection()) {
            //ideally there would be a transaction here but I'm not in a learning mood so that'll have to wait

            //delete current db copy
            PreparedStatement delete = connection.prepareStatement(deleteSql);
            delete.setInt(1, this.deckDetails.getId());
            delete.executeUpdate();
            //add cards
            int position = 0;
            for ( Card card : this.deck ) {
                PreparedStatement create = connection.prepareStatement(createSql);
                create.setInt(1, card.getId());
                create.setInt(2, this.deckDetails.getId());
                create.setInt(3, position++);
                create.executeUpdate();
            }
        } catch (final SQLException e ) {
            throw new RuntimeException("error while storing a deck in the db", e);
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
