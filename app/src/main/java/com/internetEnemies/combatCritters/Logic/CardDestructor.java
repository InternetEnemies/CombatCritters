package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Currency;

public class CardDestructor {

    //this feels like shit lol
    public static final int COMMON_VALUE = 5;
    public static final int UNCOMMON_VALUE = 7;
    public static final int RARE_VALUE = 15;
    public static final int EPIC_VALUE = 20;
    public static final int LEGENDARY_VALUE = 30;
    private ICardInventory cardInventory;
    private ICurrencyInventory currencyInventory;

    public CardDestructor(ICardInventory cardInventory, ICurrencyInventory currencyInventory){
        this.cardInventory = cardInventory;
        this.currencyInventory = currencyInventory;
    }
    //is there a nicer way to do this or am i just dumb
    /**
     * Adds a value of currency to the player's currency inventory based on the card's rarity.
     * @param card the card being deconstructed
     * @param id the id of the currency to be added to.
     */
    public void deconstruct(Card card, int id){
        if (card.getRarity().equals(Card.Rarity.COMMON)){
            Currency value = new Currency(COMMON_VALUE, id);
            currencyInventory.addtoBalance(value, id);
            cardInventory.removeCard(card, 1);
        }
        if (card.getRarity().equals(Card.Rarity.UNCOMMON)){
            Currency value = new Currency(UNCOMMON_VALUE, id);
            currencyInventory.addtoBalance(value, id);
            cardInventory.removeCard(card, 1);

        }
        if (card.getRarity().equals(Card.Rarity.RARE)){
            Currency value = new Currency(RARE_VALUE, id);
            currencyInventory.addtoBalance(value, id);
            cardInventory.removeCard(card, 1);

        }
        if (card.getRarity().equals(Card.Rarity.EPIC)){
            Currency value = new Currency(EPIC_VALUE, id);
            currencyInventory.addtoBalance(value, id);
            cardInventory.removeCard(card, 1);

        }
        if (card.getRarity().equals(Card.Rarity.LEGENDARY)){
            Currency value = new Currency(LEGENDARY_VALUE, id);
            currencyInventory.addtoBalance(value, id);
            cardInventory.removeCard(card, 1);

        }
    }

}
