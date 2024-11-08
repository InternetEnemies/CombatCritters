package com.internetEnemies.combatCritters.LogicUnitTests.market;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.exceptions.NXTransactionException;
import com.internetEnemies.combatCritters.Logic.market.IVendor;
import com.internetEnemies.combatCritters.Logic.market.Vendor;
import com.internetEnemies.combatCritters.data.market.IVendorOfferDB;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class VendorTest {
    IVendor vendor;
    VendorDetails vendorDetails;
    IVendorOfferDB vendorOfferDB;
    @Before
    public void setup() {
        vendorDetails = new VendorDetails(1,"","",3600);
        vendorOfferDB = mock(IVendorOfferDB.class);
        vendor = new Vendor(vendorDetails, vendorOfferDB);
    }
    
    @Test
    public void test_getDetails(){
        assertEquals(vendorDetails, vendor.getDetails());
    }
    
    @Test(expected = NXTransactionException.class)
    public void test_getNXOffer(){
        when(vendorOfferDB.getVendorOffer(-1)).thenReturn(null);
        vendor.getOffer(-1);
    }
    
    @Test
    public void test_getOffer(){
        when(vendorOfferDB.getVendorOffer(1)).thenReturn(new VendorTransaction(1, null,null));
        assertEquals(1, vendor.getOffer(1).getId());
    }
    
    @Test
    public void test_getOffers(){
        when(vendorOfferDB.getAllVendorOffers()).thenReturn(List.of(
                mock(VendorTransaction.class),
                mock(VendorTransaction.class),
                mock(VendorTransaction.class),
                mock(VendorTransaction.class)
                ));
        assertEquals(4, vendor.getOffers().size());
    }
    
    @Test
    public void test_getSpecials(){
        when(vendorOfferDB.getSpecialOffers()).thenReturn(List.of(
                mock(VendorTransaction.class),
                mock(VendorTransaction.class),
                mock(VendorTransaction.class),
                mock(VendorTransaction.class)
        ));
        assertEquals(4, vendor.getSpecialOffers().size());
    }
    
}
