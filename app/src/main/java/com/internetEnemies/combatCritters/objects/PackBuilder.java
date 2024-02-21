package com.internetEnemies.combatCritters.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

public class PackBuilder implements IPackBuilder{
    private int id;
    private String name;
    private String image;
    private final List<CardSlot> probabilityList; // list of probability maps for each slot and its generated rarity
    private final List<Card> setList; //set of all cards in the pack
    public PackBuilder(){
        this.id = 0;
        this.name = "";
        this.image = "";

        setList = new ArrayList<>();
        probabilityList = new ArrayList<>();
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name){
        this.name = name;

    }
    public void setImage(String image){
        this.image = image;
    }

    public PackBuilder addSetList(List<Card> cards){
        setList.addAll(cards);
        return this;
    }
    public void addSlot(NavigableMap<Double, Card.Rarity> weightMap){
        probabilityList.add(new CardSlotBuilder().addProbabilityMap(weightMap).build());
    }
    public Pack build(){
        return new Pack(id, name, image, probabilityList, setList);
    }

}
