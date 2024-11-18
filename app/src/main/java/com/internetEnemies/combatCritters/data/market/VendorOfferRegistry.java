package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.data.hsqldb.HSQLDBModel;
import com.internetEnemies.combatCritters.data.hsqldb.dataHandlers.GenericSQLOperations;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.ResultListExtractor;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SingleResultExtractor;
import com.internetEnemies.combatCritters.data.hsqldb.transactions.TransactionsDB;
import com.internetEnemies.combatCritters.objects.DiscountTransaction;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import com.internetEnemies.combatCritters.objects.market.DiscountOfferCreator;
import com.internetEnemies.combatCritters.objects.market.VendorOfferCreator;

import java.sql.Connection;
import java.sql.SQLException;
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

    @Override
    public List<VendorTransaction> getRandomStandardOffers(int vendorid, int amount) {
        return execute(
                GenericSQLOperations.query(ResultListExtractor.getListExtractor(vendorOfferHelper::fromResultSet)),
                VendorOfferSQL.getRandomStandardOffers(vendorid, amount),
                "Failed to get random standard offers"
                );
    }

    @Override
    public List<VendorTransaction> getRandomSpecialOffers(int vendorid, int amount) {
        return execute(
                GenericSQLOperations.query(ResultListExtractor.getListExtractor(vendorOfferHelper::fromResultSet)),
                VendorOfferSQL.getRandomSpecialOffers(vendorid, amount),
                "Failed to get random special offers"
        );
    }

    @Override
    public void activateSpecials(List<Integer> ids) {
        execute(
                GenericSQLOperations.update(),
                VendorOfferSQL.activateSpecials(ids),
                "Failed to activate special offers"
        );
    }

    @Override
    public void resetDiscounts(int vendorid) {
        //get list of discount offers
        List<Integer> discountIds = execute(
                GenericSQLOperations.query(ResultListExtractor.getListExtractor(rs -> rs.getInt(1))),
                VendorOfferSQL.getDiscountIds(vendorid),
                "failed to get discount ids"
        );
        try (Connection connection = connection()) { //? this is way cleaner than using the new methods in this case
            connection.setAutoCommit(false);
            //remove items
            VendorOfferSQL.removeDiscountItems(vendorid).getStatement(connection).execute();
            //remove discount offer
            VendorOfferSQL.removeDiscountOffers(vendorid).getStatement(connection).execute();
            //remove vendor offer
            VendorOfferSQL.removeDiscountVendorOffer(vendorid).getStatement(connection).execute();
            //remove transaction
            VendorOfferSQL.deleteTransactionsById(discountIds).getStatement(connection).execute();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to reset discounts", e);
        }
    } // this should probably be a for loop (more accurately a transaction executor)

    @Override
    public void resetSpecials(int vendorid) {
        execute(
                GenericSQLOperations.update(),
                VendorOfferSQL.resetSpecials(vendorid),
                "Failed to reset special offers"
        );
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
