package com.internetEnemies.combatCritters.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

public class PackBuilder implements IPackBuilder{
    private final int id;
    private final String name;
    private final String image;
    private final List<CardSlot> probabilityList; // list of probability maps for each slot and its generated rarity
    private final List<Card> setList; //set of all cards in the pack
    public PackBuilder(int id, String name, String image){
        this.id = id;
        this.name = name;
        this.image = image;

        setList = new ArrayList<>();
        probabilityList = new ArrayList<>();
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
