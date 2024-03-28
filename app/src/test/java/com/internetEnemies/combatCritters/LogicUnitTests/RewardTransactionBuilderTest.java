package com.internetEnemies.combatCritters.LogicUnitTests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.internetEnemies.combatCritters.Logic.RewardTransactionBuilder;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.battles.IRewardTransactionBuilder;

import org.junit.Before;
import org.junit.Test;

public class RewardTransactionBuilderTest {

    private IRewardTransactionBuilder builder;
    @Before
    public void setup(){
        builder = new RewardTransactionBuilder();
    }

    @Test
    public void testSetID(){
        builder.setID(1);
    }

    @Test
    public void testAddToReceived(){
        Card card = new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null);
        ItemStack<?> cardStack = new ItemStack<>(card,1);
        builder.addToReceived(cardStack);
    }

    @Test
    public void testBuild(){
        builder.setID(1);
        Card card = new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null);
        ItemStack<?> cardStack = new ItemStack<>(card,1);
        builder.addToReceived(cardStack);
        assert builder.build() != null;
    }

    @Test
    public void testReset(){
        builder.setID(1);
        Card card = new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null);
        ItemStack<?> cardStack = new ItemStack<>(card,1);
        builder.addToReceived(cardStack);
        assert builder.build() != null;
        builder.reset();
    }
}
