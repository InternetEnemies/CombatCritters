package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.CardHelper;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * CardInventoryHSQLDB.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/7/24
 *
 * @PURPOSE:    sql implementation of CardInventory
 */
public class CardInventoryHSQLDB implements ICardInventory {

    private final Connection connection;

    public CardInventoryHSQLDB(final String dbPath) {
        try {
            this.connection = HSQLDBUtil.connection(dbPath);
        } catch (SQLException e) {
            throw new RuntimeException("Error while initializing CardInventory",e);
        }
    }

    @Override
    public int getCardAmount(Card card) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT amount FROM CardInventory WHERE cardId = ?");
            stmt.setInt(1, card.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("amount");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting card amount",e);
        }
        return 0;
    }

    @Override
    public void addCard(Card card) {
        try{
            int currAmount = getCardAmount(card);
            PreparedStatement stmt;
            if (currAmount == 0) {
                stmt = this.connection.prepareStatement("INSERT INTO CardInventory (cardId, amount) VALUES (?,1)");
            } else {
                stmt = this.connection.prepareStatement("UPDATE CardInventory set amount = amount + 1 WHERE cardId = ?");
            }
            stmt.setInt(1, card.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding card",e);
        }
    }

    @Override
    public void addCards(List<Card> cards) {
        for (Card card : cards) {
            addCard(card);
        }
    }

    @Override
    public void removeCard(Card card, int amount) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM CardInventory WHERE cardId = ? LIMIT ?");
            stmt.setInt(1, card.getId());
            stmt.setInt(2, amount);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error removing card",e);
        }
    }

    @Override
    public void removeCard(Card card) {
        removeCard(card, 1);
    }

    @Override
    public List<ItemStack<Card>> getCards() {
        List<ItemStack<Card>> cardStacks = new ArrayList<>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Cards");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Card card = CardHelper.cardFromResultSet(rs);
                int amount = getCardAmount(card);
                cardStacks.add(new ItemStack<>(card, amount));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting cards",e);
        }
        return cardStacks;
    }
}
