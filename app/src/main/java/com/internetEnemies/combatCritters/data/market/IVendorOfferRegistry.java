package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.objects.DiscountTransaction;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import com.internetEnemies.combatCritters.objects.market.DiscountOfferCreator;
import com.internetEnemies.combatCritters.objects.market.VendorOfferCreator;

import java.util.List;

/**
 * IVendorOfferRegistry.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/14/24
 * 
 * @PURPOSE:    interface vendor offer management
 */
public interface IVendorOfferRegistry {
    /**
     * get a vendor offer from its id
     */
    VendorTransaction getVendorOffer(int vendorid, int offerid);

    /**
     * create a new vendor offer
     * @param vendorid id of the vendor to create for
     * @param vendorOfferCreator details of the offer to create
     * @return newly created vendor offer
     */
    VendorTransaction createVendorOffer(int vendorid, VendorOfferCreator vendorOfferCreator);

    /**
     * create a new special offer
     * @param vendorid id of the vendor to create for
     * @param vendorOfferCreator details of the offer to create
     * @return newly created special offer
     */
    VendorTransaction createSpecialOffer(int vendorid, VendorOfferCreator vendorOfferCreator);

    /**
     * create a new discount offer
     * @param vendorid id of the vendor to create for
     * @param discountOfferCreator details of the discount to create
     * @return newly created discount offer
     */
    DiscountTransaction createDiscountOffer(int vendorid, DiscountOfferCreator discountOfferCreator);



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
