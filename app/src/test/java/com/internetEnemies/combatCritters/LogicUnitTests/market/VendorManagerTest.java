package com.internetEnemies.combatCritters.LogicUnitTests.market;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.market.IVendor;
import com.internetEnemies.combatCritters.Logic.market.IVendorFactory;
import com.internetEnemies.combatCritters.Logic.market.IVendorManager;
import com.internetEnemies.combatCritters.Logic.market.VendorManager;
import com.internetEnemies.combatCritters.data.hsqldb.IVendorDB;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class VendorManagerTest {
    IVendorManager vendorManager;
    IVendorDB vendorDB;
    IVendorFactory vendorFactory;
    
    @Before
    public void setup() {
        vendorDB = mock(IVendorDB.class);
        vendorFactory = mock(IVendorFactory.class);
        vendorManager = new VendorManager(vendorDB, vendorFactory);
    }
    
    @Test
    public void test_getVendor(){
        when(vendorFactory.getVendor(any())).thenReturn(mock(IVendor.class));
        
        when(vendorDB.getVendor(1)).thenReturn(new VendorDetails(1, "","", 3600));
        
        assertNotNull(vendorManager.getVendor(1));
    }
    
    @Test
    public void test_getVendors(){
        when(vendorFactory.getVendor(any())).thenReturn(mock(IVendor.class));
        when(vendorDB.getAllVendors()).thenReturn(List.of(new VendorDetails(1, "","", 3600)));
        assertNotNull(vendorManager.getVendors().getFirst());
    }
}
