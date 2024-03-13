package com.internetEnemies.combatCritters.LogicUnitTests;

import com.internetEnemies.combatCritters.Logic.ItemStackListExtractor;
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

public class ItemStackListExtractorUnitTest {
    private List<ItemStack<?>> itemStackList;
    private ItemStackListExtractor extractor;

    private ItemStack<Card> cardStack1;
    private ItemStack<Card> cardStack2;
    private ItemStack<Pack> packStack1;
    private ItemStack<Pack> packStack2;

    @Before
    public void setup() {
        itemStackList = new ArrayList<>();
        extractor = null;

        cardStack1 = new ItemStack<>(new CritterCard(0,"","",0, Card.Rarity.COMMON,0,0,null), 1);
        cardStack2 = new ItemStack<>(new CritterCard(0,"","",0, Card.Rarity.UNCOMMON,0,0,null), 1);
        packStack1 = new ItemStack<>(new Pack(0,"","",null,null), 1);
        packStack2 = new ItemStack<>(new Pack(1,"","",null,null), 1);
    }

    @Test
    public void testOnlyCards() {
        itemStackList.add(cardStack1);
        itemStackList.add(cardStack2);

        extractor = new ItemStackListExtractor(itemStackList);

        List<Card> cards = extractor.getCards();

        Assert.assertEquals(2, cards.size());
        Assert.assertTrue(cards.contains(cardStack1.getItem()));
        Assert.assertTrue(cards.contains(cardStack2.getItem()));
    }

    @Test
    public void testOnlyPacks() {
        itemStackList.add(packStack1);
        itemStackList.add(packStack2);

        extractor = new ItemStackListExtractor(itemStackList);

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

        extractor = new ItemStackListExtractor(itemStackList);

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

        extractor = new ItemStackListExtractor(itemStackList);
        List<Card> cards = extractor.getCards();
        List<Pack> packs = extractor.getPacks();

        Assert.assertEquals(0, cards.size());
        Assert.assertEquals(0, packs.size());
    }
}
