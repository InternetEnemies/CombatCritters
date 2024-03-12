/**
 * Database.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-01
 *
 * @PURPOSE:    a singleton interface to the database
 *              containing inventories from data layer
 */

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
    private final IPackInventory packInventory;
    private final ICardSearch cardSearch;
    private final ICurrencyInventory currencyInventory;
    private final IMarketDB marketDB;
    private final TradeRegistry tradeRegistry;



    private Database() {
        String path = Main.getDBPathName();
        deckInventory = new DeckInventoryHSQLDB(path);
        cardInventory = new CardInventoryHSQLDB(path);
        packInventory = new PackInventoryStub();
        cardSearch = new CardSearchHSQLDB(path);
        currencyInventory = new CurrencyInventoryStub();
        tradeRegistry = OffersDatabase.getInstance().getTradesDB();
        marketDB = OffersDatabase.getInstance().getMarketDB();
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
    public ICurrencyInventory getCurrencyInventory(){
        return currencyInventory;
    }
    public IPackInventory getPackInventory(){
        return packInventory;
    }
}
