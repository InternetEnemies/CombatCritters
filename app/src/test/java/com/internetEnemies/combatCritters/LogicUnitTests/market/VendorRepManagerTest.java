package com.internetEnemies.combatCritters.LogicUnitTests.market;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.market.IVendorRepManager;
import com.internetEnemies.combatCritters.Logic.market.VendorManager;
import com.internetEnemies.combatCritters.Logic.market.VendorRepManager;
import com.internetEnemies.combatCritters.data.market.IVendorRepDB;
import com.internetEnemies.combatCritters.objects.VendorRep;
import org.junit.Before;
import org.junit.Test;

public class VendorRepManagerTest {
    IVendorRepManager vendorRepManager;
    IVendorRepDB vendorRepDB;
    @Before
    public void setup() {
        vendorRepDB = mock(IVendorRepDB.class);
        vendorRepManager = new VendorRepManager(vendorRepDB);
    }
    
    @Test
    public void test_addRep(){
        vendorRepManager.addRep(42);
        verify(vendorRepDB).addRep(42);
    }
    
    @Test
    public void test_getRep(){
        when(vendorRepDB.getRep()).thenReturn(123);
        VendorRep rep = vendorRepManager.getVendorRep();
        assertEquals(123/VendorRepManager.XP_PER_LEVEL, rep.level());
        assertEquals(123, rep.currentXp());
    }
}
