package com.internetEnemies.combatCritters;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.market.IMarketPurchaseHandler;
import com.internetEnemies.combatCritters.Logic.market.IVendorRepManager;
import com.internetEnemies.combatCritters.Logic.market.MarketPurchaseHandler;
import com.internetEnemies.combatCritters.Logic.market.VendorRepManager;
import com.internetEnemies.combatCritters.Logic.transaction.ITransactionHandler;
import com.internetEnemies.combatCritters.Logic.transaction.TransactionHandler;
import com.internetEnemies.combatCritters.Logic.transaction.participant.IUserParticipantFactory;
import com.internetEnemies.combatCritters.Logic.transaction.participant.SystemParticipant;
import com.internetEnemies.combatCritters.Logic.transaction.participant.UserParticipant;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.CurrencyInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.PackInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.market.IVendorOfferDB;
import com.internetEnemies.combatCritters.data.market.VendorOfferDB;
import com.internetEnemies.combatCritters.data.market.VendorRepDB;
import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class MarketPurchaseHandlerITest {
    IMarketPurchaseHandler marketPurchaseHandler;
    ITransactionHandler transactionHandler;
    IVendorRepManager vendorRepManager;
    IVendorOfferDB vendorOfferDB;
    
    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        User dummy = TestUtils.getDummyUser(path);
        IUserParticipantFactory userParticipantFactory = user -> new UserParticipant(
                new CardInventoryHSQLDB(path, user),
                new PackInventoryHSQLDB(path, user), 
                new CurrencyInventoryHSQLDB(path, user)
        );
        VendorDetails details = new VendorDetails(1,"","",3600);
        transactionHandler = new TransactionHandler(userParticipantFactory.createUserParticipant(dummy),new SystemParticipant());
        vendorRepManager = new VendorRepManager(new VendorRepDB(path, dummy, details));
        marketPurchaseHandler = new MarketPurchaseHandler(transactionHandler, vendorRepManager);
        TestUtils.initFull(path);
        
        vendorOfferDB = new VendorOfferDB(path, dummy, details, (_,_) -> vendorRepManager);
    }
    
    //this should create then use a transaction instead
    @Test
    public void test_purchase(){
        VendorTransaction transaction = vendorOfferDB.getAllVendorOffers().getFirst();
        int change = marketPurchaseHandler.purchase(transaction);
        assertTrue(change > 0);
    }
}
