package com.internetEnemies.combatCritters;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.market.*;
import com.internetEnemies.combatCritters.data.market.*;
import com.internetEnemies.combatCritters.objects.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class VendorManagerITest {
    private IVendorDB vendorDB;
    private IVendorManager vendorManager;
    
    String path;
    
    
    @Before
    public void setup() throws IOException {
        path = TestUtils.getDBPath();
        User dummy = TestUtils.getDummyUser(path);
        vendorDB = new VendorDB(path, dummy);
        vendorManager = new VendorManager(vendorDB,
                new VendorFactory(dummy, (vendorDetails, user) -> new VendorOfferDB(path,user, vendorDetails)));
    }
    
    @Test
    public void test_getVendor(){
        TestUtils.initFull(path);
        IVendor vendor = vendorManager.getVendor(1);
        assertEquals(1, vendor.getDetails().id());
    }
    
    @Test
    public void test_getNXVendor(){
        IVendor vendor = vendorManager.getVendor(1);
        assertNull(vendor);
    }
    
    @Test
    public void test_getNoVendors(){
        List<IVendor> vendors = vendorManager.getVendors();
        assertTrue(vendors.isEmpty());
        
    }
    
    @Test
    public void test_getVendors(){
        TestUtils.initFull(path);
        List<IVendor> vendors = vendorManager.getVendors();
        assertFalse(vendors.isEmpty());
    }
}
