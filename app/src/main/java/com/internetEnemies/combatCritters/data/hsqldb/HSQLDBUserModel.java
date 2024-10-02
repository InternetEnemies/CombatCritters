package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.objects.User;

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
