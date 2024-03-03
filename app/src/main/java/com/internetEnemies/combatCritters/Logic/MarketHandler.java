package com.internetEnemies.combatCritters.Logic;

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
        Currency currency = new Currency();
        currency.setAmount(20);


        Card c1 = new CritterCard(0, "Waffle Warrior", "card_id_1",3, Card.Rarity.COMMON,1,3, null);
//        Card c2 = new CritterCard(1, "Pillow Pugilist", "card_id_2",2, Card.Rarity.COMMON,1,2, null);
//        Card c3 = new CritterCard(2, "Sock Samurai", "card_id_3",1, Card.Rarity.COMMON,1,1, null);

        TransactionBuilder builder = new TransactionBuilder();
        builder.addToGiven(new ItemStack<>(currency));
        builder.addToReceived(new ItemStack<>(c1));
        Transaction transaction = builder.build();
        List<Transaction> tList = new ArrayList<>();
        tList.add(transaction);
        return tList;
    }
}
