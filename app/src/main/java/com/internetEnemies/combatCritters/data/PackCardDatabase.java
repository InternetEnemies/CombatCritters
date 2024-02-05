package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PackCardDatabase {
    private static PackCardDatabase INSTANCE;

    private final IRegistry<Pack> packDB;
    private final IRegistry<Card> cardDB;
    static Card[] cards = {
            new CritterCard(1, "Waffle Warrior", "",3, Card.Rarity.COMMON,1,3, null),
            new CritterCard(2, "Pillow Pugilist", "",2, Card.Rarity.COMMON,1,2, null),
            new CritterCard(3, "Sock Samurai", "",1, Card.Rarity.COMMON,1,1, null),
            new CritterCard(4, "Bubble Gum Battlers", "",3, Card.Rarity.COMMON,0,1, null),
            new CritterCard(5, "Tuba Trooper", "",1, Card.Rarity.COMMON,1,2, null),
            new CritterCard(6, "Hole-In-One Hero", "",3, Card.Rarity.COMMON,2,1, null),
            new CritterCard(7, "Slam Dunk Soldier", "",1, Card.Rarity.COMMON,2,1, null),
            new CritterCard(8, "Slimy Slugger", "",2, Card.Rarity.COMMON,1,3, null),
            new CritterCard(9, "Dodgeball Dragon", "",2, Card.Rarity.COMMON,1,2, null),
            new CritterCard(10, "", "",3, Card.Rarity.COMMON,0,2, null),
            new CritterCard(11, "", "",2, Card.Rarity.COMMON,2,1, null),
            new CritterCard(12, "", "",3, Card.Rarity.COMMON,1,1, null),
            new CritterCard(13, "", "",1, Card.Rarity.COMMON,1,1, null),
            new CritterCard(14, "", "",1, Card.Rarity.COMMON,1,1, null),
            new CritterCard(15, "", "",2, Card.Rarity.COMMON,2,3, null),
            new CritterCard(16, "", "",1, Card.Rarity.COMMON,2,2, null),
            new CritterCard(17, "Furious Fridge Fighter", "",4, Card.Rarity.UNCOMMON,1,1, null),
            new CritterCard(18, "Bubble-Wrapped Berserker", "",3, Card.Rarity.UNCOMMON,1,3, null),
            new CritterCard(19, "Banana Barbarian", "",1, Card.Rarity.UNCOMMON,2,4, null),
            new CritterCard(20, "Pencil Swordsman", "",4, Card.Rarity.UNCOMMON,2,4, null),
            new CritterCard(21, "Goalkeeper Guy", "",1, Card.Rarity.UNCOMMON,1,3, null),
            new CritterCard(22, "Superstriker Gal", "",3, Card.Rarity.UNCOMMON,3,4, null),
            new CritterCard(23, "Slapshot Beast", "",1, Card.Rarity.UNCOMMON,4,3, null),
            new CritterCard(24, "Demonic Referee", "",4, Card.Rarity.UNCOMMON,3,4, null),
            new CritterCard(25, "", "",3, Card.Rarity.UNCOMMON,3,4, null),
            new CritterCard(26, "", "",1, Card.Rarity.UNCOMMON,3,3, null),
            new CritterCard(27, "", "",1, Card.Rarity.UNCOMMON,2,4, null),
            new CritterCard(28, "", "",1, Card.Rarity.UNCOMMON,2,4, null),
            new CritterCard(29, "", "",3, Card.Rarity.UNCOMMON,3,3, null),
            new CritterCard(30, "", "",2, Card.Rarity.UNCOMMON,3,1, null),
            new CritterCard(31, "", "",3, Card.Rarity.UNCOMMON,2,2, null),
            new CritterCard(32, "Kitchen Knight", "",3, Card.Rarity.RARE,1,4, null),
            new CritterCard(33, "Giggly Gorgon, the Snickering Serpent", "",4, Card.Rarity.RARE,3,5, null),
            new CritterCard(34, "Snickerdoodle Sphinx, the Grinning Guardian", "",2, Card.Rarity.RARE,2,1, null),
            new CritterCard(35, "", "",4, Card.Rarity.RARE,3,4, null),
            new CritterCard(36, "", "",1, Card.Rarity.RARE,2,5, null),
            new CritterCard(37, "", "",3, Card.Rarity.RARE,3,3, null),
            new CritterCard(38, "", "",3, Card.Rarity.RARE,1,3, null),
            new CritterCard(39, "", "",2, Card.Rarity.RARE,3,4, null),
            new CritterCard(40, "", "",1, Card.Rarity.RARE,2,3, null),
            new CritterCard(41, "", "",1, Card.Rarity.RARE,2,3, null),
            new CritterCard(42, "", "",2, Card.Rarity.RARE,2,3, null),
            new CritterCard(43, "", "",2, Card.Rarity.RARE,4,4, null),
            new CritterCard(44, "", "",1, Card.Rarity.RARE,2,3, null),
            new CritterCard(45, "", "",3, Card.Rarity.RARE,2,3, null),
            new CritterCard(46, "", "",2, Card.Rarity.RARE,3,1, null),
            new CritterCard(47, "Sweet-Toothed Dragon", "",2, Card.Rarity.EPIC,4,6, null),
            new CritterCard(48, "UglyMan, the Hideous Hero", "",3, Card.Rarity.EPIC,4,5, null),
            new CritterCard(49, "", "",3, Card.Rarity.EPIC,4,5, null),
            new CritterCard(50, "", "",2, Card.Rarity.EPIC,4,5, null),
            new CritterCard(51, "", "",3, Card.Rarity.EPIC,4,6, null),
            new CritterCard(52, "", "",3, Card.Rarity.EPIC,5,6, null),
            new CritterCard(53, "", "",2, Card.Rarity.EPIC,3,6, null),
            new CritterCard(54, "", "",3, Card.Rarity.EPIC,5,6, null),
            new CritterCard(55, "", "",3, Card.Rarity.EPIC,4,6, null),
            new CritterCard(56, "", "",3, Card.Rarity.EPIC,5,6, null),
            new CritterCard(57, "White-Eyes Blue Dragon", "",2, Card.Rarity.LEGENDARY,4,5, null),
            new CritterCard(58, "", "",3, Card.Rarity.LEGENDARY,4,6, null),
            new CritterCard(59, "", "",3, Card.Rarity.LEGENDARY,5,5, null),
            new CritterCard(60, "", "",2, Card.Rarity.LEGENDARY,5,6, null)

    };

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
