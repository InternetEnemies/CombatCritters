package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.Logic.market.IVendorRepManager;
import com.internetEnemies.combatCritters.Logic.market.IVendorRepManagerFactory;
import com.internetEnemies.combatCritters.data.hsqldb.HSQLDBUserModel;
import com.internetEnemies.combatCritters.data.hsqldb.dataHandlers.GenericSQLOperations;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.ResultListExtractor;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SingleResultExtractor;
import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;

import java.util.List;

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
        IVendorRepManager repManager = vendorRepManagerFactory.create(getUser(), vendorDetails);
        int level = repManager.getVendorRep().level();
        return execute(
                GenericSQLOperations.query(SingleResultExtractor.getSingleResultExtractor(vendorOfferHelper::fromResultSet)),
                VendorOfferSQL.getVendorOffer(offerId,vendorDetails.id(), level),
                "Error getting vendor offer"
        );
    }

    @Override
    public List<VendorTransaction> getAllVendorOffers() {
        IVendorRepManager repManager = vendorRepManagerFactory.create(getUser(), vendorDetails);
        int level = repManager.getVendorRep().level();
        return execute(
                GenericSQLOperations.query(ResultListExtractor.getListExtractor(vendorOfferHelper::fromResultSet)),
                VendorOfferSQL.getVendorOffers(vendorDetails.id(),level),
                "Error getting vendor offers"
        );
    }
}
