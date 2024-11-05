package com.internetEnemies.combatCritters.LogicUnitTests.market;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.market.IMarketPurchaseHandler;
import com.internetEnemies.combatCritters.Logic.market.IVendorRepManager;
import com.internetEnemies.combatCritters.Logic.market.MarketPurchaseHandler;
import com.internetEnemies.combatCritters.Logic.transaction.ITransactionHandler;
import com.internetEnemies.combatCritters.objects.VendorRep;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import org.junit.Before;
import org.junit.Test;

public class MarketPurchaseHandlerTest {
    IMarketPurchaseHandler marketPurchaseHandler;
    ITransactionHandler transactionHandler;
    IVendorRepManager vendorRepManager;
    @Before
    public void setup() {
        transactionHandler = mock(ITransactionHandler.class);
        vendorRepManager = mock(IVendorRepManager.class);
        marketPurchaseHandler = new MarketPurchaseHandler(transactionHandler, vendorRepManager);
    }
    
    @Test
    public void test_purchase(){
        when(vendorRepManager.getVendorRep()).thenReturn(new VendorRep(15, 0,0,0));
        VendorRep rep = marketPurchaseHandler.purchase(mock(VendorTransaction.class));
        assertTrue(rep.currentXp() == 15);
        
    }
}
