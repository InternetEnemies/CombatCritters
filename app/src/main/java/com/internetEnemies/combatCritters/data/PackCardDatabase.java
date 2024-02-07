package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardSlot;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class PackCardDatabase {
    private static PackCardDatabase INSTANCE;

    private final IRegistry<Pack> packDB;
    private final IRegistry<Card> cardDB;

    private PackCardDatabase() {

        List<Pack> testPacks = new ArrayList<>();
        List<Card> cards = new ArrayList<>();
        cards.add(new CritterCard(0, "Waffle Warrior", "card_id_1",3, Card.Rarity.COMMON,1,3, null));
        cards.add(new CritterCard(1, "Pillow Pugilist", "card_id_2",2, Card.Rarity.COMMON,1,2, null));
        cards.add(new CritterCard(2, "Sock Samurai", "card_id_3",1, Card.Rarity.COMMON,1,1, null));
        cards.add(new CritterCard(3, "Bubble Gum Battlers", "card_id_4",3, Card.Rarity.COMMON,0,1, null));
        cards.add(new CritterCard(4, "Tuba Trooper", "card_id_5",1, Card.Rarity.COMMON,1,2, null));
        cards.add(new CritterCard(5, "Hole-In-One Hero", "card_id_6",3, Card.Rarity.COMMON,2,1, null));
        cards.add(new CritterCard(6, "Jumpshot Soldier", "card_id_7",1, Card.Rarity.COMMON,2,1, null));
        cards.add(new CritterCard(7, "Slimy Slugger", "card_id_8",2, Card.Rarity.COMMON,1,3, null));
        cards.add(new CritterCard(8, "Dodgeball Dragon", "card_id_9",2, Card.Rarity.COMMON,1,2, null));
        cards.add(new CritterCard(9, "Track and Field Titan", "card_id_10",3, Card.Rarity.COMMON,0,2, null));
        cards.add(new CritterCard(10, "Shredder Shark", "card_id_11",2, Card.Rarity.COMMON,2,1, null));
        cards.add(new CritterCard(11, "Deadly Fisherman", "card_id_12",3, Card.Rarity.COMMON,1,1, null));
        cards.add(new CritterCard(12, "Ambush of Anglers", "card_id_13",1, Card.Rarity.COMMON,1,1, null));
        cards.add(new CritterCard(13, "Airborne Guppy", "card_id_14",1, Card.Rarity.COMMON,1,1, null));
        cards.add(new CritterCard(14, "Conjurer of Coral", "card_id_15",2, Card.Rarity.COMMON,2,3, null));
        cards.add(new CritterCard(16, "Furious Fridge Fighter", "card_id_17",4, Card.Rarity.UNCOMMON,1,1, null));
        cards.add(new CritterCard(17, "Bubble-Wrapped Berserker", "card_id_18",3, Card.Rarity.UNCOMMON,1,3, null));
        cards.add(new CritterCard(18, "Banana Barbarian", "card_id_19",1, Card.Rarity.UNCOMMON,2,4, null));
        cards.add(new CritterCard(19, "Pencil Swordsman", "card_id_20",4, Card.Rarity.UNCOMMON,2,4, null));
        cards.add(new CritterCard(20, "Goalkeeper Guy", "card_id_21",1, Card.Rarity.UNCOMMON,1,3, null));
        cards.add(new CritterCard(21, "Superstriker Gal", "card_id_22",3, Card.Rarity.UNCOMMON,3,4, null));
        cards.add(new CritterCard(22, "Slapshot Beast", "card_id_23",1, Card.Rarity.UNCOMMON,4,3, null));
        cards.add(new CritterCard(23, "Demonic Referee", "card_id_24",4, Card.Rarity.UNCOMMON,3,4, null));
        cards.add(new CritterCard(24, "Fish Fist Fighter", "card_id_25",3, Card.Rarity.UNCOMMON,3,4, null));
        cards.add(new CritterCard(25, "Giant Carp", "card_id_26",1, Card.Rarity.UNCOMMON,3,3, null));
        cards.add(new CritterCard(26, "Swordfish of the Samurai", "card_id_27",1, Card.Rarity.UNCOMMON,2,4, null));
        cards.add(new CritterCard(27, "Heavily Armored Turtle", "card_id_28",1, Card.Rarity.UNCOMMON,2,4, null));
        cards.add(new CritterCard(31, "Kitchen Knight", "card_id_32",3, Card.Rarity.RARE,1,4, null));
        cards.add(new CritterCard(32, "Giggly Gorgon, the Snickering Serpent", "card_id_33",4, Card.Rarity.RARE,3,5, null));
        cards.add(new CritterCard(33, "Snickerdoodle Sphinx, the Grinning Guardian", "card_id_34",2, Card.Rarity.RARE,2,1, null));
        cards.add(new CritterCard(34, "Surfs Up Cyborg", "card_id_35",4, Card.Rarity.RARE,3,4, null));
        cards.add(new CritterCard(35, "Volleyball Vanguard", "card_id_36",1, Card.Rarity.RARE,2,5, null));
        cards.add(new CritterCard(36, "Slam Dunk Sorcerer", "card_id_37",3, Card.Rarity.RARE,3,3, null));
        cards.add(new CritterCard(37, "Island Whale", "card_id_38",3, Card.Rarity.RARE,1,3, null));
        cards.add(new CritterCard(38, "Deep Sea Beast", "card_id_39",2, Card.Rarity.RARE,3,4, null));
        cards.add(new CritterCard(39, "Barrage of Fish", "card_id_40",1, Card.Rarity.RARE,2,3, null));
        cards.add(new CritterCard(46, "Sweet-Toothed Dragon", "card_id_47",2, Card.Rarity.EPIC,4,6, null));
        cards.add(new CritterCard(47, "UglyMan, the Hideous Hero", "card_id_48",3, Card.Rarity.EPIC,4,5, null));
        cards.add(new CritterCard(48, "Karate Boxer Man", "card_id_49",3, Card.Rarity.EPIC,4,5, null));
        cards.add(new CritterCard(49, "Knockout Champion", "card_id_50",2, Card.Rarity.EPIC,4,5, null));
        cards.add(new CritterCard(50, "Deep Sea King", "card_id_51",3, Card.Rarity.EPIC,4,6, null));
        cards.add(new CritterCard(51, "Mega Kraken", "card_id_52",3, Card.Rarity.EPIC,5,6, null));
        cards.add(new CritterCard(56, "White-Eyes Blue Dragon", "card_id_57",2, Card.Rarity.LEGENDARY,4,5, null));
        cards.add(new CritterCard(57, "The Sports Wizard", "card_id_58",3, Card.Rarity.LEGENDARY,4,6, null));
        cards.add(new CritterCard(58, "Wrath of the Ocean", "card_id_59",3, Card.Rarity.LEGENDARY,5,5, null));

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
        pack1Setlist.add(cards.get(0));
        pack1Setlist.add(cards.get(1));
        pack1Setlist.add(cards.get(2));
        pack1Setlist.add(cards.get(3));
        pack1Setlist.add(cards.get(4));
        pack1Setlist.add(cards.get(15));
        pack1Setlist.add(cards.get(16));
        pack1Setlist.add(cards.get(17));
        pack1Setlist.add(cards.get(18));
        pack1Setlist.add(cards.get(27));
        pack1Setlist.add(cards.get(28));
        pack1Setlist.add(cards.get(29));
        pack1Setlist.add(cards.get(36));
        pack1Setlist.add(cards.get(37));
        pack1Setlist.add(cards.get(42));

        List<Card> pack2Setlist = new ArrayList<>();
        pack2Setlist.add(cards.get(5));
        pack2Setlist.add(cards.get(6));
        pack2Setlist.add(cards.get(7));
        pack2Setlist.add(cards.get(8));
        pack2Setlist.add(cards.get(9));
        pack2Setlist.add(cards.get(19));
        pack2Setlist.add(cards.get(20));
        pack2Setlist.add(cards.get(21));
        pack2Setlist.add(cards.get(22));
        pack2Setlist.add(cards.get(30));
        pack2Setlist.add(cards.get(31));
        pack2Setlist.add(cards.get(32));
        pack2Setlist.add(cards.get(38));
        pack2Setlist.add(cards.get(39));
        pack2Setlist.add(cards.get(43));

        List<Card> pack3Setlist = new ArrayList<>();
        pack3Setlist.add(cards.get(10));
        pack3Setlist.add(cards.get(11));
        pack3Setlist.add(cards.get(12));
        pack3Setlist.add(cards.get(13));
        pack3Setlist.add(cards.get(14));
        pack3Setlist.add(cards.get(23));
        pack3Setlist.add(cards.get(24));
        pack3Setlist.add(cards.get(25));
        pack3Setlist.add(cards.get(26));
        pack3Setlist.add(cards.get(33));
        pack3Setlist.add(cards.get(34));
        pack3Setlist.add(cards.get(35));
        pack3Setlist.add(cards.get(40));
        pack3Setlist.add(cards.get(41));
        pack3Setlist.add(cards.get(44));

        Pack pack1 = new Pack(0,"Assorted Critters", "", packSlots, pack1Setlist);
        Pack pack2 = new Pack(1,"Athletic Assailants", "", packSlots, pack2Setlist);
        Pack pack3 = new Pack(2,"Power of the Sea", "", packSlots, pack3Setlist);

        testPacks.add(pack1);
        testPacks.add(pack2);
        testPacks.add(pack3);

        packDB = new Registry<>(testPacks);
        cardDB = new Registry<>(cards);
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