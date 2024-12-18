package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.data.market.IVendorOfferRegistry;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import com.internetEnemies.combatCritters.objects.market.DiscountOfferCreator;

import java.util.List;
import java.util.Random;

public class MarketCycle implements IMarketCycle{
    private final static int NUM_DISCOUNT = 1;
    private final static int NUM_SPECIAL = 1;
    
    private final static int DISCOUNT_MAX = 75;
    private final static int DISCOUNT_MIN = 10;
    private final static int DISCOUNT_MOD = 5; // interval of discount (so you don't get something like 13% off and instead get 10 or 15)
    
    private final static int DISCOUNT_LVL = 0; // minor oversight lmao
    
    private final IVendorOfferRegistry vendorOfferRegistry;
    private final Random random;

    public MarketCycle(IVendorOfferRegistry vendorOfferRegistry) {
        this.vendorOfferRegistry = vendorOfferRegistry;
        this.random = new Random();
    }


    @Override
    public void runCycle(int vendorId) {
        System.out.println("Running Market Cycle: " + vendorId);
        clearOld(vendorId);
        doSpecials(vendorId);
        doDiscounts(vendorId);
    }

    private void clearOld(int vendorId) {
        vendorOfferRegistry.resetDiscounts(vendorId);
        vendorOfferRegistry.resetSpecials(vendorId);
    }

    private void doDiscounts(int vendorId) {
        // choose offers to discount
        List<VendorTransaction> offers = vendorOfferRegistry.getRandomStandardOffers(vendorId, numDiscounts());
        // create discounts for each offer
        List<DiscountOfferCreator> discounts = offers.stream().map(this::createDiscount).toList();
        discounts.forEach(creator -> vendorOfferRegistry.createDiscountOffer(vendorId, creator));// this is kinda garbage performance wise
    }
    
    private DiscountOfferCreator createDiscount(VendorTransaction parent){
        //get discount amount
        int discount = getDiscountAmount();
        float discountMulti = 1f - (float)discount/100f;
        //get initial offer requirement
        List<ItemStack<?>> initial = parent.getTransmit().getItems();
        // discount requirement by discount
        List<ItemStack<?>> discounted = initial.stream().map(stack -> {
            //only discount currency, we could change this in the future aswell
            if(stack.getItem() instanceof Currency){ 
                return new ItemStack<>(new Currency((int)Math.ceil(((Currency) stack.getItem()).getAmount() * discountMulti)),1);
            } else {
                return stack;
            }
        }).toList();
        return new DiscountOfferCreator(discounted, DISCOUNT_LVL, parent.getId(), discount);
    }

    private void doSpecials(int vendorId) {
        //activate random set of specials
        List<VendorTransaction> offers = vendorOfferRegistry.getRandomSpecialOffers(vendorId, numSpecials());
        List<Integer> ids = offers.stream().map(VendorTransaction::getId).toList();
        if (ids.isEmpty()) return; // nothing to do, there are no specials
        vendorOfferRegistry.activateSpecials(ids);
    }
    
    private int getDiscountAmount() {
        int discount = random.nextInt(DISCOUNT_MIN, DISCOUNT_MAX + 1); // +1 since Random excludes max
        return discount - discount % DISCOUNT_MOD;
    }
    
    private int numDiscounts() {
        return NUM_DISCOUNT;
    }
    
    private int numSpecials() {
        return NUM_SPECIAL;
    }
}
