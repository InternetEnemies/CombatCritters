package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.objects.battles.Opponent;

import java.util.List;

/**
 * BattleInfoRegistryHSQLDB.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     27/7/24
 *
 * @PURPOSE:    sql implementation of the battleInfo registry
 */

public class BattleInfoRegistryHSQLDB extends HSQLDBModel implements IRegistry<Opponent> {
    public BattleInfoRegistryHSQLDB(String dbPath) {
        super(dbPath);
    }

    @Override
    public Opponent getSingle(int id) {
        return null;
    }

    @Override
    public List<Opponent> getListOf(List<Integer> ids) {
        return null;
    }

    @Override
    public List<Opponent> getAll() {
        return null;
    }

    public Opponent addOpponent(){
        return null;
    }
}
