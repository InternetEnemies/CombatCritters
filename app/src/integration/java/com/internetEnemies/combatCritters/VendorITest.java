package com.internetEnemies.combatCritters;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.market.IVendor;
import com.internetEnemies.combatCritters.Logic.market.Vendor;
import com.internetEnemies.combatCritters.data.market.IVendorOfferDB;
import com.internetEnemies.combatCritters.data.market.VendorOfferDB;
import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class VendorITest {
    IVendorOfferDB vendorOfferDB;
    IVendor vendor;
    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        TestUtils.initFull(path);
        User dummy = TestUtils.getDummyUser(path);
        VendorDetails details = new VendorDetails(1,"","",1);
        vendorOfferDB = new VendorOfferDB(path, dummy, details);
        vendor = new Vendor(details,vendorOfferDB);
    }
    
    @Test
    public void test_getOffer(){
        
    }
}
