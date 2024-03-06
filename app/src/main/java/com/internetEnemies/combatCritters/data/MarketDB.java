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

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.objects.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MarketDB implements IMarketDB {
    private final List<MarketTransaction> offers;

    public MarketDB(List<MarketTransaction> start){
        this();
        offers.addAll(start);
    }

    public MarketDB(){
        offers = new ArrayList<MarketTransaction>();
    }

    @Override
    public MarketTransaction getSingle(int id) {
        return offers.get(id);
    }

    @Override
    public List getAll() {
        return Collections.unmodifiableList(new ArrayList<>(offers));
    }

    @Override
    public List getListOf(List<Integer> ids) {
        List<MarketTransaction> result = new ArrayList<>();
        for(int id : ids) {
            result.add(offers.get(id));
        }
        return result;
    }

    @Override
    public void add(MarketTransaction offer){
        assert offer != null;
        if(offers.contains(offer)){
            throw new UnsupportedOperationException("Market offer already in MarketDB");
        }
        offers.add(offer);
    }

    @Override
    public List<MarketTransaction> getCardOffers(){
        List<MarketTransaction> result = new ArrayList<MarketTransaction>();
        for(MarketTransaction i: offers){
            int numOfItems = i.getReceived().size();
            assert numOfItems > 0;
            if(i.getReceived().get(0).getItem() instanceof Card && numOfItems == 1){
                result.add(i);
            }
        }
        return result;
    }

    @Override
    public List<MarketTransaction> getPackOffers(){
        List<MarketTransaction> result = new ArrayList<MarketTransaction>();
        for(MarketTransaction i: offers){
            int numOfItems = i.getReceived().size();
            assert numOfItems > 0;
            if(i.getReceived().get(0).getItem() instanceof Pack && numOfItems == 1){
                result.add(i);
            }
        }
        return result;
    }

    @Override
    public List<MarketTransaction> getBundleOffers(){
        List<MarketTransaction> result = new ArrayList<MarketTransaction>();
        for(MarketTransaction i: offers){
            int numOfItems = i.getReceived().size();
            assert numOfItems > 0;
            if(numOfItems > 1){
                result.add(i);
            }
        }
        return result;
    }
}
