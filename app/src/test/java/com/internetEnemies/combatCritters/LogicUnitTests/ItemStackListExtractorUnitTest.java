package com.internetEnemies.combatCritters.LogicUnitTests;

import android.content.ClipData;

import com.internetEnemies.combatCritters.Logic.ItemStackListExtractor;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;

import org.checkerframework.checker.units.qual.C;
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
    private ItemStack<Currency> currencyStack1;
    private ItemStack<Currency> currencyStack2;

    @Before
    public void setup() {
        itemStackList = new ArrayList<>();
        extractor = null;

        cardStack1 = new ItemStack<>(new CritterCard(0,"","",0, Card.Rarity.COMMON,0,0,null), 1);
        cardStack2 = new ItemStack<>(new CritterCard(0,"","",0, Card.Rarity.UNCOMMON,0,0,null), 1);
        packStack1 = new ItemStack<>(new Pack(0,"","",new ArrayList<>(),new ArrayList<>()), 1);
        packStack2 = new ItemStack<>(new Pack(1,"","",new ArrayList<>(),new ArrayList<>()), 1);
        currencyStack1 = new ItemStack<>(new Currency(1), 1);
        currencyStack2 = new ItemStack<>(new Currency(1), 1);
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
    public void testOnlyCurrency() {
        itemStackList.add(currencyStack1);
        itemStackList.add(currencyStack2);

        extractor = new ItemStackListExtractor(itemStackList);

        List<Currency> currencies = extractor.getCurrencies();

        Assert.assertEquals(2, currencies.size());
        Assert.assertTrue(currencies.contains(currencyStack1.getItem()));
        Assert.assertTrue(currencies.contains(currencyStack2.getItem()));
    }

    @Test
    public void testCardsAndPacksAndCurrencies() {
        itemStackList.add(cardStack2);
        itemStackList.add(packStack1);
        itemStackList.add(currencyStack1);
        itemStackList.add(cardStack1);
        itemStackList.add(packStack2);
        itemStackList.add(currencyStack2);


        extractor = new ItemStackListExtractor(itemStackList);

        List<Pack> packs = extractor.getPacks();
        List<Card> cards = extractor.getCards();
        List<Currency> currencies = extractor.getCurrencies();

        Assert.assertEquals(2, packs.size());
        Assert.assertTrue(packs.contains(packStack1.getItem()));
        Assert.assertTrue(packs.contains(packStack2.getItem()));

        Assert.assertEquals(2, cards.size());
        Assert.assertTrue(cards.contains(cardStack1.getItem()));
        Assert.assertTrue(cards.contains(cardStack2.getItem()));

        Assert.assertEquals(2, currencies.size());
        Assert.assertTrue(currencies.contains(currencyStack1.getItem()));
        Assert.assertTrue(currencies.contains(currencyStack2.getItem()));
    }

    @Test
    public void testEmpty() {
        itemStackList = new ArrayList<>();

        extractor = new ItemStackListExtractor(itemStackList);
        List<Card> cards = extractor.getCards();
        List<Pack> packs = extractor.getPacks();
        List<Currency> currencies = extractor.getCurrencies();

        Assert.assertEquals(0, cards.size());
        Assert.assertEquals(0, packs.size());
        Assert.assertEquals(0, currencies.size());
    }
}
