package com.internetEnemies.combatCritters.objects;

public abstract class Card {
    public static enum Rarity {
        COMMON,
        RARE,
        LEGENDARY
    }

    private final int id;
    private final String name;
    private final String image;// TODO how do images work (/how should this be stored)
    private final int playCost;// TODO if we want multiple ways to play cards this should be done differently
    private final Rarity rarity;

    public Card(
            int id,
            String name,
            String image,
            int playCost,
            Rarity rarity
    ) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.playCost = playCost;
        this.rarity = rarity;
    }

}
