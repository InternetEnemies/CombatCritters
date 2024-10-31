package com.internetEnemies.combatCritters.Logic.transaction.participant;

import com.internetEnemies.combatCritters.objects.User;

/**
 * IUserParticipantFactory.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/31/24
 * 
 * @PURPOSE:    provide interface for creating user participant instances
 */
public interface IUserParticipantFactory {
    /**
     * get UserParticipant instance for a user
     */
    UserParticipant createUserParticipant(User user);
}
