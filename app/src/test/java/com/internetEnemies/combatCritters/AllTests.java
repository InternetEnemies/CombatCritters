package com.internetEnemies.combatCritters;

import com.internetEnemies.combatCritters.DataUnitTests.CardInventoryStubTests;
import com.internetEnemies.combatCritters.DataUnitTests.DeckInventoryStubTest;
import com.internetEnemies.combatCritters.DataUnitTests.DeckStubTest;
import com.internetEnemies.combatCritters.DataUnitTests.PackSubTest;
import com.internetEnemies.combatCritters.DSOUnitTests.CritterCardUnitTest;
import com.internetEnemies.combatCritters.DSOUnitTests.DeckDetailsUnitTest;
import com.internetEnemies.combatCritters.DSOUnitTests.ItemCardUnitTest;
import com.internetEnemies.combatCritters.DSOUnitTests.PackUnitTest;
import com.internetEnemies.combatCritters.LogicUnitTests.CardCatalogTest;
import com.internetEnemies.combatCritters.LogicUnitTests.DeckBuilderTest;
import com.internetEnemies.combatCritters.LogicUnitTests.PackOpeningUnitTest;
import com.internetEnemies.combatCritters.LogicUnitTests.DeckManagerTest;
import com.internetEnemies.combatCritters.LogicUnitTests.PackCatalogUnitTest;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        CardInventoryStubTests.class,
        DeckInventoryStubTest.class,
        DeckStubTest.class,
        PackSubTest.class,
        CritterCardUnitTest.class,
        DeckDetailsUnitTest.class,
        ItemCardUnitTest.class,
        PackUnitTest.class,
        CardCatalogTest.class,
        DeckBuilderTest.class,
        PackOpeningUnitTest.class,
        DeckManagerTest.class,
        PackCatalogUnitTest.class
})
public class AllTests {
}
