package com.internetEnemies.combatCritters.objects;

import java.util.List;

public class Pack {
    private final int id;
    private final String name;
    private final String image;
    private final List<CardSlot> probabilityList; // list of probability maps for each slot and its generated rarity
    private final List<Card> setList; //set of all cards in the pack



    public Pack(int id, String name, String image, List<CardSlot> cards, List<Card> setList) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.probabilityList = cards;
        this.setList = setList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public List<CardSlot> getProbabilityList(){return probabilityList;}

    public List<Card> getSetList(){return setList;}

}