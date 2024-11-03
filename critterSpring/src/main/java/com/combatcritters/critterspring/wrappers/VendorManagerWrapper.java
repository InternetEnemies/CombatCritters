package com.combatcritters.critterspring.wrappers;

import com.internetEnemies.combatCritters.Logic.market.IVendor;
import com.internetEnemies.combatCritters.Logic.market.IVendorFactory;
import com.internetEnemies.combatCritters.Logic.market.VendorManager;
import com.internetEnemies.combatCritters.data.market.IVendorDB;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VendorManagerWrapper extends VendorManager {
    public VendorManagerWrapper(IVendorDB vendorDB, IVendorFactory vendorFactory) {
        super(vendorDB, vendorFactory);
    }
    
    @Override
    public IVendor getVendor(int id) {
        IVendor vendor = super.getVendor(id);
        if(vendor == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendor not found");
        return vendor;
    }
}
