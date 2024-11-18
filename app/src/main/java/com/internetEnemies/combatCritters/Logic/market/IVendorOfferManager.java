package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.objects.DiscountTransaction;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import com.internetEnemies.combatCritters.objects.market.DiscountOfferCreator;
import com.internetEnemies.combatCritters.objects.market.VendorOfferCreator;

import java.util.List;

/**
 * IVendorOfferManager.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/14/24
 * 
 * @PURPOSE:    manage offers for all vendors
 */
public interface IVendorOfferManager {
    /**
     * create a new vendor offer
     * @param vendorOfferCreator creation object for the offer
     * @param vendorid id of the vendor to create for
     * @return the created vendor transaction
     */
    VendorTransaction createVendorOffer(VendorOfferCreator vendorOfferCreator, int vendorid);

    /**
     * create a new special offer
     * @param vendorOfferCreator creation object for the offer
     * @param vendorid id of the vendor to create for
     * @return the created vendor transaction
     */
    VendorTransaction createSpecialOffer(VendorOfferCreator vendorOfferCreator, int vendorid);

    /**
     * create a new discount offer
     * @param creator creation object for the discount
     * @param vendorid id of the vendor to create for
     * @return the created discount transaction
     */
    DiscountTransaction createDiscountOffer(DiscountOfferCreator creator, int vendorid);

    /**
     * get random set of standard offers
     * @param vendorid parent vendor of offers
     * @param amount amount of offers to get
     * @return list of random standard offers
     */
    List<VendorTransaction> getRandomStandardOffers(int vendorid, int amount);

    /**
     * get random set of special offers
     * @param vendorid parent vendor of the offers
     * @param amount amount of offers to get
     * @return list of random special offers
     */
    List<VendorTransaction> getRandomSpecialOffers(int vendorid, int amount);

    /**
     * set list of special offers to active
     * @param ids ids of the special offers
     */
    void activateSpecials(List<Integer> ids);

    /**
     * reset all discounts to a non-active state for a vendor
     * @param vendorid id of vendor to reset discounts for
     */
    void resetDiscounts(int vendorid);

    /**
     * reset all specials to a non-active state for a vendor
     * @param vendorid id of the vendor to reset the specials for
     */
    void resetSpecials(int vendorid);
    
}
