package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.data.hsqldb.HSQLDBModel;
import com.internetEnemies.combatCritters.data.hsqldb.dataHandlers.GenericSQLOperations;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SingleResultExtractor;
import com.internetEnemies.combatCritters.data.hsqldb.transactions.TransactionsDB;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import com.internetEnemies.combatCritters.objects.market.VendorOfferCreator;

import java.util.List;

/**
 * VendorOfferRegistry.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/14/24
 * 
 * @PURPOSE:    manage all vendor offers
 */
public class VendorOfferRegistry extends HSQLDBModel implements IVendorOfferRegistry {
    private final VendorOfferHelper vendorOfferHelper;
    private final TransactionsDB transactionsDB;
    public VendorOfferRegistry(String dbPath) {
        super(dbPath);
        this.transactionsDB = new TransactionsDB(this::connection);
        this.vendorOfferHelper = new VendorOfferHelper(this::connection);
    }

    @Override
    public VendorTransaction getVendorOffer(int vendorid, int offerid) {
        return execute(
                GenericSQLOperations.query(SingleResultExtractor.getSingleResultExtractor(vendorOfferHelper::fromResultSet)),
                VendorOfferSQL.getVendorOffer(offerid, vendorid, Integer.MAX_VALUE),// should probably make a seperate statement for this lmao, whatever tho this code is like 2 weeks late
                "Error getting VendorOffer"
        );
    }

    @Override
    public VendorTransaction createVendorOffer(int vendorid, VendorOfferCreator vendorOfferCreator) {
        // create new transaction
        int tid = transactionsDB.createTransaction(vendorOfferCreator.tx(), List.of(vendorOfferCreator.rx()));
        // create offer
        execute(
                GenericSQLOperations.update(),
                VendorOfferSQL.createVendorOffer(tid,vendorid, vendorOfferCreator.level()),
                "Failed to create vendor offer"
        );
        // create standardOffer
        execute(
                GenericSQLOperations.update(),
                VendorOfferSQL.createStandardOffer(tid),
                "Failed to create standard offer"
        );
        return getVendorOffer(tid, vendorid);
    }
}
