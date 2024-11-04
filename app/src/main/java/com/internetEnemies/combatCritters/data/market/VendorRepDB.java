package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.data.hsqldb.HSQLDBUserModel;
import com.internetEnemies.combatCritters.data.hsqldb.dataHandlers.GenericSQLOperations;
import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;

public class VendorRepDB extends HSQLDBUserModel implements IVendorRepDB {
    private final VendorDetails vendorDetails;

    public VendorRepDB(String dbPath, User user, VendorDetails vendorDetails) {
        super(dbPath, user);
        this.vendorDetails = vendorDetails;
    }

    @Override
    public void addRep(int amount) {
        execute(
                GenericSQLOperations.update(),
                VendorRepSQL.updateRep(vendorDetails.id(), getUser().getId(), amount),
                "Failed to update vendor reputation"
        );
    }

    @Override
    public int getRep() {
        return execute(
                GenericSQLOperations.query(VendorRepHelper::fromResultSet),
                VendorRepSQL.getRep(vendorDetails.id(), getUser().getId()),
                "Failed to get vendor reputation"
        );
    }
}
