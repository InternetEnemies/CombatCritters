package com.internetEnemies.combatCritters.Logic;

import android.util.Log;

import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.PackCardDatabase;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.MarketTransactionBuilder;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.objects.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//TODO: delete this class
public class TempMarketHandler implements IMarketHandler{

    @Override
    public List<MarketTransaction> getCardOffers() {
        PackCardDatabase db = PackCardDatabase.getInstance();

        IRegistry<Card> cardsDB = db.getCardDB();
        List<Card> cards = cardsDB.getAll();

        MarketTransactionBuilder builder = new MarketTransactionBuilder();
        List<MarketTransaction> tList = new ArrayList<>();
        for(int i = 0; i < 25; i++) {
            builder.reset();
            builder.setPrice(new Currency(i+1));
            builder.addToReceived(new ItemStack<>(cards.get(i)));


            Random r = new Random();
            if(r.nextBoolean()) {
                builder.setDiscount((i*1.0)/100);
            }
            MarketTransaction transaction = builder.build();

            tList.add(transaction);
        }

        return tList;
    }

    @Override
    public List<MarketTransaction> getPackOffers() {
        PackCardDatabase db = PackCardDatabase.getInstance();
        IRegistry<Pack> packDB = db.getPackDB();
        List<Pack> packs = packDB.getAll();


        MarketTransactionBuilder builder = new MarketTransactionBuilder();
        List<MarketTransaction> tList = new ArrayList<>();
        for(int i = 0; i < packs.size(); i++) {
            builder.reset();
            builder.setPrice(new Currency(i+1));
            builder.addToReceived(new ItemStack<>(packs.get(i)));
            MarketTransaction transaction = builder.build();

            tList.add(transaction);
        }
        return tList;
    }

    @Override
    public MarketTransaction getOffer(int i) {return new MarketTransaction(null, null );}

    @Override
    public boolean performTransaction(MarketTransaction t) {return false;}
    @Override
    public List<MarketTransaction> getOffers() {return null;}

    @Override
    public List<MarketTransaction> getBundleOffers() {
        PackCardDatabase db = PackCardDatabase.getInstance();
        IRegistry<Card> cardsDB = db.getCardDB();
        List<Card> cards = cardsDB.getAll();
        IRegistry<Pack> packDB = db.getPackDB();
        List<Pack> packs = packDB.getAll();

        MarketTransactionBuilder builder = new MarketTransactionBuilder();
        List<MarketTransaction> tList = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            builder.reset();
            builder.setPrice(new Currency(i+1));
            builder.addToReceived(new ItemStack<>(packs.get(0)));
            builder.addToReceived(new ItemStack<>(packs.get(0)));
            builder.addToReceived(new ItemStack<>(packs.get(0)));
            builder.addToReceived(new ItemStack<>(cards.get(i)));
            MarketTransaction transaction = builder.build();

            tList.add(transaction);
        }
        return tList;
    }
}
