package com.internetEnemies.combatCritters.objects.battles;

import com.internetEnemies.combatCritters.objects.Transaction;

/**
 * Opponent.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/27/24
 *
 * @PURPOSE: Opponents DSO for the battle
 */
public class Opponent {
    private final int id;
    private final String name;
    private final String description;
    private final String imageRef;
    private final RewardTransaction rewards;

    public Opponent(int id, String name, String description, String imageRef, RewardTransaction rewards){
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageRef = imageRef;
        this.rewards = rewards;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getImage(){
        return imageRef;
    }

    public RewardTransaction getReward(){
        return rewards;
    }
}
