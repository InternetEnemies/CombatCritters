package com.internetEnemies.combatCritters.objects;

import androidx.annotation.NonNull;

import java.util.Objects;

public abstract class Card implements IItem {
    public static enum Rarity {
        COMMON,
        UNCOMMON,
        RARE,
        EPIC,
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return id == card.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    /**
     * clone the card object using a builder
     * @param builder builder to clone with
     */
    public void clone(ICardBuilder builder) {
        builder.setId(this.id);
        builder.setName(this.name);
        builder.setImage(this.image);
        builder.setCost(this.playCost);
        builder.setRarity(this.rarity);
    }

    @NonNull
    public String toString(){
        return "Card: " + this.id + " " + this.name;
    }
}
