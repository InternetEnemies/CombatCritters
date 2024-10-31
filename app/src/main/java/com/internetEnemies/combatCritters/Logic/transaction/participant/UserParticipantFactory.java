package com.internetEnemies.combatCritters.Logic.transaction.participant;

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.objects.User;

/**
 * UserParticipantFactory.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/31/24
 * 
 * @PURPOSE:    basic factory for UserParticipants
 */
public class UserParticipantFactory implements IUserParticipantFactory {
    private final Database database;
    public UserParticipantFactory(Database database) {
        this.database = database;
    }
    @Override
    public UserParticipant createUserParticipant(User user) {
        return new UserParticipant(
                database.getCardInventory(user),
                database.getPackInventory(user),
                database.getCurrencyInventory(user)
                );
    }
}
