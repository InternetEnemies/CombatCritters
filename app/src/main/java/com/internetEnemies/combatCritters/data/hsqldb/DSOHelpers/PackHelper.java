package com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardSlot;
import com.internetEnemies.combatCritters.Logic.CardSlotBuilder;
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

    public static Pack fromId(int packId,Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM PACKS WHERE id = ?");
        statement.setInt(1,packId);
        ResultSet rs = statement.executeQuery();
        rs.next();
        return packFromResultSet(rs, connection);
    }
}
