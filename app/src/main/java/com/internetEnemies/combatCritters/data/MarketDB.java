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

import com.internetEnemies.combatCritters.data.market.IMarketDB;
import com.internetEnemies.combatCritters.objects.MarketTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public void adjustDiscount(Map<Integer, Double> discounts) {
        adjustAllDiscounts(discounts, bundleOffers);
        adjustAllDiscounts(discounts, cardOffers);
        adjustAllDiscounts(discounts, packOffers);
    }

    @Override
    public List<MarketTransaction> getAllOffers() {
        List<MarketTransaction> allTransactions = new ArrayList<>();

        allTransactions.addAll(this.getBundleOffers());
        allTransactions.addAll(this.getCardOffers());
        allTransactions.addAll(this.getPackOffers());

        return allTransactions;
    }

    private void adjustAllDiscounts(Map<Integer, Double> discounts, List<MarketTransaction> transactions){
        //this doesnt feel very data layer-y, this stub is weird

        for (Map.Entry<Integer, Double> discount: discounts.entrySet()) {
            for (MarketTransaction transaction: transactions) {
                if (discount.getKey() == transaction.getId()){
                    MarketTransaction transactionClone = new MarketTransaction(transaction.getId(), transaction.getReceived(), transaction.getPriceWithoutDiscount(), discount.getValue());
                    transactions.remove(transaction);
                    transactions.add(transactionClone);
                }
            }

        }
    }

}
