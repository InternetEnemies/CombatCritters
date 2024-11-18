package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.Logic.market.IVendorRepManager;
import com.internetEnemies.combatCritters.Logic.market.IVendorRepManagerFactory;
import com.internetEnemies.combatCritters.data.hsqldb.HSQLDBUserModel;
import com.internetEnemies.combatCritters.data.hsqldb.dataHandlers.GenericSQLOperations;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.ResultListExtractor;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SingleResultExtractor;
import com.internetEnemies.combatCritters.data.hsqldb.transactions.DiscountTransactionDetails;
import com.internetEnemies.combatCritters.data.hsqldb.transactions.DiscountTransactionDetailsHelper;
import com.internetEnemies.combatCritters.objects.DiscountTransaction;
import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;

import java.util.*;

public class VendorOfferDB extends HSQLDBUserModel implements IVendorOfferDB {
    private final VendorDetails vendorDetails;
    private final VendorOfferHelper vendorOfferHelper;
    private final IVendorRepManagerFactory vendorRepManagerFactory;

    public VendorOfferDB(String dbPath, User user, VendorDetails vendorDetails, IVendorRepManagerFactory vendorRepManagerFactory) {
        super(dbPath, user);
        this.vendorDetails = vendorDetails;
        this.vendorOfferHelper = new VendorOfferHelper(this::connection);
        this.vendorRepManagerFactory = vendorRepManagerFactory;
    }

    @Override
    public VendorTransaction getVendorOffer(int offerId) {
        return execute(
                GenericSQLOperations.query(SingleResultExtractor.getSingleResultExtractor(vendorOfferHelper::fromResultSet)),
                VendorOfferSQL.getVendorOffer(offerId,vendorDetails.id(), getLevel()),
                "Error getting vendor offer"
        );
    }

    @Override
    public List<VendorTransaction> getAllVendorOffers() {
        return execute(
                GenericSQLOperations.query(ResultListExtractor.getListExtractor(vendorOfferHelper::fromResultSet)),
                VendorOfferSQL.getVendorOffers(vendorDetails.id(),getLevel()),
                "Error getting vendor offers"
        );
    }

    @Override
    public List<VendorTransaction> getSpecialOffers() {
        return execute(
                GenericSQLOperations.query(ResultListExtractor.getListExtractor(vendorOfferHelper::fromResultSet)),
                VendorOfferSQL.getVendorSpecials(vendorDetails.id(),getLevel()),
                "Error getting vendor special offers"
        );
    }

    @Override
    public List<DiscountTransaction> getDiscountOffers() {
        //? This is kinda inefficient, it may need to be improved at some point. main issue is the loop over an sql call
        //? the current design is intended to remain as O(n), a potential improvement would involve a more specialized result handler 
        //Get Discount objects
        List<DiscountTransactionDetails> discountDetails = execute(
                GenericSQLOperations.query(ResultListExtractor.getListExtractor(DiscountTransactionDetailsHelper::fromResultSet)),
                VendorOfferSQL.getVendorDiscounts(vendorDetails.id(),getLevel()),
                "Error getting discount offers"
        );
        List<DiscountTransaction> discounts;
        if(!discountDetails.isEmpty()) {
            discounts = getDiscountTransactions(discountDetails);
        } else {//if there are no results return empty list
            discounts = List.of();
        }
        return discounts;
    }// maybe figuring out the single sql would have been easier, whatever lol

    private List<DiscountTransaction> getDiscountTransactions(List<DiscountTransactionDetails> discountTransactions) {
        List<Integer> discountIds = new LinkedList<>();
        List<Integer> parentIds = new LinkedList<>();
        List<DiscountTransaction> discounts;
        
        for(DiscountTransactionDetails discountTransaction : discountTransactions) {
            discountIds.add(discountTransaction.tid());
            parentIds.add(discountTransaction.parent());
        }
        //Get Parent Transactions
        Map<Integer, VendorTransaction> parentMap = getOffersMap(parentIds);
        //Get Discount Transaction
        Map<Integer, VendorTransaction> discountMap = getOffersMap(discountIds);
        
        // create discounts for valid discounts
        discounts = new ArrayList<>();
        for ( DiscountTransactionDetails details: discountTransactions) {
            VendorTransaction parent = parentMap.get(details.parent());
            VendorTransaction discount = discountMap.get(details.tid());
            if(parent == null || discount == null) continue; // if either of these are null the user does not have access to the discount
            discounts.add(new DiscountTransaction(parent, discount, details.discount()));
        }
        return discounts;
    }

    private int getLevel() {
        IVendorRepManager repManager = vendorRepManagerFactory.create(getUser(), vendorDetails);
        return repManager.getVendorRep().level();
    }
    
    private Map<Integer, VendorTransaction> getOffersMap(List<Integer> offerIds) {
        Map<Integer, VendorTransaction> offersMap = new HashMap<>();
        execute(
                GenericSQLOperations.query(ResultListExtractor.getListExtractor(vendorOfferHelper::fromResultSet)),
                VendorOfferSQL.getVendorOffersFromIds(vendorDetails.id(), getLevel(), offerIds),
                "Error getting vendor offer list"
        )
                .forEach(offer -> offersMap.put(offer.getId(), getVendorOffer(offer.getId())));
        return offersMap;
    }
}
