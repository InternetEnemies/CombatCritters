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
import java.util.Collections;
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
    public Transaction getSingle(int id) {
        return offers.get(id);
    }

    @Override
    public List getAll() {
        return Collections.unmodifiableList(new ArrayList<>(offers));
    }

    @Override
    public List getListOf(List<Integer> ids) {
        List<Transaction> result = new ArrayList<>();
        for(int id : ids) {
            result.add(offers.get(id));
        }
        return result;
    }

    /**
     *  add an offer into MarketDB
     * @param offer a MarketTransaction to add
     */
    public void add(Transaction offer){
        assert offer != null;
        if(offers.contains(offer)){
            throw new UnsupportedOperationException("Market offer already in MarketDB");
        }
        offers.add(offer);
    }
}
