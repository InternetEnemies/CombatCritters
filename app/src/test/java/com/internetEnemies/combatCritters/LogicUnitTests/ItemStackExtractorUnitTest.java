package com.internetEnemies.combatCritters.LogicUnitTests;

import com.internetEnemies.combatCritters.Logic.ItemStackExtractor;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.PackCardDatabase;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ItemStackExtractorUnitTest {
    private List<ItemStack<?>> itemStackList;
    private ItemStackExtractor extractor;

    private final IRegistry<Pack> stubPackDB = PackCardDatabase.getInstance().getPackDB();
    private final List<Pack> packs = stubPackDB.getAll();
    private final ItemStack<Card> cardStack1 = new ItemStack<>(new CritterCard(0,"","",0, Card.Rarity.COMMON,0,0,null), 1);
    private final ItemStack<Card> cardStack2 = new ItemStack<>(new CritterCard(0,"","",0, Card.Rarity.UNCOMMON,0,0,null), 1);
    private final ItemStack<Pack> packStack1 = new ItemStack<>(packs.get(0), 1);
    private final ItemStack<Pack> packStack2 = new ItemStack<>(packs.get(1), 1);

    @Before
    public void setup() {
        itemStackList = new ArrayList<>();
        extractor = null;
    }

    @Test
    public void testOnlyCards() {
        itemStackList.add(cardStack1);
        itemStackList.add(cardStack2);

        extractor = new ItemStackExtractor(itemStackList);

        List<Card> cards = extractor.getCards();

        Assert.assertEquals(2, cards.size());
        Assert.assertTrue(cards.contains(cardStack1.getItem()));
        Assert.assertTrue(cards.contains(cardStack2.getItem()));
    }

    @Test
    public void testOnlyPacks() {
        itemStackList.add(packStack1);
        itemStackList.add(packStack2);

        extractor = new ItemStackExtractor(itemStackList);

        List<Pack> packs = extractor.getPacks();

        Assert.assertEquals(2, packs.size());
        Assert.assertTrue(packs.contains(packStack1.getItem()));
        Assert.assertTrue(packs.contains(packStack2.getItem()));
    }

    @Test
    public void testCardsAndPacks() {
        itemStackList.add(cardStack2);
        itemStackList.add(packStack1);
        itemStackList.add(cardStack1);
        itemStackList.add(packStack2);

        extractor = new ItemStackExtractor(itemStackList);

        List<Pack> packs = extractor.getPacks();
        List<Card> cards = extractor.getCards();

        Assert.assertEquals(2, packs.size());
        Assert.assertTrue(packs.contains(packStack1.getItem()));
        Assert.assertTrue(packs.contains(packStack2.getItem()));

        Assert.assertEquals(2, cards.size());
        Assert.assertTrue(cards.contains(cardStack1.getItem()));
        Assert.assertTrue(cards.contains(cardStack2.getItem()));
    }

    @Test
    public void testEmpty() {
        itemStackList = new ArrayList<>();

        extractor = new ItemStackExtractor(itemStackList);
        List<Card> cards = extractor.getCards();
        List<Pack> packs = extractor.getPacks();

        Assert.assertEquals(0, cards.size());
        Assert.assertEquals(0, packs.size());
    }
}
