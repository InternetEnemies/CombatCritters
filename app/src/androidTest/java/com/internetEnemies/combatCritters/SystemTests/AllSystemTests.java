package com.internetEnemies.combatCritters.SystemTests;

import com.internetEnemies.combatCritters.SystemTests.Tests.CardCatalogSystemTest;
import com.internetEnemies.combatCritters.SystemTests.Tests.CurrencySystemTest;
import com.internetEnemies.combatCritters.SystemTests.Tests.DeckBuilderSystemTest;
import com.internetEnemies.combatCritters.SystemTests.Tests.MarketplaceSystemTest;
import com.internetEnemies.combatCritters.SystemTests.Tests.PackOpeningSystemTest;
import com.internetEnemies.combatCritters.SystemTests.Tests.TradeUpSystemTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        CardCatalogSystemTest.class,
        CurrencySystemTest.class,
        MarketplaceSystemTest.class,
        PackOpeningSystemTest.class,
        TradeUpSystemTest.class,
        DeckBuilderSystemTest.class
})
public class AllSystemTests {
}
