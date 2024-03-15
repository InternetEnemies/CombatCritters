package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Currency;
/**
 * CardDeconstructor.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-05
 *
 * @PURPOSE:    Converts cards in the player's inventory into currency that they can use.
 */

public class CardDeconstructor {

    public static final int COMMON_VALUE = 5;
    public static final int UNCOMMON_VALUE = 7;
    public static final int RARE_VALUE = 15;
    public static final int EPIC_VALUE = 20;
    public static final int LEGENDARY_VALUE = 30;
    private final ICardInventory cardInventory;
    private final ICurrencyInventory currencyInventory;
    public CardDeconstructor(){
        this.cardInventory = Database.getInstance().getCardInventory();
        this.currencyInventory = Database.getInstance().getCurrencyInventory();
    }

    public CardDeconstructor(ICardInventory cardInventory, ICurrencyInventory currencyInventory){
        this.cardInventory = cardInventory;
        this.currencyInventory = currencyInventory;
    }

    /**
     * Adds a value of currency to the player's currency inventory based on the card's rarity.
     * @param card the card being deconstructed
     */
    public void deconstruct(Card card){
        int value = 0;

        switch (card.getRarity()){
            case COMMON:
                value = COMMON_VALUE;
                break;
            case UNCOMMON:
                value = UNCOMMON_VALUE;
                break;
            case RARE:
                value = RARE_VALUE;
                break;
            case EPIC:
                value = EPIC_VALUE;
                break;
            case LEGENDARY:
                value = LEGENDARY_VALUE;
                break;
        }

        Currency toBeAdded = new Currency(value);
        currencyInventory.addToBalance(toBeAdded);
        cardInventory.removeCard(card, 1);

    }
    /**
     * Deconstructs a certain quantity of cards in the user's inventory.
     * @param card the card being deconstructed
     * @param amount the amount of the card to be removed
     */
    public int deconstructMultiple(Card card, int amount){
        int total = 0;
        int value = 0;

        switch (card.getRarity()){
            case COMMON:
                value = COMMON_VALUE;
                break;
            case UNCOMMON:
                value = UNCOMMON_VALUE;
                break;
            case RARE:
                value = RARE_VALUE;
                break;
            case EPIC:
                value = EPIC_VALUE;
                break;
            case LEGENDARY:
                value = LEGENDARY_VALUE;
                break;
        }

        for (int i = 0; i < amount; i++){
            total += value;
            deconstruct(card);
        }

        return total;
    }
}
