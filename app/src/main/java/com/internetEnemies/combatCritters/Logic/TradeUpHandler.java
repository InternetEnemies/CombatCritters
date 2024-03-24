/**
 * TradeUpHandler.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-19
 *
 * @PURPOSE:    implementation of ITradeUpValidator
 */

package com.internetEnemies.combatCritters.Logic;

import androidx.annotation.NonNull;

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
    public List<ItemStack<Card>> getCards() {
        CardFilter filter;
        List<Card.Rarity> rarities = new ArrayList<>();
        if(!tradeUpCards.isEmpty()) {
            rarities.add(currentRarity);
            filter = new CardFilter(true, rarities, true, null, false);
        }else{
            rarities.add(Card.Rarity.LEGENDARY);
            filter = new CardFilter(false, rarities, true, null, false);
        }
        return cardSearch.get(cardOrder,filter);
    }

    @Override
    public void addCard(Card card) {
        assert card != null;
        if(tradeUpCards.isEmpty()){
            currentRarity = card.getRarity();
        }
            tradeUpCards.add(card);
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
        //converting the tradeUpCards into a list of ItemStack
        List<ItemStack<Card>> itemStackList  = getItemStackList();
        //adding the itemstack into the tradeBuilder
        for(ItemStack<Card> cards: itemStackList){
            builder.addToGiven(cards);
        }
        //getting the received card rarity filter based on current card rarity
        ItemStack<Card> tradeUpCard = getTradeUpCard();
        //give an amount to it
        tradeUpCard = new ItemStack<>(tradeUpCard.getItem(),1);
        builder.addToReceived(tradeUpCard);
        //build up the transaction
        TradeTransaction tradeTransaction = builder.build();
        TradeUpValidity status = validator.validate(tradeTransaction.getGiven());
        //perform the transaction if the trade is fine in trade up aspect
        if(status.isValid()){
            boolean flag = transactionHandler.performTransaction(tradeTransaction);
            //ui problem
            if(!flag){
                throw new IllegalArgumentException("inventory does not have these card, should not be happened");
            }
            //reset the selected cards if the deal is done
            resetSelectedCards();
        }
        return status;
    }

    /**
     * get a random tradeUpCard based on current rarity
     */
    @NonNull
    private ItemStack<Card> getTradeUpCard() {
        Card.Rarity tradeUpRarity;
        if(tradeUpCards.isEmpty() || (currentRarity == Card.Rarity.LEGENDARY)){
            tradeUpRarity = Card.Rarity.values()[0];
        }else{
            tradeUpRarity = Card.Rarity.values()[currentRarity.ordinal()+1];
        }

        List<Card.Rarity> rarities = new ArrayList<>();
        rarities.add(tradeUpRarity);
        CardFilter filter = new CardFilter(true,rarities,false,null,false);
        List<ItemStack<Card>> cardPool = cardSearch.get(cardOrder,filter);
        Random random = new Random();
        int randomIndex = random.nextInt(cardPool.size());
        return cardPool.get(randomIndex);
    }

    /**
     * reset the current selected cards, called a new rarity of inventory cards is fetched
     */
    private void resetSelectedCards(){
        tradeUpCards = new ArrayList<Card>();
    }

    /**
     * converting List<Card> into List<ItemStack<Card>>
     */
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
