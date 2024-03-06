package com.internetEnemies.combatCritters.data;

/**
 * This class is used as a singleton interface to the database
 */
public class Database {
    private static Database INSTANCE;

    private final IDeckInventory deckInventory;
    private final ICardInventory cardInventory;
    private final ICardSearch cardSearch;
    private final TradeRegistry tradeRegistry;



    private Database() {
        deckInventory = new DeckInventoryStub();
        cardInventory = new CardInventoryStub();
        cardSearch = new CardSearchStub(cardInventory, PackCardDatabase.getInstance().getCardDB());
        tradeRegistry = new TradeRegistry();
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

    public TradeRegistry getTradeRegistry(){
        return tradeRegistry;
    }
}
