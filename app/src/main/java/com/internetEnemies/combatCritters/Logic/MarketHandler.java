package com.internetEnemies.combatCritters.Logic;

import android.util.Log;

import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.PackCardDatabase;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.objects.Transaction;
import com.internetEnemies.combatCritters.objects.TransactionBuilder;

import java.util.ArrayList;
import java.util.List;

public class MarketHandler {

    public List<Transaction> getCardOffers() {
        PackCardDatabase db = PackCardDatabase.getInstance();

        IRegistry<Card> cardsDB = db.getCardDB();
        List<Card> cards = cardsDB.getAll();

        TransactionBuilder builder = new TransactionBuilder();
        List<Transaction> tList = new ArrayList<>();
        for(int i = 0; i < 25; i++) {
            builder.reset();
            builder.addToGiven(new ItemStack<>(new Currency(i+1)));
            builder.addToReceived(new ItemStack<>(cards.get(i)));
            Transaction transaction = builder.build();

            tList.add(transaction);
        }

        return tList;
    }

    public List<Transaction> getPackOffers() {
        PackCardDatabase db = PackCardDatabase.getInstance();
        IRegistry<Card> cardsDB = db.getCardDB();
        IRegistry<Pack> packDB = db.getPackDB();
        List<Pack> packs = packDB.getAll();


        TransactionBuilder builder = new TransactionBuilder();
        List<Transaction> tList = new ArrayList<>();
        for(int i = 0; i < packs.size(); i++) {
            builder.reset();
            builder.addToGiven(new ItemStack<>(new Currency(i+1)));
            builder.addToReceived(new ItemStack<>(packs.get(i)));
            Transaction transaction = builder.build();

            tList.add(transaction);
        }
        return tList;
    }

    public List<Transaction> getBundleOffers() {
        PackCardDatabase db = PackCardDatabase.getInstance();
        IRegistry<Card> cardsDB = db.getCardDB();
        List<Card> cards = cardsDB.getAll();
        IRegistry<Pack> packDB = db.getPackDB();
        List<Pack> packs = packDB.getAll();

        TransactionBuilder builder = new TransactionBuilder();
        List<Transaction> tList = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            builder.reset();
            builder.addToGiven(new ItemStack<>(new Currency(i+1)));
            builder.addToReceived(new ItemStack<>(packs.get(0)));
            builder.addToReceived(new ItemStack<>(packs.get(0)));
            builder.addToReceived(new ItemStack<>(packs.get(0)));
            builder.addToReceived(new ItemStack<>(cards.get(i)));
            Transaction transaction = builder.build();

            tList.add(transaction);
        }
        return tList;
    }

    public List<Transaction> getDealOffers() {
        PackCardDatabase db = PackCardDatabase.getInstance();
        IRegistry<Card> cardsDB = db.getCardDB();
        List<Card> cards = cardsDB.getAll();
        IRegistry<Pack> packDB = db.getPackDB();
        List<Pack> packs = packDB.getAll();

        TransactionBuilder builder = new TransactionBuilder();
        List<Transaction> tList = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            builder.reset();
            builder.addToGiven(new ItemStack<>(new Currency(i+1)));
            builder.addToReceived(new ItemStack<>(packs.get(i%3)));
            Transaction transaction = builder.build();
            tList.add(transaction);

            builder.reset();
            builder.addToGiven(new ItemStack<>(new Currency(i+1)));
            builder.addToReceived(new ItemStack<>(cards.get(i)));
            Transaction transaction1 = builder.build();
            tList.add(transaction1);



        }
        return tList;
    }
}
