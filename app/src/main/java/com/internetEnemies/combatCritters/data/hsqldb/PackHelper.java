package com.internetEnemies.combatCritters.data.hsqldb;

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
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM PackCards WHERE packId = ?");
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
            builder.addProbability(rs.getDouble("common"), Card.Rarity.COMMON);
            builder.addProbability(rs.getDouble("uncommon"), Card.Rarity.UNCOMMON);
            builder.addProbability(rs.getDouble("rare"), Card.Rarity.RARE);
            builder.addProbability(rs.getDouble("epic"), Card.Rarity.EPIC);
            builder.addProbability(rs.getDouble("legend"), Card.Rarity.LEGENDARY);
            slots.add(builder.build());
            builder.reset();
        }
        return slots;
    }
}
