package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.sql.Types;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CardInventoryHSQLDB implements ICardInventory {

    private final String dbPath;

    public CardInventoryHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public int getCardAmount(Card card) {
        try (Connection conn = connection()){
            PreparedStatement stmt = conn.prepareStatement("SELECT amount FROM CardInventory WHERE cardId = ?");
            stmt.setInt(1, card.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("amount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void addCard(Card card) {
        try (Connection conn = connection()){
            int currAmount = getCardAmount(card);
            PreparedStatement stmt;
            if (currAmount == 0) {
                stmt = conn.prepareStatement("INSERT INTO CardInventory (cardId, amount) VALUES (?,1)");
            } else {
                stmt = conn.prepareStatement("UPDATE CardInventory set amount = amount + 1 WHERE cardId = ?");
            }
            stmt.setInt(1, card.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM CardInventory WHERE cardId = ? LIMIT ?")) {
            stmt.setInt(1, card.getId());
            stmt.setInt(2, amount);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCard(Card card) {
        removeCard(card, 1);
    }

    @Override
    public List<ItemStack<Card>> getCards() {
        List<ItemStack<Card>> cardStacks = new ArrayList<>();
        try (Connection conn = connection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Cards")) {
            while (rs.next()) {
                Card card = DSOHelper.cardFromResultSet(rs);
                int amount = getCardAmount(card);
                cardStacks.add(new ItemStack<>(card, amount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cardStacks;
    }
}
