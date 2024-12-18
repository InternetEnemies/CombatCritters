package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.data.exception.NXDeckException;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.CardHelper;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.sql.Connection;
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
public class DeckHSQLDB extends HSQLDBModel implements IDeck {

    private final DeckDetails deckDetails;
    private final List<Card> deck;

    public DeckHSQLDB(final String dbPath, final DeckDetails deckDetails) throws NXDeckException {
        super(dbPath);
        this.deckDetails = deckDetails;
        this.deck = new ArrayList<>();

        //Check that this deck exists
        checkDeckExists(deckDetails);
        //load deck
        loadDeck();
    }

    private void checkDeckExists(DeckDetails deckDetails) throws NXDeckException {
        try (Connection connection = this.connection()){
            final PreparedStatement statement = connection.prepareStatement("SELECT id FROM Decks WHERE id = ?");
            statement.setInt(1, deckDetails.getId());
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new NXDeckException("Deck with ID " + deckDetails.getId() + " doesn't exists.");
            }
        } catch(SQLException e){
            throw new RuntimeException("Error while checking if deck exists",e);
        }
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
        try  (Connection connection = this.connection()){
            final PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM DeckCards WHERE deckId = ? AND cardId = ?");
            statement.setInt(1, this.deckDetails.getId());
            statement.setInt(2, card.getId());
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
        try  (Connection connection = this.connection()){
            final PreparedStatement statement = connection.prepareStatement("SELECT id, name, image, playCost, rarity, type, damage, health, effectId,description, COUNT(*) as count FROM Cards INNER JOIN DeckCards ON DeckCards.cardId = Cards.id WHERE deckId = ? GROUP BY Cards.id");//certainly better ways of doing this
            statement.setInt(1, this.deckDetails.getId());
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

    @Override
    public void setCards(List<Card> cards) {
        this.deck.clear();
        this.deck.addAll(cards);
        storeDeck();
    }

    private void loadDeck(){
        this.deck.clear();
        try  (Connection connection = this.connection()){
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
        try (Connection connection = this.connection()){
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

}
