package com.combatcritters.critterspring.routes;

import com.combatcritters.critterspring.payloads.market.OfferPayload;
import com.combatcritters.critterspring.payloads.market.RepChangePayload;
import com.combatcritters.critterspring.payloads.market.VendorPayload;
import com.internetEnemies.combatCritters.Logic.market.IVendor;
import com.internetEnemies.combatCritters.Logic.market.IVendorManager;
import com.internetEnemies.combatCritters.Logic.market.IVendorManagerFactory;
import com.internetEnemies.combatCritters.Logic.transaction.ITransactionHandler;
import com.internetEnemies.combatCritters.Logic.transaction.ITransactionHandlerFactory;
import com.internetEnemies.combatCritters.Logic.transaction.participant.IUserParticipantFactory;
import com.internetEnemies.combatCritters.Logic.transaction.participant.SystemParticipant;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class VendorController {
    private final IUserManager userManager;
    private final IVendorManagerFactory vendorManagerFactory;
    private final ITransactionHandlerFactory transactionHandlerFactory;
    private final IUserParticipantFactory userParticipantFactory;

    @Autowired
    public VendorController(IUserManager userManager, 
                            IVendorManagerFactory vendorManagerFactory, 
                            ITransactionHandlerFactory transactionHandlerFactory, 
                            IUserParticipantFactory userParticipantFactory) {
        this.userManager = userManager;
        this.vendorManagerFactory = vendorManagerFactory;
        this.transactionHandlerFactory = transactionHandlerFactory;
        this.userParticipantFactory = userParticipantFactory;
    }
    @GetMapping("/vendors")
    public List<VendorPayload> getVendors(Principal principal) {
        User user = userManager.getUserByUsername(principal.getName());
        IVendorManager vendorManager = vendorManagerFactory.create(user);
        return vendorManager.getVendors().stream().map(VendorPayload::from).toList();
    }
    
    @GetMapping("/vendors/{vendorid}/offers")
    public List<OfferPayload> getVendorOffers(@PathVariable int vendorid, Principal principal) {
        User user = userManager.getUserByUsername(principal.getName());
        IVendorManager vendorManager = vendorManagerFactory.create(user);
        
        IVendor vendor = vendorManager.getVendor(vendorid);
        List<VendorTransaction> offers =  vendor.getOffers();
        List<OfferPayload> offerPayloads = new ArrayList<>();
        for(VendorTransaction offer: offers) {
            offerPayloads.add(OfferPayload.from(offer));
        }
        
        return offerPayloads;
    }
    
    @PostMapping("/vendors/{vendorid}/offers/{offerid}")
    public RepChangePayload purchaseOffer(@PathVariable int vendorid, @PathVariable int offerid, Principal principal) {
        User user = userManager.getUserByUsername(principal.getName());
        IVendorManager vendorManager = vendorManagerFactory.create(user);
        IVendor vendor = vendorManager.getVendor(vendorid);//todo switch vendor to wrapper that throws 404 when the vendor isnt found or when Offer isnt found
        VendorTransaction transaction = vendor.getOffer(offerid);
        ITransactionHandler handler = getTransactionHandler(user);
        handler.verifiedPerform(transaction);
        
        return new RepChangePayload(1,vendorid);//todo see #145 details
    }

    /**
     * get transaction handlers for market transactions
     */
    private ITransactionHandler getTransactionHandler(User user) {
        return transactionHandlerFactory.getTransactionHandler(userParticipantFactory.createUserParticipant(user), new SystemParticipant());
    }
}
