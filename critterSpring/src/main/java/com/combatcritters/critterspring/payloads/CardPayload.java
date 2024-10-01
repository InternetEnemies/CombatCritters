package com.combatcritters.critterspring.payloads;

import com.internetEnemies.combatCritters.objects.Card;

public record CardPayload<T>(int cardid,
                             String name,
                             int playcost,
                             int rarity,
                             String image,
                             String type,
                             String description,
                             T type_specific) {
    public static final String TYPE_CRITTER = "critter";
    public static final String TYPE_ITEM = "item";
    
    static public CardPayload<?> from(Card card) {
        CardPayloadFactory factory = new CardPayloadFactory();
        card.accept(factory);
        return factory.getPayload();
    }
}
