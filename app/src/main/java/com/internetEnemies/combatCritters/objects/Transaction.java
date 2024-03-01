package com.internetEnemies.combatCritters.objects;
import java.util.List;

/**
 * Transaction.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-01
 *
 * @PURPOSE: Transaction class to store items to be removed and added to the player's inventory.
 */

public class Transaction {
    List<TransactionItem> given;
    List<TransactionItem> received;

    Transaction(List<TransactionItem> given, List<TransactionItem> received){
        this.given = given;
        this.received = received;
    }
    public List<TransactionItem> getGiven(){
        return given;
    }
    public List<TransactionItem> getReceived(){
        return received;
    }


}
