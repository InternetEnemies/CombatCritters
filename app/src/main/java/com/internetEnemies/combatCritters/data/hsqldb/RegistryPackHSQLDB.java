package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.Logic.PackBuilder;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.PackHelper;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardSlot;
import com.internetEnemies.combatCritters.objects.Pack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * RegistryPackHSQLDB.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/7/24
 *
 * @PURPOSE:    sql implementation of the pack registry
 */
public class RegistryPackHSQLDB implements IRegistry<Pack> {
    private final Connection connection;

    public RegistryPackHSQLDB(final String dbPath) {
        try {
            this.connection = HSQLDBUtil.connection(dbPath);
        } catch (SQLException e) {
            throw new RuntimeException("Error while initializing Pack Registry",e);
        }
    }

    @Override
    public Pack getSingle(int id) {
        Pack pack;
        try {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM Packs WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                pack = PackHelper.packFromResultSet(rs,connection);
            } else {
                pack = null;
            }
        }
        catch (final SQLException e) {
            throw new RuntimeException("error getting a single pack", e);
        }
        return pack;
    }

    @Override
    public List<Pack> getAll() {
        List<Pack> packs = new ArrayList<>();
        try {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM Packs");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                packs.add(PackHelper.packFromResultSet(rs,connection));
            }
        } catch (final SQLException e) {
            throw new RuntimeException("error getting all packs", e);
        }
        return packs;
    }

    @Override
    public List<Pack> getListOf(List<Integer> ids) {
        throw new RuntimeException("Not implemented for HSQLDB"); //this should be done at some point or the function should be removed (i dont think it is used)
    }

    public Pack addPack(Pack pack) {
        //! this should probably use a transaction if we want to do this right
        Pack outPack;
        try {
            // CREATE PACK
            int id = createPack(pack);
            // ADD CARD SET
            addPackCards(id, pack.getSetList());
            // ADD CARD SLOTS
            addCardSlots(id, pack.getProbabilityList());

            //Make return pack
            PackBuilder builder = new PackBuilder();
            builder.fromPack(pack);
            builder.setId(id);
            outPack = builder.build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return outPack;
    }

    private int createPack(Pack pack) throws SQLException {
        int id;
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO PACKS (name,image) VALUES (?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        statement.setString(1, pack.getName());
        statement.setString(2, pack.getImage());
        statement.executeUpdate();
        ResultSet created = statement.getGeneratedKeys();
        if(created.next()) {
            id = created.getInt(1);
        } else {
            throw new RuntimeException("Failed to create new pack");
        }
        return id;
    }

    private void addPackCards(int packId, List<Card> cards) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO PackCards (packId, cardId) VALUES (?,?)");
        statement.setInt(1,packId);
        for (Card card : cards) {
            statement.setInt(2,card.getId());
            statement.executeUpdate();
        }
    }

    private void addCardSlots(int packId, List<CardSlot> slots) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO CardSlot (packId, position, common, uncommon, rare, epic, legend) VALUES (?,?,?,?,?,?,?)");
        statement.setInt(1,packId);

        int position = 0;
        for (CardSlot slot : slots) {
            statement.setInt(2,position);
            Map<Card.Rarity, Double> rarityMap = new TreeMap<>();
            slot.getRarityWeights().forEach((key, value) -> {// this kinda sucks
                if(value != null) {
                    rarityMap.put(value, key);
                }
            });

            //the null pointer warnings here cannot actually happen
            statement.setDouble(3,rarityMap.getOrDefault(Card.Rarity.COMMON, -1e0));
            statement.setDouble(4,rarityMap.getOrDefault(Card.Rarity.UNCOMMON, -1e0));
            statement.setDouble(5,rarityMap.getOrDefault(Card.Rarity.RARE, -1e0));
            statement.setDouble(6,rarityMap.getOrDefault(Card.Rarity.EPIC, -1e0));
            statement.setDouble(7,rarityMap.getOrDefault(Card.Rarity.LEGENDARY, -1e0));

            position++;
        }

    }
}
