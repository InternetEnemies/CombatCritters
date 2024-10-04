package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.objects.User;

/**
 * HSQLDBUserModel.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/4/24
 * 
 * @PURPOSE:    parent class for db implementations that need users
 */
public class HSQLDBUserModel extends HSQLDBModel{
    private final User user;
    public HSQLDBUserModel(String dbPath, User user) {
        super(dbPath);
        this.user = user;
    }
    
    protected User getUser() {
        return user;
    }
}
