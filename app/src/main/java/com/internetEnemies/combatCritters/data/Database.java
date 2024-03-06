package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.MarketTransaction;

/**
 * This class is used as a singleton interface to the database
 */
public class Database {
    private static Database INSTANCE;

    private final IDeckInventory deckInventory;
    private final ICardInventory cardInventory;
    private final ICardSearch cardSearch;
    private final IRegistry marketDB;
    private final IRegistry tradeRegistry;



    private Database() {
        deckInventory = new DeckInventoryStub();
        cardInventory = new CardInventoryStub();
        cardSearch = new CardSearchStub(cardInventory, PackCardDatabase.getInstance().getCardDB());
        marketDB = OffersDatabase.getInstance().getMarketDB();
        tradeRegistry = OffersDatabase.getInstance().getTradesDB();
    }

    public static Database getInstance() {
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

    public IRegistry<MarketTransaction> getMarketDB(){
        return marketDB;
    }

    public IRegistry<TradeRegistry> getTradeRegistry(){
        return tradeRegistry;
    }
}
