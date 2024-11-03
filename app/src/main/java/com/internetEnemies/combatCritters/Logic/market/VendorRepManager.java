package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.data.market.IVendorRepDB;
import com.internetEnemies.combatCritters.objects.VendorRep;

public class VendorRepManager implements IVendorRepManager {
    private final IVendorRepDB vendorRepDB;
    public final static int XP_PER_LEVEL = 100;

    public VendorRepManager(IVendorRepDB vendorRepDB) {
        this.vendorRepDB = vendorRepDB;
    }
    @Override
    public void addRep(int amount) {
        this.vendorRepDB.addRep(amount);
    }

    @Override
    public VendorRep getVendorRep() {
        int total = this.vendorRepDB.getRep();
        int level = total / XP_PER_LEVEL;
        
        return new VendorRep(
                total,
                level,
                (level+1) * XP_PER_LEVEL,
                level * XP_PER_LEVEL
        );
    }
}
