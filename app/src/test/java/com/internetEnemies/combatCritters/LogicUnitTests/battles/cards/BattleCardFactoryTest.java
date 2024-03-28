package com.internetEnemies.combatCritters.LogicUnitTests.battles.cards;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.events.EventSystem;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;

import java.util.ArrayList;

/**
 * BattleCardFactoryTest.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-28
 *
 * @PURPOSE:    Test the BattleCardFactory
 */
public class BattleCardFactoryTest {
    @Test
    public void test_getCard(){
        IBattleCardFactory cardFactory = new BattleCardFactory(new EventSystem());
        CritterCard card = new CritterCard(0,"","",1, Card.Rarity.COMMON,1,1,new ArrayList<>());
        IBattleCard battleCard = cardFactory.getCard(card);
        assertNotNull(battleCard);
    }
}
