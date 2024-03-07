package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.application.Main;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.CardSearchHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.DeckInventoryHSQLDB;

/**
 * This class is used as a singleton interface to the database
 */
public class Database {
    private static Database INSTANCE;

    private final IDeckInventory deckInventory;
    private final ICardInventory cardInventory;
    private final ICardSearch cardSearch;
    private final IMarketDB marketDB;
    private final TradeRegistry tradeRegistry;



    private Database() {
        final String path = Main.getDBPathName();
        deckInventory = new DeckInventoryHSQLDB(path);
        cardInventory = new CardInventoryHSQLDB(path);
        cardSearch = new CardSearchHSQLDB(path);

        tradeRegistry = new TradeRegistry();
        marketDB = new MarketDB();
        //PackCardDatabase.getInstance();//todo remove this(this just ensures the db is initialized
    }

    public static synchronized Database getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    public IDeckInventory getDeckInventory() {
        return deckInventory;
    }

    public ICardInventory getCardInventory() {
        return cardInventory;
    }

    public ICardSearch getCardSearch() {
        return this.cardSearch;
    }

    public TradeRegistry getTradeRegistry(){
        return tradeRegistry;
    }

    public IMarketDB getMarketDB(){
        return marketDB;
    }
}
