package com.internetEnemies.combatCritters;

import com.internetEnemies.combatCritters.DataUnitTests.CardInventoryStubTests;
import com.internetEnemies.combatCritters.DataUnitTests.CardSearchTest;
import com.internetEnemies.combatCritters.DataUnitTests.DeckInventoryStubTest;
import com.internetEnemies.combatCritters.DataUnitTests.DeckStubTest;
import com.internetEnemies.combatCritters.DataUnitTests.PackSubTest;
import com.internetEnemies.combatCritters.DSOUnitTests.CritterCardUnitTest;
import com.internetEnemies.combatCritters.DSOUnitTests.DeckDetailsUnitTest;
import com.internetEnemies.combatCritters.DSOUnitTests.ItemCardUnitTest;
import com.internetEnemies.combatCritters.DSOUnitTests.PackUnitTest;
import com.internetEnemies.combatCritters.LogicUnitTests.BankUnitTest;
import com.internetEnemies.combatCritters.LogicUnitTests.CardCatalogTest;
import com.internetEnemies.combatCritters.LogicUnitTests.CardDeconstructorUnitTest;
import com.internetEnemies.combatCritters.LogicUnitTests.DeckBuilderTest;
import com.internetEnemies.combatCritters.LogicUnitTests.DeckValidatorTest;
import com.internetEnemies.combatCritters.LogicUnitTests.ItemStackListExtractorUnitTest;
import com.internetEnemies.combatCritters.LogicUnitTests.MarketCycleTest;
import com.internetEnemies.combatCritters.LogicUnitTests.MarketHandlerTest;
import com.internetEnemies.combatCritters.LogicUnitTests.PackInventoryManagerTest;
import com.internetEnemies.combatCritters.LogicUnitTests.PackOpeningUnitTest;
import com.internetEnemies.combatCritters.LogicUnitTests.DeckManagerTest;
import com.internetEnemies.combatCritters.LogicUnitTests.PackCatalogUnitTest;
import com.internetEnemies.combatCritters.LogicUnitTests.TradeUpHandlerTest;
import com.internetEnemies.combatCritters.LogicUnitTests.TradeUpValidatorTest;
import com.internetEnemies.combatCritters.LogicUnitTests.TransactionUnitTest;
import com.internetEnemies.combatCritters.LogicUnitTests.TradesHandlerTest;
import com.internetEnemies.combatCritters.LogicUnitTests.battles.BattleRegistryTest;
import com.internetEnemies.combatCritters.LogicUnitTests.battles.BattleTest;
import com.internetEnemies.combatCritters.LogicUnitTests.battles.cards.BattleCardFactoryTest;
import com.internetEnemies.combatCritters.LogicUnitTests.battles.cards.BattleCardTest;
import com.internetEnemies.combatCritters.LogicUnitTests.battles.events.EventHostTest;
import com.internetEnemies.combatCritters.LogicUnitTests.battles.events.EventTests;
import com.internetEnemies.combatCritters.LogicUnitTests.battles.events.IntegerEventTest;
import com.internetEnemies.combatCritters.LogicUnitTests.battles.opponents.SingleSlotOpponentTest;
import com.internetEnemies.combatCritters.LogicUnitTests.battles.stateHandlers.BoardRowTest;
import com.internetEnemies.combatCritters.LogicUnitTests.battles.stateHandlers.BoardTest;
import com.internetEnemies.combatCritters.LogicUnitTests.battles.stateHandlers.EnergyTest;
import com.internetEnemies.combatCritters.LogicUnitTests.battles.stateHandlers.HealthTest;


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
        PackCatalogUnitTest.class,
        DeckValidatorTest.class,
        BankUnitTest.class,
        CardSearchTest.class,
        TradesHandlerTest.class,
        TransactionUnitTest.class,
        CardDeconstructorUnitTest.class,
        MarketHandlerTest.class,
        ItemStackListExtractorUnitTest.class,
        PackInventoryManagerTest.class,
        TradeUpHandlerTest.class,
        TradeUpValidatorTest.class,
        MarketCycleTest.class,
        BattleCardFactoryTest.class,
        BattleCardTest.class,
        EventHostTest.class,
        EventTests.class,
        IntegerEventTest.class,
        SingleSlotOpponentTest.class,
        BoardRowTest.class,
        BoardTest.class,
        EnergyTest.class,
        HealthTest.class,
        BattleRegistryTest.class,
        BattleTest.class
})
public class AllUnitTests {
}
