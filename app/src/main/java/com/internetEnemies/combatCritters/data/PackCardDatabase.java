/**
 * PackCardDatabase.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-03
 *
 * @PURPOSE:    fake database for packs and cards
 */

package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.application.Main;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.Logic.CardSlotBuilder;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.Logic.PackBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class PackCardDatabase {
    private static PackCardDatabase INSTANCE;

    private final IRegistry<Pack> packDB;
    private final IRegistry<Card> cardDB;

    private PackCardDatabase() {
        String path = Main.getDBPathName();
        List<Pack> testPacks = new ArrayList<>();
        cardDB = new RegistryCardHSQLDB(path);


        NavigableMap<Double, Card.Rarity> fullOdds = new TreeMap<>();
        fullOdds.put(4.0, Card.Rarity.COMMON);
        fullOdds.put(3.5, Card.Rarity.UNCOMMON);
        fullOdds.put(2.5, Card.Rarity.RARE);
        fullOdds.put(1.75, Card.Rarity.EPIC);
        fullOdds.put(0.25, Card.Rarity.LEGENDARY);

        NavigableMap<Double, Card.Rarity> guaranteedRare = new TreeMap<>();
        guaranteedRare.put(6.0, Card.Rarity.RARE);
        guaranteedRare.put(3.0, Card.Rarity.EPIC);
        guaranteedRare.put(1.0, Card.Rarity.LEGENDARY);

        List<Card> pack1Setlist = new ArrayList<>();
        pack1Setlist.add(cardDB.getSingle(1));
        pack1Setlist.add(cardDB.getSingle(2));
        pack1Setlist.add(cardDB.getSingle(3));
        pack1Setlist.add(cardDB.getSingle(4));
        pack1Setlist.add(cardDB.getSingle(15));
        pack1Setlist.add(cardDB.getSingle(16));
        pack1Setlist.add(cardDB.getSingle(17));
        pack1Setlist.add(cardDB.getSingle(18));
        pack1Setlist.add(cardDB.getSingle(27));
        pack1Setlist.add(cardDB.getSingle(28));
        pack1Setlist.add(cardDB.getSingle(29));
        pack1Setlist.add(cardDB.getSingle(36));
        pack1Setlist.add(cardDB.getSingle(37));
        pack1Setlist.add(cardDB.getSingle(42));

        List<Card> pack2Setlist = new ArrayList<>();
        pack2Setlist.add(cardDB.getSingle(5));
        pack2Setlist.add(cardDB.getSingle(6));
        pack2Setlist.add(cardDB.getSingle(7));
        pack2Setlist.add(cardDB.getSingle(8));
        pack2Setlist.add(cardDB.getSingle(9));
        pack2Setlist.add(cardDB.getSingle(19));
        pack2Setlist.add(cardDB.getSingle(20));
        pack2Setlist.add(cardDB.getSingle(21));
        pack2Setlist.add(cardDB.getSingle(22));
        pack2Setlist.add(cardDB.getSingle(30));
        pack2Setlist.add(cardDB.getSingle(31));
        pack2Setlist.add(cardDB.getSingle(32));
        pack2Setlist.add(cardDB.getSingle(38));
        pack2Setlist.add(cardDB.getSingle(39));
        pack2Setlist.add(cardDB.getSingle(43));

        List<Card> pack3Setlist = new ArrayList<>();
        pack3Setlist.add(cardDB.getSingle(10));
        pack3Setlist.add(cardDB.getSingle(11));
        pack3Setlist.add(cardDB.getSingle(12));
        pack3Setlist.add(cardDB.getSingle(13));
        pack3Setlist.add(cardDB.getSingle(14));
        pack3Setlist.add(cardDB.getSingle(23));
        pack3Setlist.add(cardDB.getSingle(24));
        pack3Setlist.add(cardDB.getSingle(25));
        pack3Setlist.add(cardDB.getSingle(26));
        pack3Setlist.add(cardDB.getSingle(33));
        pack3Setlist.add(cardDB.getSingle(34));
        pack3Setlist.add(cardDB.getSingle(35));
        pack3Setlist.add(cardDB.getSingle(40));
        pack3Setlist.add(cardDB.getSingle(41));
        pack3Setlist.add(cardDB.getSingle(44));

        PackBuilder packBuilder = new PackBuilder();
        CardSlotBuilder cardSlotBuilder = new CardSlotBuilder();

        cardSlotBuilder.addProbabilityMap(fullOdds);

        packBuilder.addSlot(cardSlotBuilder.build());
        packBuilder.addSlot(cardSlotBuilder.build());
        packBuilder.addSlot(cardSlotBuilder.build());
        packBuilder.addSlot(cardSlotBuilder.build());

        cardSlotBuilder.reset();
        cardSlotBuilder.addProbabilityMap(guaranteedRare);
        packBuilder.addSlot(cardSlotBuilder.build());

        packBuilder.setId(0);
        packBuilder.setName("Assorted Critters");
        packBuilder.setImage("");
        packBuilder.addSetList(pack1Setlist);

        testPacks.add(packBuilder.build());

        cardSlotBuilder.reset();
        packBuilder.reset();

        cardSlotBuilder.addProbabilityMap(fullOdds);

        packBuilder.addSlot(cardSlotBuilder.build());
        packBuilder.addSlot(cardSlotBuilder.build());
        packBuilder.addSlot(cardSlotBuilder.build());
        packBuilder.addSlot(cardSlotBuilder.build());

        cardSlotBuilder.reset();
        cardSlotBuilder.addProbabilityMap(guaranteedRare);
        packBuilder.addSlot(cardSlotBuilder.build());

        packBuilder.setId(1);
        packBuilder.setName("Athletic Assailants");
        packBuilder.setImage("");
        packBuilder.addSetList(pack2Setlist);

        testPacks.add(packBuilder.build());

        cardSlotBuilder.reset();
        packBuilder.reset();

        cardSlotBuilder.addProbabilityMap(fullOdds);

        packBuilder.addSlot(cardSlotBuilder.build());
        packBuilder.addSlot(cardSlotBuilder.build());
        packBuilder.addSlot(cardSlotBuilder.build());
        packBuilder.addSlot(cardSlotBuilder.build());

        cardSlotBuilder.reset();
        cardSlotBuilder.addProbabilityMap(guaranteedRare);
        packBuilder.addSlot(cardSlotBuilder.build());

        packBuilder.setId(0);
        packBuilder.setName("Power of the Ocean");
        packBuilder.setImage("");
        packBuilder.addSetList(pack3Setlist);

        testPacks.add(packBuilder.build());

        cardSlotBuilder.reset();
        packBuilder.reset();


        packDB = new Registry<>(testPacks);
    }

    public static synchronized PackCardDatabase getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PackCardDatabase();
        }
        return INSTANCE;
    }
    public IRegistry<Pack> getPackDB(){
        return packDB;
    }
    public IRegistry<Card> getCardDB(){
        return cardDB;
    }

}
