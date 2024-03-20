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
import com.internetEnemies.combatCritters.objects.CardFilter;
import com.internetEnemies.combatCritters.objects.CardOrder;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.TradeUpValidity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradeUpHandler implements ITradeUpHandler{
    private final ITradeUpValidator validator;
    private final ICardSearch cardSearch;
    private final ICardInventory cardInventory;
    private final ITransactionHandler transactionHandler;
    private final List<CardOrder> cardOrder;

    private List<Card> tradeUpCards;

    public TradeUpHandler(ITradeUpValidator validator, ICardSearch cardSearch, ICardInventory cardInventory, ITransactionHandler transactionHandler, CardOrder order){
        this.validator = validator;
        this.cardSearch = cardSearch;
        this.cardInventory = cardInventory;
        this.transactionHandler = transactionHandler;
        this.cardOrder = new ArrayList<CardOrder>();
        this.cardOrder.add(order);
        tradeUpCards = new ArrayList<Card>();
    }

    public TradeUpHandler(){
        this(new TradeUpValidator(),
                Database.getInstance().getCardSearch(),
                Database.getInstance().getCardInventory(),
                new TransactionHandler(),
                CardOrder.NAME);
    }

    @Override
    public List<ItemStack<Card>> getCards(Card.Rarity rarity) {
        List<Card.Rarity> rarities = new ArrayList<>();
        rarities.add(rarity);
        CardFilter filter = new CardFilter(true,rarities,true,null,false);
        return cardSearch.get(cardOrder,filter);
    }

    @Override
    public void addCard(Card card) {

    }

    @Override
    public void removeCard(Card card) {

    }

    @Override
    public List<ItemStack<Card>> getSelectedCards() {
        Map<Card, Integer> accumulatedCards = new HashMap<Card, Integer>();
        for(Card card: tradeUpCards){
            if(accumulatedCards.containsKey(card)){
                int currentAmount = accumulatedCards.get(card);
                accumulatedCards.put(card, ++currentAmount);
            }else{
                accumulatedCards.put(card,1);
            }
        }
        List<ItemStack<Card>> itemStackList = new ArrayList<>();
        for(Map.Entry<Card, Integer> entry: accumulatedCards.entrySet()){
            itemStackList.add(new ItemStack<>(entry.getKey(), entry.getValue()));
        }
        return itemStackList;
    }

    @Override
    public TradeUpValidity confirmTradeUp() {
        return validator.validate(null);
    }
}
