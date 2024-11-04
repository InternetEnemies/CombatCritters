package com.internetEnemies.combatCritters.LogicUnitTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.IUserDataFactory;
import com.internetEnemies.combatCritters.Logic.users.IUserInitializer;
import com.internetEnemies.combatCritters.Logic.users.UserInitializer;
import com.internetEnemies.combatCritters.data.*;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.objects.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserInitializerTest {
    IUserInitializer userInitializer;
    
    IUserDataFactory userDataFactory;
    ICardInventory cardInventory;
    IPackInventory packInventory;
    ICurrencyInventory currencyInventory;
    IRegistry<Card> cardRegistry;
    IRegistry<Pack> packRegistry;
    
    @Before
    public void setup() {
        userDataFactory = mock(IUserDataFactory.class);
        packRegistry = (IRegistry<Pack>) mock(IRegistry.class);
        cardRegistry = (IRegistry<Card>) mock(IRegistry.class);
        cardInventory = mock(ICardInventory.class);
        packInventory = mock(IPackInventory.class);
        currencyInventory = mock(ICurrencyInventory.class);
        
        when(userDataFactory.getPackInventory(any())).thenReturn(packInventory);
        when(userDataFactory.getCardInventory(any())).thenReturn(cardInventory);
        when(userDataFactory.getCurrencyInventory(any())).thenReturn(currencyInventory);
        
        userInitializer = new UserInitializer(packRegistry, cardRegistry, userDataFactory);
    }
    
    @Test
    public void test_initialize(){
        when(packRegistry.getSingle(anyInt())).thenReturn(mock(Pack.class));
        when(cardRegistry.getListOf(anyList())).thenReturn(List.of(mock(Card.class)));
        
        userInitializer.initialize(mock(User.class));
        
        verify(packInventory).addPack(any());
        verify(cardInventory).addCard(any());
    }
    
}
