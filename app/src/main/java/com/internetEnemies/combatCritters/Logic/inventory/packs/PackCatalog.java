/**
 * PackCatalog.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-06
 *
 * @PURPOSE:    Temporary accessor for packs in the database
 *              Will be replaced in iteration 2
 */

package com.internetEnemies.combatCritters.Logic.inventory.packs;

import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.PackCardDatabase;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.List;
/**
 *
 * Temporary accessor for packs in the database
 * Will be replaced in next iteration with marketplace.
 */

public class PackCatalog implements IPackCatalog {
    private final IRegistry<Pack> PackDB;

    public PackCatalog(){this(PackCardDatabase.getInstance().getPackDB());}

    public PackCatalog(IRegistry<Pack> PackDB) {
        this.PackDB = PackDB;
    }

    @Override
    public Pack getPack(int id){
        return PackDB.getSingle(id);
    }
    @Override
    public List<Pack> getListOfPacks(){
        return PackDB.getAll();
    }
}
