package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PackInventoryHSQLDB implements IPackInventory {

    private final Connection connection;

    public PackInventoryHSQLDB(String dbPath) {
        try {
            this.connection = HSQLDBUtil.connection(dbPath);
        } catch (SQLException e) {
            throw new RuntimeException("Error in pack inventory initialization",e);
        }
    }
    @Override
    public int getPackAmount(Pack pack) {
        return 0;
    }

    @Override
    public void addPack(Pack pack) {

    }

    @Override
    public void addPacks(List<Pack> packs) {
        for (Pack pack : packs) {
            addPack(pack);
        } // there are better ways to do this, but this'll work
    }

    @Override
    public void removePack(Pack pack, int amount) {

    }

    @Override
    public void removePack(Pack pack) {

    }

    @Override
    public List<ItemStack<Pack>> getPacks() {
        return null;
    }
}
