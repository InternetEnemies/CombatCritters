package com.combatcritters.critterspring.battle.payloads;

/**
 * BattleRequest.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/25/24
 * 
 * @PURPOSE:    represent a basic request 
 */
public record BattleRequest(String resource, String payload) {
    /**
     * get a BattleRequest object from the ray request data
     */
    public static BattleRequest fromRaw(String raw){
        String[] split = raw.split("\n",2); // split on first newline
        return new BattleRequest(split[0].toLowerCase(), split[1]);
    }
}
