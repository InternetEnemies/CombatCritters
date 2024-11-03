package com.internetEnemies.combatCritters.LogicUnitTests.transaction;

import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.transaction.participant.IParticipant;
import com.internetEnemies.combatCritters.Logic.transaction.participant.UserParticipantFactory;
import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.User;
import org.junit.Before;
import org.junit.Test;

public class ParticipantTest {
    ItemStack<?> itemStack;
    IItem item;
    IParticipant participant;
    @Before
    public void setup(){
        Database database = mock(Database.class);
        when(database.getCurrencyInventory(any())).thenReturn(mock(ICurrencyInventory.class));
        when(database.getCardInventory(any())).thenReturn(mock(ICardInventory.class));
        when(database.getPackInventory(any())).thenReturn(mock(IPackInventory.class));
        
        participant = new UserParticipantFactory(database).createUserParticipant(mock(User.class));
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
