package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.data.hsqldb.HSQLDBModel;
import com.internetEnemies.combatCritters.data.hsqldb.dataHandlers.GenericSQLOperations;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SingleResultExtractor;
import com.internetEnemies.combatCritters.data.hsqldb.transactions.TransactionsDB;
import com.internetEnemies.combatCritters.objects.DiscountTransaction;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import com.internetEnemies.combatCritters.objects.market.DiscountOfferCreator;
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
    private static final boolean DEFAULT_SPECIAL_STATE = true; // specials default to active
    
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
        VendorTransaction transaction = createAbstract(vendorid, vendorOfferCreator);
        // create standardOffer
        execute(
                GenericSQLOperations.update(),
                VendorOfferSQL.createStandardOffer(transaction.getId()),
                "Failed to create standard offer"
        );
        return transaction;
    }

    @Override
    public VendorTransaction createSpecialOffer(int vendorid, VendorOfferCreator vendorOfferCreator) {
        VendorTransaction transaction = createAbstract(vendorid, vendorOfferCreator);
        
        execute(
                GenericSQLOperations.update(),
                VendorOfferSQL.createSpecialOffer(transaction.getId(),DEFAULT_SPECIAL_STATE),
                "Failed to create special offer"
        );
        
        return transaction;
    }

    @Override
    public DiscountTransaction createDiscountOffer(int vendorid, DiscountOfferCreator discountOfferCreator) {
        // create new transaction / vendor offer
        VendorTransaction parent = getVendorOffer(vendorid, discountOfferCreator.parentId());
        VendorOfferCreator abstractCreator = new VendorOfferCreator(
                parent.getReceive().getItem(),
                discountOfferCreator.discountedGive(),
                discountOfferCreator.level()
        );
        VendorTransaction created = createAbstract(vendorid, abstractCreator);
        // create discount entry
        execute(
                GenericSQLOperations.update(),
                VendorOfferSQL.createDiscountOffer(created.getId(),discountOfferCreator.parentId(), discountOfferCreator.discount()),
                "Failed to create discount offer"
        );
        return new DiscountTransaction(parent, created, discountOfferCreator.discount());
    }

    /**
     * create the abstract vendor offer db object 
     * @param vendorid id of the parent vendor
     * @param vendorOfferCreator creation details
     * @return the newly created vendor transaction
     */
    private VendorTransaction createAbstract(int vendorid, VendorOfferCreator vendorOfferCreator) {
        // create new transaction
        int tid = transactionsDB.createTransaction(vendorOfferCreator.tx(), List.of(vendorOfferCreator.rx()));
        // create offer
        execute(
                GenericSQLOperations.update(),
                VendorOfferSQL.createVendorOffer(tid, vendorid, vendorOfferCreator.level()),
                "Failed to create vendor offer"
        );
        return getVendorOffer(vendorid, tid);
    }
}
