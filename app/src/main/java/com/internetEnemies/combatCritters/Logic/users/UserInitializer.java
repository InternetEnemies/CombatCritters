package com.internetEnemies.combatCritters.Logic.users;

import com.internetEnemies.combatCritters.Logic.IUserDataFactory;
import com.internetEnemies.combatCritters.data.*;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.objects.User;

import java.util.List;

public class UserInitializer implements IUserInitializer {


    private final IRegistry<Pack> packRegistry;
    private final IRegistry<Card> cardRegistry;
    private final IUserDataFactory userDataFactory;

    public UserInitializer(IRegistry<Pack> packRegistry, IRegistry<Card> cardRegistry, IUserDataFactory userDataFactory) {
        this.packRegistry = packRegistry;
        this.cardRegistry = cardRegistry;
        this.userDataFactory = userDataFactory;
    }
    
    @Override
    public void initialize(User user) {
        initCards(user);
        initPacks(user);
    }

    private void initPacks(User user) {
        IPackInventory packInventory = userDataFactory.getPackInventory(user);
        var pack = packRegistry.getSingle(1);
        packInventory.addPack(pack);
    }

    private void initCards(User user) {
        ICardInventory cardInventory = userDataFactory.getCardInventory(user);
        List<Card> cards = cardRegistry.getListOf(List.of(1,2,3,4,5,6));
        for (Card card : cards) {
            cardInventory.addCard(card);
        }
    }
}
