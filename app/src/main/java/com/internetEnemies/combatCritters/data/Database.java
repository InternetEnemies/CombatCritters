package com.internetEnemies.combatCritters.data;

public class Database {
    private static Database INSTANCE;


    private final IDeckInventory deckInventory;
    private final ICardInventory cardInventory;

    private Database() {
        deckInventory = new DeckInventoryStub();
        cardInventory = new CardInventoryStub();
    }

    public static Database getInstance() { // Singleton
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
}
