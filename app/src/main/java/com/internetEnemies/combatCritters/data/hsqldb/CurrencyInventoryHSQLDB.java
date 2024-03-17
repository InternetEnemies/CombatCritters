package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.CurrencyHelper;
import com.internetEnemies.combatCritters.objects.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * CurrencyInventoryHSQLDB.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-17
 *
 * @PURPOSE:    sql implementation of players currency inventory
 */
public class CurrencyInventoryHSQLDB extends HSQLDBModel implements ICurrencyInventory {
    private static final int USER_ID = 1;// defines the players id
    private static final String UPDATE_FORMAT = "UPDATE PlayerInfo SET balance = %s ? WHERE id = ?";

    public CurrencyInventoryHSQLDB(String dbPath) {
        super(dbPath);
    }


    @Override
    public Currency getCurrentBalance() {
        Currency currency;
        try (Connection connection = this.connection()){
            PreparedStatement statement = connection.prepareStatement("SELECT balance FROM PlayerInfo WHERE id = ?");
            statement.setInt(1,USER_ID);
            ResultSet rs = statement.executeQuery();
            rs.next();
            currency = CurrencyHelper.fromResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting the users balance",e);
        }
        return currency;
    }

    @Override
    public void addToBalance(Currency value) {
        runUpdate("balance +",value);
    }

    @Override
    public void removeFromBalance(Currency value) {
        runUpdate("balance -",value);
    }

    @Override
    public void setBalance(Currency value) {
        runUpdate("",value);
    }

    /**
     * update the currency in some way
     * @param operator sql operator to do on players 'balance'
     * @param currency currency to update with
     */
    private void runUpdate(String operator, Currency currency){
        try (Connection connection = this.connection()){
            PreparedStatement statement = connection.prepareStatement(String.format(UPDATE_FORMAT,operator));
            statement.setInt(1, currency.getAmount());
            statement.setInt(2, USER_ID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("error while updating the player balance",e);
        }
    }
}
