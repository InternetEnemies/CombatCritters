package com.internetEnemies.combatCritters;

import com.internetEnemies.combatCritters.IntegrationTests.hsqldb.CardInventoryTests;
import com.internetEnemies.combatCritters.IntegrationTests.hsqldb.DeckInventoryTest;
import com.internetEnemies.combatCritters.IntegrationTests.hsqldb.DeckTest;
import com.internetEnemies.combatCritters.IntegrationTests.BankIntegrationTest;
import com.internetEnemies.combatCritters.IntegrationTests.CardCatalogTest;
import com.internetEnemies.combatCritters.IntegrationTests.CardDeconstructorIntegrationTest;
import com.internetEnemies.combatCritters.IntegrationTests.DeckBuilderTest;
import com.internetEnemies.combatCritters.IntegrationTests.DeckManagerTest;
import com.internetEnemies.combatCritters.IntegrationTests.DeckValidatorTest;
import com.internetEnemies.combatCritters.IntegrationTests.MarketHandlerIntegrationTest;
import com.internetEnemies.combatCritters.IntegrationTests.PackInventoryManagerIntegrationTest;
import com.internetEnemies.combatCritters.IntegrationTests.PackOpenerIntegrationTest;
import com.internetEnemies.combatCritters.IntegrationTests.TradeUpHandlerIntegrationTest;
import com.internetEnemies.combatCritters.IntegrationTests.TradesHandlerIntegrationTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CardInventoryTests.class,
        DeckInventoryTest.class,
        DeckTest.class,
        CardCatalogTest.class,
        DeckBuilderTest.class,
        DeckManagerTest.class,
        DeckValidatorTest.class,
        BankIntegrationTest.class,
        MarketHandlerIntegrationTest.class,
        TradesHandlerIntegrationTest.class,
        TradeUpHandlerIntegrationTest.class,
        CardDeconstructorIntegrationTest.class,
        PackInventoryManagerIntegrationTest.class,
        PackOpenerIntegrationTest.class,
})
public class AllIntegrationTests {
}
