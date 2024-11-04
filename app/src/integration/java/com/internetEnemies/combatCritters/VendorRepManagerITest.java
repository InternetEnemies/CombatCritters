package com.internetEnemies.combatCritters;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.market.IVendorRepManager;
import com.internetEnemies.combatCritters.Logic.market.VendorRepManager;
import com.internetEnemies.combatCritters.data.market.IVendorRepDB;
import com.internetEnemies.combatCritters.data.market.VendorRepDB;
import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class VendorRepManagerITest {
    IVendorRepManager vendorRepManager;
    IVendorRepDB vendorRepDB;
    
    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        User dummy = TestUtils.getDummyUser(path);
        vendorRepDB = new VendorRepDB(path, dummy, new VendorDetails(1,"","",3600));
        vendorRepManager = new VendorRepManager(vendorRepDB);
        TestUtils.initFull(path);
    }
    
    @Test
    public void test_addGetRep(){
        int rep = vendorRepDB.getRep(); 
        assertEquals(0, rep);// check zero on NX rep
        vendorRepManager.addRep(10);
        rep = vendorRepDB.getRep();
        assertEquals(10, rep); // check after init
        vendorRepManager.addRep(10);
        rep = vendorRepDB.getRep();
        assertEquals(20, rep); // check after update
    }
}
