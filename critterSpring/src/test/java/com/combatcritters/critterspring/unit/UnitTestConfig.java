package com.combatcritters.critterspring.unit;

import com.internetEnemies.combatCritters.data.Database;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnitTestConfig {
    @MockBean
    public Database database;
    
}
