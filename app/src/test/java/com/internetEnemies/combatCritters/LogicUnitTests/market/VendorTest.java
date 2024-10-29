package com.internetEnemies.combatCritters.LogicUnitTests.market;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.market.IVendor;
import com.internetEnemies.combatCritters.Logic.market.Vendor;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;
import org.junit.Before;
import org.junit.Test;

public class VendorTest {
    IVendor vendor;
    VendorDetails vendorDetails;
    @Before
    public void setup() {
        vendorDetails = new VendorDetails(1,"","",3600);
        vendor = new Vendor(vendorDetails);
    }
    
    @Test
    public void test_getDetails(){
        assertEquals(vendorDetails, vendor.getDetails());
    }
}
