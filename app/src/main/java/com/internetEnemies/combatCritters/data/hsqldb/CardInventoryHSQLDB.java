package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.CardHelper;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.sql.Connection;
import java.util.*;

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
public class CardInventoryHSQLDB extends HSQLDBModel implements ICardInventory {

    public CardInventoryHSQLDB(final String dbPath) {
        super(dbPath);
    }

    @Override
    public int getCardAmount(Card card) {
        try(Connection connection = this.connection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT amount FROM CardInventory WHERE cardId = ?");
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
        try(Connection connection = this.connection()){
            int currAmount = getCardAmount(card);
            PreparedStatement stmt;
            if (currAmount == 0) {
                stmt = connection.prepareStatement("INSERT INTO CardInventory (cardId, amount) VALUES (?,1)");
            } else {
                stmt = connection.prepareStatement("UPDATE CardInventory set amount = amount + 1 WHERE cardId = ?");
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
        int currAmount = getCardAmount(card);
        try (Connection connection = this.connection()){
            PreparedStatement stmt;
            if(currAmount <= amount) {
                stmt = connection.prepareStatement("DELETE FROM CardInventory WHERE cardId = ?");
                stmt.setInt(1, card.getId());
            } else {// curr > amount
                stmt = connection.prepareStatement("UPDATE CardInventory set amount = amount - ? WHERE cardId = ?");
                stmt.setInt(1, amount);
                stmt.setInt(2, card.getId());
            }

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
        try (Connection connection = this.connection()){
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Cards LEFT JOIN CardInventory ON Cards.id = CardInventory.cardId");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Card card = CardHelper.cardFromResultSet(rs);
                cardStacks.add(new ItemStack<>(card, rs.getInt("amount")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting cards",e);
        }
        return cardStacks;
    }
}
