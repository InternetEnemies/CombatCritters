package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.Logic.transaction.ITransactionHandler;
import com.internetEnemies.combatCritters.objects.VendorRep;
import com.internetEnemies.combatCritters.objects.VendorTransaction;

import java.util.Random;

/**
 * MarketPurchaseHandler.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/3/24
 * 
 * @PURPOSE:    basic purchase handler
 */
public class MarketPurchaseHandler implements IMarketPurchaseHandler {
    private static final int MIN_XP = 5;
    private static final int MAX_XP = 15;
    
    private final Random random;
    
    ITransactionHandler transactionHandler;
    IVendorRepManager vendorRepManager;
    public MarketPurchaseHandler(ITransactionHandler transactionHandler, IVendorRepManager vendorRepManager) {
        this.transactionHandler = transactionHandler;
        this.vendorRepManager = vendorRepManager;
        this.random = new Random();
    }
    
    @Override
    public VendorRep purchase(VendorTransaction transaction) {
        transactionHandler.verifiedPerform(transaction);
        int change = getRepChange();
        vendorRepManager.addRep(change);
        return vendorRepManager.getVendorRep();
    }
    
    private int getRepChange(){
        return random.nextInt(MIN_XP,MAX_XP);
    }
}
