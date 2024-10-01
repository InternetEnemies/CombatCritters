package com.combatcritters.critterspring.payloads;

public record CardPayload<T>(int cardid, 
                             String name, 
                             int playcost,
                             int rarity,
                             String image,
                             String type,
                             String description,
                             T type_specific) {
}
