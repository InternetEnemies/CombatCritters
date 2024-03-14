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
    private final List<MarketTransaction> bundleOffers;
    private final List<MarketTransaction> cardOffers;
    private final List<MarketTransaction> packOffers;

    public MarketDB(List<MarketTransaction> bundleOffers,List<MarketTransaction> cardOffers,List<MarketTransaction> packOffers){
        this();
        this.bundleOffers.addAll(bundleOffers);
        this.cardOffers.addAll(cardOffers);
        this.packOffers.addAll(packOffers);
    }

    public MarketDB(){
        bundleOffers = new ArrayList<>();
        cardOffers = new ArrayList<>();
        packOffers = new ArrayList<>();
    }

    public void addBundleOffer(MarketTransaction offer) {
        assert offer != null;
        if(bundleOffers.contains(offer)){
            throw new UnsupportedOperationException("Bundle Market offer already in MarketDB");
        }
        bundleOffers.add(offer);
    }

    public void addCardOffer(MarketTransaction offer) {
        assert offer != null;
        if(cardOffers.contains(offer)){
            throw new UnsupportedOperationException("Card Market offer already in MarketDB");
        }
        cardOffers.add(offer);
    }

    public void addPackOffer(MarketTransaction offer) {
        assert offer != null;
        if(packOffers.contains(offer)){
            throw new UnsupportedOperationException("Pack Market offer already in MarketDB");
        }
        packOffers.add(offer);
    }

    @Override
    public List<MarketTransaction> getCardOffers(){
        return cardOffers;
    }

    @Override
    public List<MarketTransaction> getPackOffers(){
        return packOffers;
    }

    @Override
    public List<MarketTransaction> getBundleOffers(){
        return bundleOffers;
    }
}
