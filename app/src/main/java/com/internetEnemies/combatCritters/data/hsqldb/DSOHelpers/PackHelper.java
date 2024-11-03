package com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers;

import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.IConnectionFactory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardSlot;
import com.internetEnemies.combatCritters.Logic.inventory.packs.CardSlotBuilder;
import com.internetEnemies.combatCritters.objects.Pack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * PackHelper.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/7/24
 *
 * @PURPOSE:    helper for sql to create pack objects
 */
public class PackHelper {
    
    private final IConnectionFactory connectionFactory;
    
    public PackHelper(IConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * get a Pack from the result set
     * @param rs ResultSet to use
     * @param connection connection to use
     * @return Pack object
     */
    public static Pack packFromResultSet(ResultSet rs, Connection connection) throws SQLException {
        int id = rs.getInt("id");
        return new Pack(
                id,
                rs.getString("name"),
                rs.getString("image"),
                getCardSlots(id, connection),
                getPackCards(id, connection)
        );

    }

    /**
     * get Pack from result set using instance connection
     */
    public Pack packFromResult(ResultSet rs) throws SQLException {
        return packFromResultSet(rs, connectionFactory.getConnection());
    }


    /**
     * get cards in a pack from its id
     * @param id id of the pack to get from
     * @param connection connection to use
     * @return list of Cards
     */
    public static List<Card> getPackCards(int id, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM PackCards INNER JOIN Cards ON PackCards.cardId = Cards.id WHERE packId = ?");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        List<Card> cards = new ArrayList<>();
        while(rs.next()) {
            cards.add(CardHelper.cardFromResultSet(rs));
        }
        return cards;
    }

    /**
     * get the card slots of a pack from its id
     * @param id id of the pack to get from
     * @param connection connection to use
     * @return list of CardSlots
     */
    public static List<CardSlot> getCardSlots(int id, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM CardSlot WHERE packId = ?");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        CardSlotBuilder builder = new CardSlotBuilder();
        List<CardSlot> slots = new ArrayList<>();
        while(rs.next()) {
            //get values
            double common = rs.getDouble("common");
            double uncommon = rs.getDouble("uncommon");
            double rare = rs.getDouble("rare");
            double epic = rs.getDouble("epic");
            double legend = rs.getDouble("legend");
            //check if valid then add value to builder
            if (common > 0) builder.addProbability(common, Card.Rarity.COMMON);
            if (uncommon > 0) builder.addProbability(uncommon, Card.Rarity.UNCOMMON);
            if (rare > 0) builder.addProbability(rare, Card.Rarity.RARE);
            if (epic > 0) builder.addProbability(epic, Card.Rarity.EPIC);
            if (legend > 0) builder.addProbability(legend, Card.Rarity.LEGENDARY);
            slots.add(builder.build());
            builder.reset();
        }
        return slots;
    }

    /**
     * get a pack from its id
     * @param packId id of the pack to get
     * @param connection connection to use
     * @return Pack object with the id
     */
    public static Pack fromId(int packId,Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM PACKS WHERE id = ?");
        statement.setInt(1,packId);
        ResultSet rs = statement.executeQuery();
        rs.next();
        return packFromResultSet(rs, connection);
    }
}
