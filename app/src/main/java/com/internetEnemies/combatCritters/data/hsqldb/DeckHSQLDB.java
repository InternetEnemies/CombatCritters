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
import java.util.List;
import java.util.Map;

public class DeckHSQLDB implements IDeck {

    private final String dbPath;

    public DeckHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
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
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM DECK WHERE SLOT=?");
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
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO DECK VALUES(?, ?)");
            statement.setInt(1, card.getId());
            statement.setString(2, card.getName());
            statement.setString(3, card.getImage());
            statement.setInt(4, card.getPlayCost());
            statement.setInt(5, card.getRarity().ordinal());

            statement.executeUpdate();
        }
        catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);  //temp exception
        }

    }

    @Override
    public void removeCard(int slot) {

    }

    @Override
    public int countCard(Card card) {
        return 0;
    }

    @Override
    public Map<Card, Integer> countCards() {
        return null;
    }

    @Override
    public DeckDetails getInfo() {
        return null;
    }

    @Override
    public List<Card> getCards() {
        return null;
    }

    @Override
    public int getTotalCards() {
        return 0;
    }
}
