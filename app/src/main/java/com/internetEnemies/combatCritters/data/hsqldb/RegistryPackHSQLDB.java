package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.Logic.inventory.packs.PackBuilder;
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
public class RegistryPackHSQLDB extends HSQLDBModel implements IRegistry<Pack> {

    public RegistryPackHSQLDB(final String dbPath) {
        super(dbPath);
    }

    @Override
    public Pack getSingle(int id) {
        Pack pack;
        try (Connection connection = this.connection()){
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
        try (Connection connection = this.connection()){
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

    /**
     * add a new pack to the database
     * @param pack Pack object to create from (id is ignored)
     * @return Pack Object created
     */
    public Pack add(Pack pack) {
        //! this should probably use a transaction if we want to do this right
        Pack outPack;
        try (Connection connection = this.connection()){
            // CREATE PACK
            int id = createPack(pack, connection);
            // ADD CARD SET
            addPackCards(id, pack.getSetList(), connection);
            // ADD CARD SLOTS
            addCardSlots(id, pack.getProbabilityList(), connection);

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

    /**
     * add a Pack to the packs table
     * @param pack pack to add (id is ignored)
     * @param connection connection to use
     * @return id of the pack that was created
     */
    private int createPack(Pack pack, Connection connection) throws SQLException {
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

    /**
     * add cards to the PackCards table
     * @param packId pack to add cards to
     * @param cards cards to add
     * @param connection connection to use
     */
    private void addPackCards(int packId, List<Card> cards, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO PackCards (packId, cardId) VALUES (?,?)");
        statement.setInt(1,packId);
        for (Card card : cards) {
            statement.setInt(2,card.getId());
            statement.executeUpdate();
        }
    }

    /**
     * add cardslots to the pack
     * @param packId pack to add to
     * @param slots slots to add
     * @param connection connection to use
     */
    private void addCardSlots(int packId, List<CardSlot> slots, Connection connection) throws SQLException {
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

            //the null pointer warnings here cannot actually happen since we create the map above
            statement.setDouble(3,rarityMap.getOrDefault(Card.Rarity.COMMON, -1e0));
            statement.setDouble(4,rarityMap.getOrDefault(Card.Rarity.UNCOMMON, -1e0));
            statement.setDouble(5,rarityMap.getOrDefault(Card.Rarity.RARE, -1e0));
            statement.setDouble(6,rarityMap.getOrDefault(Card.Rarity.EPIC, -1e0));
            statement.setDouble(7,rarityMap.getOrDefault(Card.Rarity.LEGENDARY, -1e0));

            position++;
        }

    }
}
