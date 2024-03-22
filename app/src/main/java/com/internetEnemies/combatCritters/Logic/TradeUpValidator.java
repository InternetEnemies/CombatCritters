/**
 * TradeUpValidator.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-19
 *
 * @PURPOSE:    implementation of ITradeUpValidator
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.TradeTransaction;
import com.internetEnemies.combatCritters.objects.TradeUpValidity;

import java.util.ArrayList;
import java.util.List;

public class TradeUpValidator implements ITradeUpValidator{

    public static final int TRADE_UP_REQUIREMENT = 5;

    private List<Card> receivedList;
    private List<Card> givenList;

    public TradeUpValidator(){

    }
    @Override
    public TradeUpValidity validate(TradeTransaction tradeUp) {
        assert tradeUp != null;
        receivedList = itemStackCardToList(tradeUp.getReceived());
        givenList = itemStackCardToList(tradeUp.getGiven());
        assert !givenList.isEmpty();   //this will be an error

        int differentNum = difference(receivedList.size());
        tradeUpCardRarity();
        return new TradeUpValidity(differentNum == 0,differentNum);
    }

    private void tradeUpCardRarity(){
        int receivedRarityOrdinal = receivedList.get(0).getRarity().ordinal();
        int givenRarityOrdinal = givenList.get(0).getRarity().ordinal();
        assert (givenRarityOrdinal == (receivedRarityOrdinal+1));
    }

    private int difference(int actual){
        return (TRADE_UP_REQUIREMENT - actual);
    }

    private List<Card> itemStackCardToList(List<ItemStack<?>> itemStackList){
        List<Card> result = new ArrayList<>();
        for(ItemStack<?> itemStack: itemStackList) {
                for (int i = 0; i < itemStack.getAmount(); i++) {
                    result.add((Card) itemStack.getItem());
            }
        }
        return result;
    }
}
