package com.internetEnemies.combatCritters.LogicUnitTests.transaction;

import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.transaction.participant.IParticipant;
import com.internetEnemies.combatCritters.Logic.transaction.participant.UserParticipant;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;
import org.junit.Before;
import org.junit.Test;

public class ParticipantTest {
    ItemStack<?> itemStack;
    IItem item;
    IParticipant participant;
    @Before
    public void setup(){
        participant = new UserParticipant(mock(ICardInventory.class), mock(IPackInventory.class), mock(ICurrencyInventory.class));
        item = mock(IItem.class);
        itemStack = new ItemStack<>(item,1);
    }
    
    @Test
    public void test_add(){
        participant.add(itemStack);
        verify(item).accept(any());
    }
    @Test
    public void test_has(){
        participant.has(itemStack);
        verify(item).accept(any());
    }
    @Test
    public void test_remove(){
        participant.remove(itemStack);
        verify(item).accept(any());
    }
    
    
}
