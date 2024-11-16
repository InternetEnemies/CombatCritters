package com.internetEnemies.combatCritters;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.market.*;
import com.internetEnemies.combatCritters.data.market.*;
import com.internetEnemies.combatCritters.objects.*;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;
import com.internetEnemies.combatCritters.objects.market.VendorOfferCreator;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class VendorITest {
    IVendorOfferDB vendorOfferDB;
    IVendor vendor;
    IVendorRepDB vendorRepDB;
    IVendorOfferManager vendorOfferManager;
    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        TestUtils.initFull(path);
        User dummy = TestUtils.getDummyUser(path);
        VendorDetails details = new VendorDetails(1,"","",1);
        this.vendorRepDB = new VendorRepDB(path, dummy, details);
        vendorOfferDB = new VendorOfferDB(path, dummy, details,(_,_)-> new VendorRepManager(vendorRepDB));
        vendor = new Vendor(details,vendorOfferDB);
        
        vendorOfferManager = new VendorOfferManager(new VendorOfferRegistry(path));
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
    
    @Test
    public void test_getDiscountOffers(){
        List<DiscountTransaction> discounts = vendor.getDiscountOffers();
        assertFalse(discounts.isEmpty());
    }
    
    @Test
    public void test_createOffer(){
        VendorOfferCreator creator = new VendorOfferCreator(
                new ItemStack<IItem>(new Currency(10),1),
                List.of(new ItemStack<IItem>(new Currency(1),1)),
                0
        );
        int startSize = vendor.getOffers().size();
        vendorOfferManager.createVendorOffer(creator,vendor.getDetails().id());
        int endSize = vendor.getOffers().size();
        assertTrue(endSize>startSize);
    }
    
    @Test
    public void test_createSpecial(){
        VendorOfferCreator creator = new VendorOfferCreator(
                new ItemStack<IItem>(new Currency(10),1),
                List.of(new ItemStack<IItem>(new Currency(1),1)),
                0
        );
        int startSize = vendor.getSpecialOffers().size();
        vendorOfferManager.createSpecialOffer(creator,vendor.getDetails().id());
        int endSize = vendor.getSpecialOffers().size();
        assertTrue(endSize>startSize);
    }
}
