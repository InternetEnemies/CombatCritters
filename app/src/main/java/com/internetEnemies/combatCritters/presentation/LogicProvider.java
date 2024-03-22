package com.internetEnemies.combatCritters.presentation;

import com.internetEnemies.combatCritters.Logic.Bank;
import com.internetEnemies.combatCritters.Logic.CardCatalog;
import com.internetEnemies.combatCritters.Logic.CardDeconstructor;
import com.internetEnemies.combatCritters.Logic.DeckManager;
import com.internetEnemies.combatCritters.Logic.IBank;
import com.internetEnemies.combatCritters.Logic.ICardCatalog;
import com.internetEnemies.combatCritters.Logic.ICardDeconstructor;
import com.internetEnemies.combatCritters.Logic.IDeckManager;
import com.internetEnemies.combatCritters.Logic.IMarketHandler;
import com.internetEnemies.combatCritters.Logic.IPackInventoryManager;
import com.internetEnemies.combatCritters.Logic.ITradesHandler;
import com.internetEnemies.combatCritters.Logic.MarketHandler;
import com.internetEnemies.combatCritters.Logic.PackInventoryManager;
import com.internetEnemies.combatCritters.Logic.TradesHandler;

/**
 * LogicProvider.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     01-January-2024
 *
 * @PURPOSE:     Provide access to logic layer classes through singletons.
 */
public class LogicProvider {
    private static LogicProvider instance;
    private final IBank bank;
    private final ICardCatalog cardCatalog;
    private final ICardDeconstructor cardDeconstructor;
    private final IDeckManager deckManager;
    private final IMarketHandler marketHandler;
    private final IPackInventoryManager packInventoryManager;
    private final ITradesHandler tradesHandler;

    private LogicProvider() {
        bank = new Bank();
        cardCatalog = new CardCatalog();
        cardDeconstructor = new CardDeconstructor();
        deckManager = new DeckManager();
        marketHandler = new MarketHandler();
        packInventoryManager = new PackInventoryManager();
        tradesHandler = new TradesHandler();
    }

    public static synchronized LogicProvider getInstance() {
        if (instance == null) {
            instance = new LogicProvider();
        }
        return instance;
    }

    public IBank getBank() {
        return bank;
    }
    public ICardCatalog getCardCatalog() {
        return cardCatalog;
    }
    public ICardDeconstructor getCardDeconstructor() {

        return cardDeconstructor;
    }
    public IDeckManager getDeckManager() {
        return deckManager
                ;}
    public IMarketHandler getMarketHandler() {
        return marketHandler;
    }
    public IPackInventoryManager getPackInventoryManager() {
        return packInventoryManager;
    }
    public ITradesHandler getTradesHandler() {return tradesHandler;}
}
