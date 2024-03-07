/**
 * TradeRegistry.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      04-March-2024
 *
 * @PURPOSE:     implements IRegistry
 *               provide TradeTransaction to TradesHandler
 *               Manage a list of TradeTransaction
 */

package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.TradeTransaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TradeRegistry implements IRegistry<TradeTransaction>{
    private final List<TradeTransaction> offers;

    public TradeRegistry(List<TradeTransaction> start){
        this();
        offers.addAll(start);
    }

    public TradeRegistry(){
        offers = new ArrayList<TradeTransaction>();
    }

    @Override
    public TradeTransaction getSingle(int id) {
        return offers.get(id);
    }

    @Override
    public List getAll() {
        return Collections.unmodifiableList(new ArrayList<>(offers));
    }

    @Override
    public List getListOf(List<Integer> ids) {
        List<TradeTransaction> result = new ArrayList<>();
        for(int id : ids) {
            result.add(offers.get(id));
        }
        return result;
    }

    /**
     *  add an offer into MarketDB
     * @param offer a TradeTransaction to add
     */
    public void add(TradeTransaction offer){
        assert offer != null;
        if(offers.contains(offer)){
            throw new UnsupportedOperationException("Trade offer already in TradeRegistry");
        }
        offers.add(offer);
    }

}
