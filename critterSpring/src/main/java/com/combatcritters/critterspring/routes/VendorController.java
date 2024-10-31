package com.combatcritters.critterspring.routes;

import com.combatcritters.critterspring.payloads.market.VendorPayload;
import com.internetEnemies.combatCritters.Logic.market.IVendorManager;
import com.internetEnemies.combatCritters.Logic.market.IVendorManagerFactory;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class VendorController {
    private final IUserManager userManager;
    private final IVendorManagerFactory vendorManagerFactory;

    @Autowired
    public VendorController(IUserManager userManager, IVendorManagerFactory vendorManagerFactory) {
        this.userManager = userManager;
        this.vendorManagerFactory = vendorManagerFactory;
    }
    @GetMapping("/vendors")
    public List<VendorPayload> getVendors(Principal principal) {
        User user = userManager.getUserByUsername(principal.getName());
        IVendorManager vendorManager = vendorManagerFactory.create(user);
        return vendorManager.getVendors().stream().map(VendorPayload::from).toList();
    }
}