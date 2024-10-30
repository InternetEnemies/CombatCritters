package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.data.hsqldb.HSQLDBUserModel;
import com.internetEnemies.combatCritters.data.hsqldb.dataHandlers.GenericSQLOperations;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.ResultListExtractor;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SingleResultExtractor;
import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;

import java.util.List;

/**
 * VendorDB.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/29/24
 * 
 * @PURPOSE:    provide a vendor db for a user
 */
public class VendorDB extends HSQLDBUserModel implements IVendorDB {
    public VendorDB(String dbPath, User user) {
        super(dbPath, user);
    }

    @Override
    public VendorDetails getVendor(int id) {
        return execute(
                GenericSQLOperations.query(SingleResultExtractor.getSingleResultExtractor(VendorHelper::fromResultSet)),
                VendorSQL.getVendor(id),
                "Error getting a vendor from id"
                );
    }

    @Override
    public List<VendorDetails> getAllVendors() {
        return execute(
                GenericSQLOperations.query(ResultListExtractor.getListExtractor(VendorHelper::fromResultSet)),
                VendorSQL::getVendors,
                "Error getting a list of vendors"
        );
    }
}
