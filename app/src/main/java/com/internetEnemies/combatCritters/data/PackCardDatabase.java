package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PackCardDatabase {
    private static PackCardDatabase INSTANCE;

    private final IRegistry<Pack> packDB;
    private final IRegistry<Card> cardDB;

    private PackCardDatabase() {
        //TODO: Create cards and data for each stub

        Map<Integer, Pack> testPacks = new TreeMap<>();
        Map<Integer, Card> testCards = new TreeMap<>();

        packDB = new PacksStub(testPacks);
        cardDB = new CardsStub(testCards);
    }

    public static PackCardDatabase getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PackCardDatabase();
        }
        return INSTANCE;
    }
    public IRegistry<Pack> getPackDB(){return packDB;}
    public IRegistry<Card> getCardDB(){return cardDB;}

}
