package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
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

public class CurrencyInventoryHSQLDB implements ICurrencyInventory {
    private final String dbPath;

    public CurrencyInventoryHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Card fromResultSet(final ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Currency getCurrentBalance(int id) {
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM Currency WHERE id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Currency(rs.getInt("balance"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addToBalance(Currency value, int id) {
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE Currency SET balance = balance + ? WHERE id = ?")) {
            stmt.setInt(1, value.getAmount());
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeFromBalance(Currency value, int id) {
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE Currency SET balance = balance - ? WHERE id = ?")) {
            stmt.setInt(1, value.getAmount());
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setBalance(Currency value, int id) {

    }
}
