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
import com.internetEnemies.combatCritters.objects.TradeUpValidity;

import java.util.ArrayList;
import java.util.List;

public class TradeUpValidator implements ITradeUpValidator{

    public static final int TRADE_UP_REQUIREMENT = 5;

    @Override
    public TradeUpValidity validate(List<ItemStack<?>> givenListStack) {
        assert givenListStack != null;
        List<Card> givenList = itemStackCardToList(givenListStack);

        int differentNum = difference(givenList.size());
        return new TradeUpValidity(sameRarity(givenList)&&rarityLimit(givenList)&&differentNum == 0,differentNum);
    }

    /**
     * subtract the number of cards by the requirement
     * @param actual number of cards
     * @return 0 = meet the requirement
     *         positive = not enough cards
     *         negative = too much cards
     */
    private int difference(int actual){
        return (TRADE_UP_REQUIREMENT - actual);
    }

    /**
     * convert the itemstack list into a list of card
     * @param itemStackList list of item stack that only consist of cards
     * @return list of cards
     */
    private List<Card> itemStackCardToList(List<ItemStack<?>> itemStackList){
        List<Card> result = new ArrayList<>();
        for(ItemStack<?> itemStack: itemStackList) {
                for (int i = 0; i < itemStack.getAmount(); i++) {
                    result.add((Card) itemStack.getItem());
            }
        }
        return result;
    }

    /**
     * check if the list of card only contain the same rarity
     * @param receivedList a list of cards
     * @return true: valid
     *         false: invalid
     */
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

    /**
     * check does a list of card contains legendary card
     * @param receivedList a list of card
     * @return true: not contains legendary cards, valid
     *         false: contains legendary cards, invalid
     */
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
