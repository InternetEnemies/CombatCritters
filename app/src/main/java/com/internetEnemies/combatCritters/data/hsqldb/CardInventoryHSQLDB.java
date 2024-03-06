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

    private Card fromResultSet(final ResultSet rs) throws SQLException {
        final Integer id = rs.getInt("id");
        final String name = rs.getString("name");
        final String image = rs.getString("image");
        final Integer playCost = rs.getInt("playCost");
        final Integer rarity = rs.getInt("rarity");
        final String type = rs.getString("type");
        Card card = null;

        List<Integer> abilities = new ArrayList<>();
        Card.Rarity rare = Card.Rarity.values()[rarity];

        switch(type) {
            case "critter":
                final Integer damage = rs.getInt("damage");
                final Integer health = rs.getInt("health");
                final Integer ability = rs.getInt("abilities");
                abilities.add(ability);
                card = new CritterCard(id, name, image, playCost, rare, damage, health, abilities);
                break;
            case "item":
                final Integer effectId = rs.getInt("effectId");
                card = new ItemCard(id, name, image, playCost, rare, effectId);
                break;
        }
        return card;
    }

    @Override
    public int getCardAmount(Card card) {
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM Cards WHERE id = ?")) {
            stmt.setInt(1, card.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // TODO prob not return 0 here lol
    }

    @Override
    public void addCard(Card card) {
        try (Connection conn = connection();//todo this isnt done here
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Cards (id, name, image, playCost, rarity, type, damage, health, abilities, effectId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setInt(1, card.getId());
            stmt.setString(2, card.getName());
            stmt.setString(3, card.getImage());
            stmt.setInt(4, card.getPlayCost());
            stmt.setInt(5, card.getRarity().ordinal());
            stmt.setString(6, card instanceof CritterCard ? "critter" : "item");
            if (card instanceof CritterCard) {
                CritterCard critterCard = (CritterCard) card;
                stmt.setInt(7, critterCard.getDamage());
                stmt.setInt(8, critterCard.getHealth());
                stmt.setInt(9, critterCard.getAbilities().get(0));
                stmt.setNull(10, Types.INTEGER);
            } else if (card instanceof ItemCard) {
                ItemCard itemCard = (ItemCard) card;
                stmt.setNull(7, Types.INTEGER);
                stmt.setNull(8, Types.INTEGER);
                stmt.setNull(9, Types.INTEGER);
                stmt.setInt(10, itemCard.getEffectId());
            }
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
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Cards WHERE id = ? LIMIT ?")) {
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
                Card card = fromResultSet(rs);
                int amount = getCardAmount(card);
                cardStacks.add(new ItemStack<>(card, amount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cardStacks;
    }
}
