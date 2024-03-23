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
import com.internetEnemies.combatCritters.objects.ITradeTransactionBuilder;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.TradeTransaction;
import com.internetEnemies.combatCritters.objects.TradeUpValidity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TradeUpHandler implements ITradeUpHandler{
    private final ITradeUpValidator validator;
    private final ICardSearch cardSearch;
    private final ITransactionHandler transactionHandler;
    private final List<CardOrder> cardOrder;

    private List<Card> tradeUpCards;
    private Card.Rarity currentRarity;

    public TradeUpHandler(ITradeUpValidator validator, ICardSearch cardSearch, ITransactionHandler transactionHandler, List<CardOrder> cardOrder){
        this.validator = validator;
        this.cardSearch = cardSearch;
        this.transactionHandler = transactionHandler;
        this.cardOrder = cardOrder;
        tradeUpCards = new ArrayList<Card>();
    }

    public TradeUpHandler(){
        List<CardOrder> cardOrder = new ArrayList<>();
        cardOrder.add(CardOrder.RARITY);
        cardOrder.add(CardOrder.NAME);
        this.validator =new TradeUpValidator();
        this.cardSearch = Database.getInstance().getCardSearch();
        this.transactionHandler = new TransactionHandler();
        this.cardOrder = cardOrder;
    }

    @Override
    public List<ItemStack<Card>> getCards(Card.Rarity rarity) {
        List<Card.Rarity> rarities = new ArrayList<>();
        rarities.add(rarity);
        CardFilter filter = new CardFilter(true,rarities,true,null,false);
        resetSelectedCards();
        return cardSearch.get(cardOrder,filter);
    }

    @Override
    public void addCard(Card card) {
        assert card != null;
        if(tradeUpCards.isEmpty()){
            tradeUpCards.add(card);
            currentRarity = card.getRarity();
        }else{
            assert card.getRarity() == currentRarity;
            tradeUpCards.add(card);
        }
    }

    @Override
    public void removeCard(Card card) {
        assert card != null;
        assert tradeUpCards.contains(card);
        tradeUpCards.remove(card);
    }

    @Override
    public List<Card> getSelectedCards() {
        return tradeUpCards;
    }

    @Override
    public TradeUpValidity confirmTradeUp() {
        ITradeTransactionBuilder builder = new TradeTransactionBuilder();
        List<ItemStack<Card>> itemStackList  = getItemStackList();
        for(ItemStack<Card> cards: itemStackList){
            builder.addToGiven(cards);
        }
        Card.Rarity tradeUpRarity;
        if(tradeUpCards.isEmpty()){
            tradeUpRarity = Card.Rarity.values()[0];
        }else{
            int currentRarityOrdinal = tradeUpCards.get(0).getRarity().ordinal();
            tradeUpRarity = Card.Rarity.values()[++currentRarityOrdinal];
        }
        List<Card.Rarity> rarities = new ArrayList<>();
        rarities.add(tradeUpRarity);
        CardFilter filter = new CardFilter(true,rarities,false,null,false);
        List<ItemStack<Card>> cardPool = cardSearch.get(cardOrder,filter);
        Random random = new Random();
        int randomIndex = random.nextInt(cardPool.size());
        ItemStack<Card> tradeUpCard = cardPool.get(randomIndex);
        tradeUpCard = new ItemStack<>(tradeUpCard.getItem(),1);
        builder.addToReceived(tradeUpCard);
        TradeTransaction tradeTransaction = builder.build();
        TradeUpValidity status = validator.validate(tradeTransaction);
        if(status.isValid()){
            boolean flag = transactionHandler.performTransaction(tradeTransaction);
            if(!flag){
                throw new IllegalArgumentException("inventory does not have these card, should not be happened");
            }
        }
        return status;
    }

    /**
     * reset the current selected cards, called a new rarity of inventory cards is fetched
     */
    private void resetSelectedCards(){
        tradeUpCards = new ArrayList<Card>();
    }

    private List<ItemStack<Card>> getItemStackList(){
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
}
