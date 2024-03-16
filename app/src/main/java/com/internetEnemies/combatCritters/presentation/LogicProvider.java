package com.internetEnemies.combatCritters.presentation;

import com.internetEnemies.combatCritters.Logic.Bank;
import com.internetEnemies.combatCritters.Logic.CardCatalog;
import com.internetEnemies.combatCritters.Logic.CardDeconstructor;
import com.internetEnemies.combatCritters.Logic.DeckBuilder;
import com.internetEnemies.combatCritters.Logic.DeckManager;
import com.internetEnemies.combatCritters.Logic.IBank;
import com.internetEnemies.combatCritters.Logic.ICardCatalog;
import com.internetEnemies.combatCritters.Logic.ICardDeconstructor;
import com.internetEnemies.combatCritters.Logic.IDeckBuilder;
import com.internetEnemies.combatCritters.Logic.IDeckManager;
import com.internetEnemies.combatCritters.Logic.IMarketHandler;
import com.internetEnemies.combatCritters.Logic.IPackCatalog;
import com.internetEnemies.combatCritters.Logic.IPackInventoryManager;
import com.internetEnemies.combatCritters.Logic.ITransactionHandler;
import com.internetEnemies.combatCritters.Logic.MarketHandler;
import com.internetEnemies.combatCritters.Logic.PackCatalog;
import com.internetEnemies.combatCritters.Logic.PackInventoryManager;
import com.internetEnemies.combatCritters.Logic.TransactionHandler;

public class LogicProvider {
    private static LogicProvider instance;
    private final IBank bank;
    private final ICardCatalog cardCatalog;
    private final ICardDeconstructor cardDeconstructor;
    private final IDeckManager deckManager;
    private final IMarketHandler marketHandler;
    private final IPackCatalog packCatalog;
    private final IPackInventoryManager packInventoryManager;
    private final ITransactionHandler transactionHandler;


    private LogicProvider() {
        bank = new Bank();
        cardCatalog = new CardCatalog();
        cardDeconstructor = new CardDeconstructor();
        deckManager = new DeckManager();
        marketHandler = new MarketHandler();
        packCatalog = new PackCatalog();
        packInventoryManager = new PackInventoryManager();
        transactionHandler = new TransactionHandler();
    }

    public static synchronized LogicProvider getInstance() {
        if (instance == null) {
            instance = new LogicProvider();
        }
        return instance;
    }

    public IBank getBank() {return bank;}
    public ICardCatalog getCardCatalog() {return cardCatalog;}
    public ICardDeconstructor getCardDeconstructor() {
        return cardDeconstructor;
    }
    public IDeckManager getDeckManager() {return deckManager;}
    public IMarketHandler getMarketHandler() {return marketHandler;}
    public IPackCatalog getPackCatalog() {return packCatalog;}
    public IPackInventoryManager getPackInventoryManager() {return packInventoryManager;}
    public ITransactionHandler getTransactionHandler() {return transactionHandler;}


}
