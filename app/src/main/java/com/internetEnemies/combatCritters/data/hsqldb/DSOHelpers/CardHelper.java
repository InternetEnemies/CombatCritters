package com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * CardHelper.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/7/24
 *
 * @PURPOSE:    help the database to make card objects
 */
public class CardHelper {

    /**
     * get a card object form the result set
     * @param rs result set to get
     * @return CritterCard or ItemCard object from the ResultSet
     */
    public static Card cardFromResultSet(final ResultSet rs) throws SQLException {
        final int id = rs.getInt("id");
        final String name = rs.getString("name");
        final String image = rs.getString("image");
        final int playCost = rs.getInt("playCost");
        final int rarity = rs.getInt("rarity");
        final String type = rs.getString("type");
        final String description = rs.getString("description");

        Card card = null;

        Card.Rarity rare = Card.Rarity.values()[rarity];

        switch(type){
            case "critter":
                final int damage = rs.getInt("damage");
                final int health = rs.getInt("health");
                card = new CritterCard(id, name, image, playCost, rare,description, damage, health, null);//abilities not supported
                break;
            case "item":
                final int effectId = rs.getInt("effectId");
                card = new ItemCard(id, name, image, playCost, rare,description, effectId);
                break;
        }
        return card;
    }

    /**
     * get a card from its id
     * @param id id of the card
     * @param connection Connection to use
     * @return Card object
     */
    public static Card fromId(int id, Connection connection) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM CARDS WHERE id = ?");
        statement.setInt(1,id);
        ResultSet rs = statement.executeQuery();
        rs.next();
        return cardFromResultSet(rs);
    }
}
