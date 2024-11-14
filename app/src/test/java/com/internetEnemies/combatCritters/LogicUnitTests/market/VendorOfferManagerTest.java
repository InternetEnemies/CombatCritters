package com.internetEnemies.combatCritters.LogicUnitTests.market;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.market.IVendorOfferManager;
import com.internetEnemies.combatCritters.Logic.market.VendorOfferManager;
import com.internetEnemies.combatCritters.data.market.IVendorOfferRegistry;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import com.internetEnemies.combatCritters.objects.market.VendorOfferCreator;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class VendorOfferManagerTest {
    IVendorOfferManager vendorOfferManager;
    IVendorOfferRegistry vendorOfferRegistry;
    @Before
    public void setup() {
        vendorOfferRegistry = mock(IVendorOfferRegistry.class);
        vendorOfferManager = new VendorOfferManager(vendorOfferRegistry);
    }
    
    @Test
    public void test_createOffer(){
        VendorOfferCreator offer = new VendorOfferCreator(mock(ItemStack.class), List.of(mock(ItemStack.class)),0);
        when(vendorOfferRegistry.createVendorOffer(1,offer)).thenReturn(VendorTransaction.of(1,offer.tx(),offer.rx()));
        VendorTransaction transaction = vendorOfferManager.createVendorOffer(offer, 1);
        assertNotNull(transaction);
        assertEquals(1, transaction.getId());
    }
}
