/**
 * MarketDB.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      04-March-2024
 *
 * @PURPOSE:     implements IRegistry
 *               provide MarketTransaction to MarketHandler
 *               Manage a list of MarketTransaction
 */

package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Transaction;

import java.util.ArrayList;
import java.util.List;

//TODO should change it to MarketTransaction
public class MarketDB implements IRegistry<Transaction> {
    private final List<Transaction> offers;

    public MarketDB(List<Transaction> start){
        this();
        offers.addAll(start);
    }

    public MarketDB(){
        offers = new ArrayList<Transaction>();
    }

    @Override
    public Object getSingle(int id) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public List getListOf(List ids) {
        return null;
    }
}
