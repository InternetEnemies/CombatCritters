package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.PackHelper;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.objects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * PackInventoryHSQLDB.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-13
 *
 * @PURPOSE:    sql db implementation for pack inventory
 */
public class PackInventoryHSQLDB extends HSQLDBUserModel implements IPackInventory {

    public PackInventoryHSQLDB(String dbPath, User user) {
        super(dbPath, user);
    }
    @Override
    public int getPackAmount(Pack pack) {
        int count;
        try (Connection connection = this.connection()){
            PreparedStatement statement = connection.prepareStatement("SELECT amount FROM PackInventory WHERE packId = ?");
            statement.setInt(1,pack.getId());
            ResultSet rs = statement.executeQuery();

            if (rs.next()) { // if this pack exists in the inventory
                count = rs.getInt("amount");
            } else {
                count = 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error when getting the amount of pack owned");
        }
        return count;
    }

    @Override
    public void addPack(Pack pack) {
        int currAmount = getPackAmount(pack);
        try (Connection connection = this.connection()){
            PreparedStatement stmt;
            if (currAmount >0) { // update if pack found in inv, add it if it doesnt
                stmt = connection.prepareStatement("UPDATE PackInventory set amount = amount + 1 WHERE packId = ?");
            } else {
                stmt = connection.prepareStatement("INSERT INTO PackInventory (packId, amount) VALUES (?,1)");
            }
            stmt.setInt(1,pack.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error while adding pack to inventory",e);
        }
    }

    @Override
    public void addPacks(List<Pack> packs) {
        for (Pack pack : packs) {
            addPack(pack);
        } // there are better ways to do this, but this'll work
    }

    @Override
    public void removePack(Pack pack, int amount) {
        assert amount > 0;

        int currAmount = getPackAmount(pack);
        try (Connection connection = this.connection()){
            PreparedStatement stmt;
            if(currAmount <= amount) {
                // fully remove
                // ? note if pack is not owned this wont do anything
                stmt = connection.prepareStatement("DELETE FROM PackInventory WHERE packId = ?");
                stmt.setInt(1, pack.getId());
            } else {// curr > amount
                //partial remove
                stmt = connection.prepareStatement("UPDATE PackInventory set amount = amount - ? WHERE packId = ?");
                stmt.setInt(1, amount);
                stmt.setInt(2, pack.getId());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while removing pack from inventory",e);
        }
    }

    @Override
    public void removePack(Pack pack) {
        removePack(pack, 1);
    }

    @Override
    public List<ItemStack<Pack>> getPacks() {
        List<ItemStack<Pack>> packs = new ArrayList<>();
        try (Connection connection = this.connection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Packs LEFT JOIN PackInventory ON Packs.id = PackInventory.packId");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) { // for each of the result packs
                Pack pack = PackHelper.packFromResultSet(rs, connection);
                packs.add(new ItemStack<>(pack,rs.getInt("amount")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("failed getting list of packs from inventory",e);
        }
        return packs;
    }
}
