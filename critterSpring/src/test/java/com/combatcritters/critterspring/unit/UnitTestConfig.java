package com.combatcritters.critterspring.unit;

import com.internetEnemies.combatCritters.Logic.market.IVendorRegistry;
import com.internetEnemies.combatCritters.data.Database;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("unit")
public class UnitTestConfig {
    @MockBean
    public Database database;
    
    @MockBean
    public IVendorRegistry vendorRegistry;
}
