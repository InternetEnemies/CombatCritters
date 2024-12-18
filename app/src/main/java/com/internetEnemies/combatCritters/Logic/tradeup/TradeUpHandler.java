/**
 * TradeUpHandler.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-19
 *
 * @PURPOSE:    implementation of ITradeUpValidator
 */

package com.internetEnemies.combatCritters.Logic.tradeup;

import com.internetEnemies.combatCritters.Logic.exceptions.InvalidTradeUpCardsException;
import com.internetEnemies.combatCritters.Logic.transaction.ITransactionHandlerDeprecated;
import com.internetEnemies.combatCritters.Logic.transaction.TransactionHandlerDeprecated;
import com.internetEnemies.combatCritters.Logic.transaction.builders.TradeTransactionBuilder;
import com.internetEnemies.combatCritters.data.Database;
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
    private final ITransactionHandlerDeprecated transactionHandler;
    private final List<CardOrder> cardOrder;
    private List<Card> tradeUpCards;
    private Card.Rarity currentRarity;

    public TradeUpHandler(ITradeUpValidator validator, ICardSearch cardSearch, ITransactionHandlerDeprecated transactionHandler, List<CardOrder> cardOrder){
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
        this.transactionHandler = new TransactionHandlerDeprecated();
        this.cardOrder = cardOrder;
        tradeUpCards = new ArrayList<Card>();
    }

    @Override
    public List<ItemStack<Card>> getCards() {
        CardFilter filter;
        List<Card.Rarity> rarities = new ArrayList<>();
        //decide should we use blacklist of legendary  or whitelist of the current rarity
        if(!tradeUpCards.isEmpty()) {
            rarities.add(currentRarity);
            filter = new CardFilter(true, rarities, true, null, false);
        }else{
            rarities.add(Card.Rarity.LEGENDARY);
            filter = new CardFilter(false, rarities, true, null, false);
        }
        // get the card that inside inventory
        List<ItemStack<Card>> showingList = cardSearch.get(cardOrder,filter);
        // cards in inventory - selected cards
        // a subset of card that show up on user screen, not including cards that are already selected
        if(!tradeUpCards.isEmpty()){
            //search for every selected cards in the showingList, and subtract the amount
            // or the item stack itself
            // match using its id
            for(Card selectedCard: tradeUpCards){
                for(int i = 0;i<showingList.size();i++){
                    ItemStack<Card> item = showingList.get(i);
                    if(item.getItem().getId() == selectedCard.getId()){
                        if(item.getAmount() <= 1){
                            showingList.remove(item);
                        }else{
                            showingList.set(i,new ItemStack<>(item.getItem(), item.getAmount()-1));
                        }
                        break;
                    }
                }
            }
        }
        return showingList;
    }

    @Override
    public TradeUpValidity addCard(Card card) {
        assert card != null;
        if(tradeUpCards.isEmpty()){
            currentRarity = card.getRarity();
        }
            tradeUpCards.add(card);
        return isValid();
    }

    @Override
    public TradeUpValidity removeCard(Card card) {
        assert card != null;
        assert tradeUpCards.contains(card);
        tradeUpCards.remove(card);
        return isValid();
    }

    @Override
    public List<Card> getSelectedCards() {
        return tradeUpCards;
    }

    @Override
    public Card confirmTradeUp() throws InvalidTradeUpCardsException {
        ITradeTransactionBuilder builder = new TradeTransactionBuilder();
        //converting the tradeUpCards into a list of ItemStack
        List<ItemStack<Card>> itemStackList  = getItemStackList();
        //adding the itemstack into the tradeBuilder
        for(ItemStack<?> cards: itemStackList){
            builder.addToGiven(cards);
        }
        //getting the received card rarity filter based on current card rarity
        ItemStack<Card> tradeUpCard = getTradeUpCard();
        //give an amount to it
        tradeUpCard = new ItemStack<>(tradeUpCard.getItem(),1);
        builder.addToReceived(tradeUpCard);
        //build up the transaction
        TradeTransaction tradeTransaction = builder.build();
        TradeUpValidity status = isValid();
        //perform the transaction if the trade is fine in trade up aspect
        if(status.isValid()){
            boolean flag = transactionHandler.performTransaction(tradeTransaction);
            //ui problem
            if(!flag){
                throw new InvalidTradeUpCardsException("Inventory does not have these cards.");
            }
            //reset the selected cards if the deal is done
            reset();
        }else{
            throw new InvalidTradeUpCardsException(status.getDifference());
        }
        return tradeUpCard.getItem();
    }

    @Override
    public Card.Rarity getCurrentTradeUpRarity(){
        Card.Rarity result;
        if(tradeUpCards.isEmpty()){
            result = null;
        }else{
            result = getTradeUpCard().getItem().getRarity();
        }
        return result;
    }

    /**
     * get a random tradeUpCard based on current rarity
     */
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
    public void reset(){
        tradeUpCards = new ArrayList<Card>();
        currentRarity = null;
    }

    /**
     * converting tradeUpCards into List<ItemStack<Card>>
     */
    private List<ItemStack<Card>> getItemStackList(){
        Map<Card, Integer> accumulatedCards = new HashMap<>();
        for (Card card : tradeUpCards) {
            Integer currentAmount = accumulatedCards.get(card);
            if (currentAmount != null) {
                accumulatedCards.put(card, currentAmount + 1);
            } else {
                accumulatedCards.put(card, 1);
            }
        }
        List<ItemStack<Card>> itemStackList = new ArrayList<>();
        for (Map.Entry<Card, Integer> entry : accumulatedCards.entrySet()) {
            itemStackList.add(new ItemStack<>(entry.getKey(), entry.getValue()));
        }

        return itemStackList;
    }


    /**
     * the current state of tradeUpCards
     * @return a TradeUpValidity indicating the current tradeUpCards
     */
    @Override
    public TradeUpValidity isValid(){
        return validator.validate(getItemStackList());
    }
}
