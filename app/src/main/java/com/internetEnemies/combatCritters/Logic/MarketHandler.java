package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.PackCardDatabase;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemStack;
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
}
