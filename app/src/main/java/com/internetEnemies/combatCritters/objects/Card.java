package com.internetEnemies.combatCritters.objects;

public abstract class Card {
    public static enum Rarity {
        COMMON,
        RARE,
        LEGENDARY
    }

    private final int id;
    private final String name;
    private final String image;
    private final int playCost;
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

    //GETTERS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getPlayCost() {
        return playCost;
    }

    public Rarity getRarity() {
        return rarity;
    }

}
