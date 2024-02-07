package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardSlot;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class PackCardDatabase {
    private static PackCardDatabase INSTANCE;

    private final IRegistry<Pack> packDB;
    private final IRegistry<Card> cardDB;
    static Card[] cards = {
            new CritterCard(1, "Waffle Warrior", "card_id_1",3, Card.Rarity.COMMON,1,3, null),
            new CritterCard(2, "Pillow Pugilist", "card_id_2",2, Card.Rarity.COMMON,1,2, null),
            new CritterCard(3, "Sock Samurai", "card_id_3",1, Card.Rarity.COMMON,1,1, null),
            new CritterCard(4, "Bubble Gum Battlers", "card_id_4",3, Card.Rarity.COMMON,0,1, null),
            new CritterCard(5, "Tuba Trooper", "card_id_5",1, Card.Rarity.COMMON,1,2, null),
            new CritterCard(6, "Hole-In-One Hero", "card_id_6",3, Card.Rarity.COMMON,2,1, null),
            new CritterCard(7, "Jumpshot Soldier", "card_id_7",1, Card.Rarity.COMMON,2,1, null),
            new CritterCard(8, "Slimy Slugger", "card_id_8",2, Card.Rarity.COMMON,1,3, null),
            new CritterCard(9, "Dodgeball Dragon", "card_id_9",2, Card.Rarity.COMMON,1,2, null),
            new CritterCard(10, "Track and Field Titan", "card_id_10",3, Card.Rarity.COMMON,0,2, null),
            new CritterCard(11, "Shredder Shark", "card_id_11",2, Card.Rarity.COMMON,2,1, null),
            new CritterCard(12, "Deadly Fisherman", "card_id_12",3, Card.Rarity.COMMON,1,1, null),
            new CritterCard(13, "Ambush of Anglers", "card_id_13",1, Card.Rarity.COMMON,1,1, null),
            new CritterCard(14, "Airborne Guppy", "card_id_14",1, Card.Rarity.COMMON,1,1, null),
            new CritterCard(15, "Conjurer of Coral", "card_id_15",2, Card.Rarity.COMMON,2,3, null),
            new CritterCard(17, "Furious Fridge Fighter", "card_id_17",4, Card.Rarity.UNCOMMON,1,1, null),
            new CritterCard(18, "Bubble-Wrapped Berserker", "card_id_18",3, Card.Rarity.UNCOMMON,1,3, null),
            new CritterCard(19, "Banana Barbarian", "card_id_19",1, Card.Rarity.UNCOMMON,2,4, null),
            new CritterCard(20, "Pencil Swordsman", "card_id_20",4, Card.Rarity.UNCOMMON,2,4, null),
            new CritterCard(21, "Goalkeeper Guy", "card_id_21",1, Card.Rarity.UNCOMMON,1,3, null),
            new CritterCard(22, "Superstriker Gal", "card_id_22",3, Card.Rarity.UNCOMMON,3,4, null),
            new CritterCard(23, "Slapshot Beast", "card_id_23",1, Card.Rarity.UNCOMMON,4,3, null),
            new CritterCard(24, "Demonic Referee", "card_id_24",4, Card.Rarity.UNCOMMON,3,4, null),
            new CritterCard(25, "Fish Fist Fighter", "card_id_25",3, Card.Rarity.UNCOMMON,3,4, null),
            new CritterCard(26, "Giant Carp", "card_id_26",1, Card.Rarity.UNCOMMON,3,3, null),
            new CritterCard(27, "Swordfish of the Samurai", "card_id_27",1, Card.Rarity.UNCOMMON,2,4, null),
            new CritterCard(28, "Heavily Armored Turtle", "card_id_28",1, Card.Rarity.UNCOMMON,2,4, null),
            new CritterCard(32, "Kitchen Knight", "card_id_32",3, Card.Rarity.RARE,1,4, null),
            new CritterCard(33, "Giggly Gorgon, the Snickering Serpent", "card_id_33",4, Card.Rarity.RARE,3,5, null),
            new CritterCard(34, "Snickerdoodle Sphinx, the Grinning Guardian", "card_id_34",2, Card.Rarity.RARE,2,1, null),
            new CritterCard(35, "Surfs Up Cyborg", "card_id_35",4, Card.Rarity.RARE,3,4, null),
            new CritterCard(36, "Volleyball Vanguard", "card_id_36",1, Card.Rarity.RARE,2,5, null),
            new CritterCard(37, "Slam Dunk Sorcerer", "card_id_37",3, Card.Rarity.RARE,3,3, null),
            new CritterCard(38, "Island Whale", "card_id_38",3, Card.Rarity.RARE,1,3, null),
            new CritterCard(39, "Deep Sea Beast", "card_id_39",2, Card.Rarity.RARE,3,4, null),
            new CritterCard(40, "Barrage of Fish", "card_id_40",1, Card.Rarity.RARE,2,3, null),
            new CritterCard(47, "Sweet-Toothed Dragon", "card_id_47",2, Card.Rarity.EPIC,4,6, null),
            new CritterCard(48, "UglyMan, the Hideous Hero", "card_id_48",3, Card.Rarity.EPIC,4,5, null),
            new CritterCard(49, "Karate Boxer Man", "card_id_49",3, Card.Rarity.EPIC,4,5, null),
            new CritterCard(50, "Knockout Champion", "card_id_50",2, Card.Rarity.EPIC,4,5, null),
            new CritterCard(51, "Deep Sea King", "card_id_51",3, Card.Rarity.EPIC,4,6, null),
            new CritterCard(52, "Mega Kraken", "card_id_52",3, Card.Rarity.EPIC,5,6, null),
            new CritterCard(57, "White-Eyes Blue Dragon", "card_id_57",2, Card.Rarity.LEGENDARY,4,5, null),
            new CritterCard(58, "The Sports Wizard", "card_id_58",3, Card.Rarity.LEGENDARY,4,6, null),
            new CritterCard(59, "Wrath of the Ocean", "card_id_59",3, Card.Rarity.LEGENDARY,5,5, null),

    };

    private PackCardDatabase() {

        Map<Integer, Pack> testPacks = new TreeMap<>();
        Map<Integer, Card> testCards = new TreeMap<>();
        for (Card c:cards) {
            testCards.put(c.getId(), c);
        }

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


        CardSlot slot1 = new CardSlot(fullOdds);
        CardSlot slot2 = new CardSlot(fullOdds);
        CardSlot slot3 = new CardSlot(fullOdds);
        CardSlot slot4 = new CardSlot(fullOdds);
        CardSlot slot5 = new CardSlot(guaranteedRare);

        List<CardSlot> packSlots = new ArrayList<>();
        packSlots.add(slot1);
        packSlots.add(slot2);
        packSlots.add(slot3);
        packSlots.add(slot4);
        packSlots.add(slot5);

        List<Card> pack1Setlist = new ArrayList<>();
        pack1Setlist.add(cards[0]);
        pack1Setlist.add(cards[1]);
        pack1Setlist.add(cards[2]);
        pack1Setlist.add(cards[3]);
        pack1Setlist.add(cards[4]);
        pack1Setlist.add(cards[15]);
        pack1Setlist.add(cards[16]);
        pack1Setlist.add(cards[17]);
        pack1Setlist.add(cards[18]);
        pack1Setlist.add(cards[27]);
        pack1Setlist.add(cards[28]);
        pack1Setlist.add(cards[29]);
        pack1Setlist.add(cards[36]);
        pack1Setlist.add(cards[37]);
        pack1Setlist.add(cards[42]);

        List<Card> pack2Setlist = new ArrayList<>();
        pack2Setlist.add(cards[5]);
        pack2Setlist.add(cards[6]);
        pack2Setlist.add(cards[7]);
        pack2Setlist.add(cards[8]);
        pack2Setlist.add(cards[9]);
        pack2Setlist.add(cards[19]);
        pack2Setlist.add(cards[20]);
        pack2Setlist.add(cards[21]);
        pack2Setlist.add(cards[22]);
        pack2Setlist.add(cards[30]);
        pack2Setlist.add(cards[31]);
        pack2Setlist.add(cards[32]);
        pack2Setlist.add(cards[38]);
        pack2Setlist.add(cards[39]);
        pack2Setlist.add(cards[43]);

        List<Card> pack3Setlist = new ArrayList<>();
        pack3Setlist.add(cards[10]);
        pack3Setlist.add(cards[11]);
        pack3Setlist.add(cards[12]);
        pack3Setlist.add(cards[13]);
        pack3Setlist.add(cards[14]);
        pack3Setlist.add(cards[23]);
        pack3Setlist.add(cards[24]);
        pack3Setlist.add(cards[25]);
        pack3Setlist.add(cards[26]);
        pack3Setlist.add(cards[33]);
        pack3Setlist.add(cards[34]);
        pack3Setlist.add(cards[35]);
        pack3Setlist.add(cards[40]);
        pack3Setlist.add(cards[41]);
        pack3Setlist.add(cards[44]);

        Pack pack1 = new Pack(1,"Assorted Critters", "", packSlots, pack1Setlist);
        Pack pack2 = new Pack(2,"Athletic Assailants", "", packSlots, pack2Setlist);
        Pack pack3 = new Pack(3,"Power of the Sea", "", packSlots, pack3Setlist);

        testPacks.put(pack1.getId(), pack1);
        testPacks.put(pack2.getId(), pack2);
        testPacks.put(pack3.getId(), pack3);

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
