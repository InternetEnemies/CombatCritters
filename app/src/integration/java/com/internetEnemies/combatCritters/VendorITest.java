package com.internetEnemies.combatCritters;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.market.IVendor;
import com.internetEnemies.combatCritters.Logic.market.Vendor;
import com.internetEnemies.combatCritters.Logic.market.VendorRepManager;
import com.internetEnemies.combatCritters.data.market.IVendorOfferDB;
import com.internetEnemies.combatCritters.data.market.IVendorRepDB;
import com.internetEnemies.combatCritters.data.market.VendorOfferDB;
import com.internetEnemies.combatCritters.data.market.VendorRepDB;
import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class VendorITest {
    IVendorOfferDB vendorOfferDB;
    IVendor vendor;
    IVendorRepDB vendorRepDB;
    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        TestUtils.initFull(path);
        User dummy = TestUtils.getDummyUser(path);
        VendorDetails details = new VendorDetails(1,"","",1);
        this.vendorRepDB = new VendorRepDB(path, dummy, details);
        vendorOfferDB = new VendorOfferDB(path, dummy, details,(_,_)-> new VendorRepManager(vendorRepDB));
        vendor = new Vendor(details,vendorOfferDB);
    }

    //todo add the transaction then use the id see (waiting on #154)
    @Test
    public void test_getOffer(){
        VendorTransaction transaction = vendor.getOffer(19);
        assertEquals(19, transaction.getId());
    }
    
    @Test
    public void test_getOffers(){
        List<VendorTransaction> transactions = vendor.getOffers();
        assertFalse(transactions.isEmpty());
    }
    
    @Test
    public void test_newOffersOnLevelUp(){
        int init = vendor.getOffers().size();
        vendorRepDB.addRep(1000000);
        int after = vendor.getOffers().size();
        assertTrue(after>init);
    }
    
    @Test
    public void test_getEmptySpecials(){
        List<VendorTransaction> transactions = vendor.getSpecialOffers();
        assertTrue(transactions.isEmpty());
    }
}
