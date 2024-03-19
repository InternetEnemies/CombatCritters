/**
 * TradeUpHandler.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-19
 *
 * @PURPOSE:    implementation of ITradeUpValidator
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICardSearch;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardOrder;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.TradeUpValidity;

import java.util.List;

public class TradeUpHandler implements ITradeUpHandler{
    private ITradeUpValidator validator;
    private ICardSearch cardSearch;
    private ICardInventory cardInventory;

    private final CardOrder cardOrder;

    public TradeUpHandler(ITradeUpValidator validator, ICardSearch cardSearch, ICardInventory cardInventory, CardOrder cardOrder){
        this.validator = validator;
        this.cardSearch = cardSearch;
        this.cardInventory = cardInventory;
        this.cardOrder = cardOrder;
    }

    public TradeUpHandler(){
        this(new TradeUpValidator(),
                Database.getInstance().getCardSearch(),
                Database.getInstance().getCardInventory(),
                CardOrder.ID);
    }

    @Override
    public List<ItemStack<Card>> getCards(Card.Rarity rarity) {
        return null;
    }

    @Override
    public void addCard(Card card) {

    }

    @Override
    public void removeCard(Card card) {

    }

    @Override
    public List<ItemStack<Card>> getSelectedCards() {
        return null;
    }

    @Override
    public TradeUpValidity confirmTradeUp() {
        return validator.validate(null);
    }
}
