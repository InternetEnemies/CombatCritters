//package com.internetEnemies.combatCritters.data.hsqldb;
//
//import com.internetEnemies.combatCritters.data.ICardInventory;
//import com.internetEnemies.combatCritters.objects.Card;
//
//import java.util.*;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//
//// ATTENTION: THis cannot be done until item stacks so disregard this
//
//
//public class CardInventoryHSQLDB implements ICardInventory {
//
//    private final String dbPath;
//
//    public CardInventoryHSQLDB(final String dbPath) {
//        this.dbPath = dbPath;
//    }
//
//    private Connection connection() throws SQLException {
//        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
//    }
//
//
//    // There are 2 more weird things here
//    private Map<Card,Integer> fromResultSet(final ResultSet rs) throws SQLException {
//        //count is yhe property
//        // Item stack return
//        return null;
//    }
//
//
//
//
//
//
//
//    // This is everything implemented from the interface - do later
//
//    @Override
//    public int getCardAmount(Card card) {
//        return 0;
//    }
//
//    @Override
//    public void addCard(Card card) {
//
//    }
//
//    @Override
//    public void addCards(List<Card> cards) {
//
//    }
//
//    @Override
//    public void removeCard(Card card, int amount) {
//
//    }
//
//    @Override
//    public void removeCard(Card card) {
//
//    }
//
//    @Override
//    public Map<Card, Integer> getCards() {
//        return null;
//    }
//}
