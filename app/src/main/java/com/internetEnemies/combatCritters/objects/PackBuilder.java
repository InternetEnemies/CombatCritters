/**
 * ICardSearch.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-19
 *
 * @PURPOSE:    Builder for pack
 */

package com.internetEnemies.combatCritters.objects;

import java.util.ArrayList;
import java.util.List;

public class PackBuilder implements IPackBuilder{
    private int id;
    private String name;
    private String image;
    private List<CardSlot> probabilityList; // list of probability maps for each slot and its generated rarity
    private List<Card> setList; //set of all cards in the pack
    public PackBuilder(){
        this.reset();
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

    public void addSetList(List<Card> cards){
        setList.addAll(cards);
    }

    public void addSlot(CardSlot cardSlot){
        probabilityList.add(cardSlot);
    }
    public Pack build(){
        return new Pack(id, name, image, probabilityList, setList);
    }
    public void reset(){
        this.id = 0;
        this.name = "";
        this.image = "";

        setList = new ArrayList<>();
        probabilityList = new ArrayList<>();
    }

}
