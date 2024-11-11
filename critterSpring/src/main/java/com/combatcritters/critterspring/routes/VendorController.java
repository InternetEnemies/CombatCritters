package com.combatcritters.critterspring.routes;

import com.combatcritters.critterspring.payloads.market.OfferDiscountPayload;
import com.combatcritters.critterspring.payloads.market.OfferPayload;
import com.combatcritters.critterspring.payloads.market.VendorPayload;
import com.combatcritters.critterspring.payloads.market.VendorReputationPayload;
import com.internetEnemies.combatCritters.Logic.market.*;
import com.internetEnemies.combatCritters.Logic.transaction.UnverifiedTransactionException;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.objects.DiscountTransaction;
import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.VendorRep;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
public class VendorController {
    private final IUserManager userManager;
    private final IVendorManagerFactory vendorManagerFactory;
    private final IMarketPurchaseHandlerFactory marketPurchaseHandlerFactory;
    private final IVendorRepManagerFactory vendorRepManagerFactory;

    @Autowired
    public VendorController(IUserManager userManager,
                            IVendorManagerFactory vendorManagerFactory,
                            IMarketPurchaseHandlerFactory marketPurchaseHandlerFactory,
                            IVendorRepManagerFactory vendorRepManagerFactory) {
        this.userManager = userManager;
        this.vendorManagerFactory = vendorManagerFactory;
        this.marketPurchaseHandlerFactory = marketPurchaseHandlerFactory;
        this.vendorRepManagerFactory = vendorRepManagerFactory;
    }
    @GetMapping("/vendors")
    public List<VendorPayload> getVendors(Principal principal) {
        User user = userManager.getUserByUsername(principal.getName());
        IVendorManager vendorManager = vendorManagerFactory.create(user);
        
        return vendorManager.getVendors().stream().map(vendor -> {
            IVendorRepManager repManager = vendorRepManagerFactory.create(user,vendor.getDetails());
            return VendorPayload.from(vendor, repManager.getVendorRep());
        }).toList();
    }
    
    @GetMapping("/vendors/{vendorid}/offers")
    public List<OfferPayload> getVendorOffers(@PathVariable int vendorid, Principal principal) {
        User user = userManager.getUserByUsername(principal.getName());
        IVendorManager vendorManager = vendorManagerFactory.create(user);
        
        IVendor vendor = vendorManager.getVendor(vendorid);
        List<VendorTransaction> offers =  vendor.getOffers();
        return OfferPayload.listFrom(offers);
    }
    
    @PostMapping("/vendors/{vendorid}/offers/{offerid}")
    public VendorReputationPayload purchaseOffer(@PathVariable int vendorid, @PathVariable int offerid, Principal principal) {
        User user = userManager.getUserByUsername(principal.getName());
        IVendorManager vendorManager = vendorManagerFactory.create(user);
        IVendor vendor = vendorManager.getVendor(vendorid);
        VendorTransaction transaction = vendor.getOffer(offerid);
        IMarketPurchaseHandler purchaseHandler = this.marketPurchaseHandlerFactory.create(user, vendor.getDetails());
        VendorRep rep;
        try{
            rep = purchaseHandler.purchase(transaction);
        } catch (UnverifiedTransactionException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        
        return VendorReputationPayload.from(rep);
    }
    
    @GetMapping("/vendors/{vendorid}/specials")
    public List<OfferPayload> getVendorSpecialOffers(@PathVariable int vendorid, Principal principal) {
        User user = userManager.getUserByUsername(principal.getName());
        IVendorManager vendorManager = vendorManagerFactory.create(user);

        IVendor vendor = vendorManager.getVendor(vendorid);
        List<VendorTransaction> offers =  vendor.getSpecialOffers();
        return OfferPayload.listFrom(offers);
    }
    
    @GetMapping("/vendors/{vendorid}/discounts")
    public List<OfferDiscountPayload> getVendorDiscountOffers(@PathVariable int vendorid, Principal principal) {
        User user = userManager.getUserByUsername(principal.getName());
        IVendorManager vendorManager = vendorManagerFactory.create(user);
        
        IVendor vendor = vendorManager.getVendor(vendorid);
        List<DiscountTransaction> discounts = vendor.getDiscountOffers();
        return OfferDiscountPayload.listFrom(discounts);
    }
}
