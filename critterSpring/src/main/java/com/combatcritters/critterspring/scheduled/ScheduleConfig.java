package com.combatcritters.critterspring.scheduled;

import com.internetEnemies.combatCritters.Logic.market.*;
import com.internetEnemies.combatCritters.data.Database;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
public class ScheduleConfig {
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    IMarketCycler getMarketCycler(IVendorRegistry vendorRegistry, IMarketCycle marketCycle) {
        return new MarketCycler(vendorRegistry,marketCycle);
    }
    
    @Bean
    IVendorRegistry getVendorRegistry(Database database){
        return new VendorRegistry(database.getVendorDB());
    }
    
    @Bean
    IMarketCycle getMarketCycle(Database database) {
        return new MarketCycle(database.getVendorOfferRegistry());
    }
}
