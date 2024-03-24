/**
 * TradeUpValidator.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-19
 *
 * @PURPOSE:    implementation of ITradeUpValidator
 *              validate three properties:
 *              does given list of cards the same rarity
 *              does given list of cards under legendary
 *              does given list of cards have the the requirement number of cards
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

    public TradeUpValidator(){

    }
    @Override
    public TradeUpValidity validate(List<ItemStack<?>> givenListStack) {
        assert givenListStack != null;
        List<Card> givenList = itemStackCardToList(givenListStack);

        int differentNum = difference(givenList.size());
        return new TradeUpValidity(sameRarity(givenList)&&rarityLimit(givenList)&&differentNum == 0,differentNum);
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

    private boolean sameRarity(List<Card> receivedList){
        boolean flag = true;
        Card.Rarity currentRarity = null;
        for(Card card: receivedList){
            if(currentRarity == null){
                currentRarity = card.getRarity();
            }else{
                if(card.getRarity() != currentRarity){
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    private boolean rarityLimit(List<Card> receivedList){
        boolean flag = true;
        for(Card card: receivedList){
            if(card.getRarity() == Card.Rarity.LEGENDARY){
                flag = false;
                break;
            }
        }
        return flag;
    }
}
