package com.internetEnemies.combatCritters.objects;

/**
 * User.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     9/24/24
 * 
 * @PURPOSE:    represent a user and its data
 */
public class User {
    private final int id;
    private final String username;
    private String password;

    public User(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * @return id of the user
     */
    public int getId() {
        return id;
    }

    /**
     * @return username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return password of the user
     */
    public String getPassword() {
        return password;
    }
}
