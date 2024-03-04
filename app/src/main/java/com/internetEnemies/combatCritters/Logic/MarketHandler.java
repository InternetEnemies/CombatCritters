package com.internetEnemies.combatCritters.Logic;

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
            builder.setTransactionType(Transaction.TransactionType.CARD);
            Transaction transaction = builder.build();

            tList.add(transaction);
        }

        return tList;
    }

    public List<Transaction> getPackOffers() {
        PackCardDatabase db = PackCardDatabase.getInstance();

        IRegistry<Pack> packDB = db.getPackDB();
        List<Pack> packs = packDB.getAll();

        TransactionBuilder builder = new TransactionBuilder();
        List<Transaction> tList = new ArrayList<>();
        for(int i = 0; i < packs.size(); i++) {
            builder.reset();
            builder.addToGiven(new ItemStack<>(new Currency(i+1)));
            builder.addToReceived(new ItemStack<>(packs.get(i)));
            builder.setTransactionType(Transaction.TransactionType.PACK);
            Transaction transaction = builder.build();

            tList.add(transaction);
        }
        return tList;
    }
}
