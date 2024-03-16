package com.internetEnemies.combatCritters;

import com.internetEnemies.combatCritters.DataUnitTests.hsqldb.CardInventoryTests;
import com.internetEnemies.combatCritters.DataUnitTests.hsqldb.DeckInventoryTest;
import com.internetEnemies.combatCritters.DataUnitTests.hsqldb.DeckTest;
import com.internetEnemies.combatCritters.IntegrationTests.BankIntegrationTest;
import com.internetEnemies.combatCritters.IntegrationTests.CardCatalogTest;
import com.internetEnemies.combatCritters.IntegrationTests.DeckBuilderTest;
import com.internetEnemies.combatCritters.IntegrationTests.DeckManagerTest;
import com.internetEnemies.combatCritters.IntegrationTests.DeckValidatorTest;
import com.internetEnemies.combatCritters.IntegrationTests.MarketHandlerIntegrationTest;

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
        MarketHandlerIntegrationTest.class
})
public class AllIntegrationTests {
}
