package com.combatcritters.critterspring.scheduled;

import com.internetEnemies.combatCritters.Logic.market.IVendor;
import com.internetEnemies.combatCritters.Logic.market.IVendorRegistry;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * MarketCycler.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/17/24
 * 
 * @PURPOSE:    cycle the market and provide info on when the next refresh is for each vendor
 */
public class MarketCycler implements IMarketCycler{
    private static final int EXECUTORS = 50;
    private static final int INIT_DELAY = 10; // delay before initial refresh in seconds, the intent with this is to refresh the market quickly on restart
    private final ScheduledExecutorService scheduledExecutorService;
    private final IVendorRegistry vendorRegistry;
    
    private final Map<Integer, ScheduledFuture<?>> cycles;

    public MarketCycler(IVendorRegistry vendorRegistry) {
        this.vendorRegistry = vendorRegistry;
        this.scheduledExecutorService = Executors.newScheduledThreadPool(EXECUTORS);
        this.cycles = new HashMap<>();
        initCycles();
    }
    
    private void initCycles() {
        List<VendorDetails> vendors = vendorRegistry.getVendors();
        for (VendorDetails vendor : vendors) {
            final ScheduledFuture<?> cycle = scheduledExecutorService.scheduleAtFixedRate(
                    ()->{/*TODO*/},INIT_DELAY,vendor.refreshInterval(),TimeUnit.SECONDS
            );
            cycles.put(vendor.id(), cycle);
        }
    }

    @Override
    public Date getRefresh(int vendorId) {
        long delay = cycles.get(vendorId).getDelay(TimeUnit.MILLISECONDS);
        return new Date(System.currentTimeMillis()+delay);
    }
}
