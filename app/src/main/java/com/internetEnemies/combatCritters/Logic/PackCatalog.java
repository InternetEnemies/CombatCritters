package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.PackCardDatabase;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.Iterator;

/**
 *
 * Temporary accessor for packs in the database
 * Will be replaced in next iteration with marketplace.
 */

public class PackCatalog {
    private final IRegistry<Pack> PackDB;

    public PackCatalog(){this(PackCardDatabase.getInstance().getPackDB());}

    public PackCatalog(IRegistry<Pack> PackDB) {
        this.PackDB = PackDB;
    }

    public Pack getPack(int id){
        return (Pack) PackDB.getSingle(id);
    }
    public Iterator<Pack> getListOfPacks(){
        return PackDB.iterator();
    }
}
